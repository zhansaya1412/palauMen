//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import kz.batyrbek.palaumen.palaumen.component.CartManager;
//import kz.batyrbek.palaumen.palaumen.model.CartItem;
//import kz.batyrbek.palaumen.palaumen.model.Order;
//import kz.batyrbek.palaumen.palaumen.repository.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@RequestMapping("/cart")
//@RestController
//public class CartControllerTest {
//    @Autowired
//    private CartManager cartManager;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @PostMapping("/add_to_cart")
//    @ResponseBody
//    public ResponseEntity<String> addToCart(@RequestBody CartItem newItem) {
//        cartManager.addToCart(newItem);
//        return ResponseEntity.ok("Товар успешно добавлен в корзину");
//    }
//
////    @PostMapping("/add_to_cart")
////    @ResponseBody
////    public ResponseEntity<String> addToCart(HttpServletRequest request, @RequestBody CartItem newItem) {
////        List<CartItem> cartItems = cartManager.getCartItems();
////        if (cartItems != null) {
////            cartItems.add(newItem);
////        } else {
////            cartItems = new ArrayList<>();
////            cartItems.add(newItem);
////        }
////        return ResponseEntity.ok("Товар успешно добавлен в корзину");
////    }
//
//
//    @GetMapping("/cart")
//    public String showCartItems(HttpServletRequest request, Model model) {
//        List<CartItem> cart = cartManager.getCartItems();
//        if (cart != null && !cart.isEmpty()) {
//            // Данные в корзине присутствуют
//            // Передайте данные в модель для отображения на странице
//            model.addAttribute("cartItems", cart);
//            System.out.println("Содержимое корзины:");
//            for (CartItem item : cart) {
//                System.out.println("id: " + item.getId());
//                System.out.println("Food Name: " + item.getFoodName());
//                System.out.println("Price: " + item.getPrice());
//                System.out.println("Price: " + item.getQuantity());
//            }
//        } else {
//            // Данные в корзине отсутствуют
//            // Обработайте ситуацию, когда корзина пуста
//        }
//
//        return "cart";
//    }
//
////    @GetMapping("/items")
////    public ResponseEntity<List<CartItem>> viewCartItems(HttpServletRequest request) {
////        HttpSession session = request.getSession();
////        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
////        if (cartItems != null && !cartItems.isEmpty()) {
////            return ResponseEntity.ok(cartItems);
////        } else {
////            return ResponseEntity.ok(new ArrayList<>());
////        }
////    }
//
//    @GetMapping("/items")
//    public ResponseEntity<List<CartItem>> viewCartItems() {
//        List<CartItem> cartItems = cartManager.getCartItems();
//        if (cartItems != null && !cartItems.isEmpty()) {
//            return ResponseEntity.ok(cartItems);
//        } else {
//            return ResponseEntity.ok(new ArrayList<>());
//        }
//    }
//
//
//    @DeleteMapping
//    public ResponseEntity<String> removeFromCart(@RequestBody CartItem item) {
//        cartManager.removeFromCart(item);
//        return ResponseEntity.ok("Товар успешно удален из корзины");
//    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<String> checkout() {
//        try {
//            List<CartItem> cartItems = cartManager.getCartItems();
//
//            // Создание нового заказа
//            Order order = new Order();
//            order.setItems(cartItems);
//            order.setOrderTime(LocalDateTime.now());
//            // Вычисление общей стоимости
//            BigDecimal totalPrice = cartItems.stream()
//                    .map(item -> BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//            order.setTotalPrice(totalPrice);
//            order.setStatus(Order.OrderStatus.NEW);
//
//            // Сохранение данных из сессии в таблицу Orders с использованием OrderRepository
//            orderRepository.save(order);
//
//            cartManager.clearCart();
//            return ResponseEntity.ok("Заказ успешно оформлен");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при оформлении заказа");
//        }
//    }
//
//}
//
