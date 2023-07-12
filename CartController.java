package kz.batyrbek.palaumen.palaumen.api;

import kz.batyrbek.palaumen.palaumen.component.CartManager;
import kz.batyrbek.palaumen.palaumen.dto.FoodDto;
import kz.batyrbek.palaumen.palaumen.mapper.FoodMapper;
import kz.batyrbek.palaumen.palaumen.model.CartItem;
import kz.batyrbek.palaumen.palaumen.repository.OrderRepository;
import kz.batyrbek.palaumen.palaumen.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartManager cartManager;
    private final OrderRepository orderRepository;
    private final FoodMapper foodMapper;
    private final FoodService foodService;

    @PostMapping("/add_to_cart")
    @ResponseBody
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem newItem) {
        cartManager.addToCart(newItem);
        return ResponseEntity.ok(newItem);
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> viewCartItems() {
        List<CartItem> cartItems = cartManager.getCartItems();

        for (CartItem item : cartItems) {
            Long foodId = item.getFood().getId();
            FoodDto foodDto = foodService.getFood(foodId);
            item.setFood(foodMapper.toEntity(foodDto));
        }

        if (cartItems != null && !cartItems.isEmpty()) {
            return ResponseEntity.ok(cartItems);
        } else {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> removeFromCart(@RequestBody CartItem item) {
        cartManager.removeFromCart(item);
        return ResponseEntity.ok("Товар успешно удален из корзины");
    }
}

