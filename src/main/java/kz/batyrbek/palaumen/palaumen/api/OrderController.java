package kz.batyrbek.palaumen.palaumen.api;

import kz.batyrbek.palaumen.palaumen.component.CartManager;
import kz.batyrbek.palaumen.palaumen.dto.CartItemDto;
import kz.batyrbek.palaumen.palaumen.dto.FoodDto;
import kz.batyrbek.palaumen.palaumen.dto.OrderDto;
import kz.batyrbek.palaumen.palaumen.mapper.CartItemMapper;
import kz.batyrbek.palaumen.palaumen.mapper.OrderMapper;
import kz.batyrbek.palaumen.palaumen.model.CartItem;
import kz.batyrbek.palaumen.palaumen.model.Customer;
import kz.batyrbek.palaumen.palaumen.model.Order;
import kz.batyrbek.palaumen.palaumen.model.Users;
import kz.batyrbek.palaumen.palaumen.repository.CartRepository;
import kz.batyrbek.palaumen.palaumen.repository.CustomerRepository;
import kz.batyrbek.palaumen.palaumen.repository.OrderRepository;
import kz.batyrbek.palaumen.palaumen.repository.UserRepository;
import kz.batyrbek.palaumen.palaumen.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/toOrder")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CartRepository cartRepository;
    private final CartItemMapper cartItemMapper;
    private final CartManager cartManager;
    private final OrderRepository orderRepository;
    private final UserRepository usersRepository;
    private final CustomerRepository customerRepository;

    @GetMapping
    public List<OrderDto> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderDto> checkout(@RequestBody OrderDto orderDto) {
        try{
            List<CartItem> cartItems = cartManager.getCartItems();

            Order order = new Order();
            order.setOrderTime(LocalDateTime.now());
            order.setTotalPrice(BigDecimal.ZERO);
            order.setStatus(Order.OrderStatus.NEW);
            order.setCommentary(orderDto.getCommentary());

            Order savedOrder = orderRepository.save(order);

            Users user = new Users();
            user.setFullname(user.getFullname());
            user.setEmail(user.getEmail());
            user.setPhone(user.getPhone());

            Users savedUser = usersRepository.save(user);

            Customer customer = new Customer();
            customer.setUsers(savedUser);

            Customer savedCustomer = customerRepository.save(customer);

            savedOrder.setCustomer(savedCustomer);

            // Создание нового списка cartItems для сохранения в таблице cartItems
            List<CartItem> savedCartItems = new ArrayList<>();

            // Сохранение каждого товара из корзины в таблице cartItems и установка связи с сохраненным заказом
            for (CartItem cartItem : cartItems) {
                CartItem savedCartItem = new CartItem();
                savedCartItem.setFood(cartItem.getFood());
                savedCartItem.setPrice(cartItem.getPrice());
                savedCartItem.setQuantity(cartItem.getQuantity());
                savedCartItem.setWeight(cartItem.getWeight());
                savedCartItem.setOrder(savedOrder);
                savedCartItems.add(savedCartItem);
            }
            cartRepository.saveAll(savedCartItems);
            List<CartItemDto> savedCartItemDto = cartItemMapper.toDtoList(savedCartItems);

            BigDecimal totalPrice = savedCartItemDto.stream()
                    .map(item -> BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            savedOrder.setTotalPrice(totalPrice);

            // Преобразование сохраненного заказа в OrderDto с помощью OrderMapper
            OrderDto savedOrderDto = orderMapper.toDto(orderRepository.save(savedOrder));
            savedOrderDto.setItems(savedCartItemDto);


            cartManager.clearCart();
            return ResponseEntity.ok(savedOrderDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

