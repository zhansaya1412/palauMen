package kz.batyrbek.palaumen.palaumen.component;

import jakarta.servlet.http.HttpSession;
import kz.batyrbek.palaumen.palaumen.dto.OrderDto;
import kz.batyrbek.palaumen.palaumen.mapper.FoodMapper;
import kz.batyrbek.palaumen.palaumen.mapper.OrderMapper;
import kz.batyrbek.palaumen.palaumen.model.CartItem;
import kz.batyrbek.palaumen.palaumen.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CartManager {
    private List<CartItem> cartItems;
    private HttpSession session;

    public CartManager(HttpSession session) {
        this.cartItems = new ArrayList<>();
        this.session = session;
        restoreCartFromSession();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void addToCart(CartItem item) {
        cartItems.add(item);
        saveCartToSession();
    }
    public void removeFromCart(CartItem item) {
        cartItems.remove(item);
        saveCartToSession();
    }

    public void updateCartItemQuantity(CartItem item, int quantity) {
        if (cartItems.contains(item)) {
            item.setQuantity(quantity);
            saveCartToSession();
        }
    }

    public void clearCart() {
        cartItems.clear();
        saveCartToSession();
    }

    private void saveCartToSession() {
        session.setAttribute("cartItems", cartItems);
    }

    private void restoreCartFromSession() {
        List<CartItem> storedCartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (storedCartItems != null) {
            cartItems = storedCartItems;
        }
    }

}
