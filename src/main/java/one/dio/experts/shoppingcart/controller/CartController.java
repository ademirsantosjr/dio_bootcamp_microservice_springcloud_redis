package one.dio.experts.shoppingcart.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.dio.experts.shoppingcart.model.Cart;
import one.dio.experts.shoppingcart.model.Item;
import one.dio.experts.shoppingcart.repository.CartRepository;

@RestController
@RequestMapping("/carts")
public class CartController {
    
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/{id}")
    public Cart addItem(@PathVariable int id, @RequestBody Item item) {        
        Optional<Cart> savedCart = cartRepository.findById(id);
        
        Cart cart;
        
        if (savedCart.equals(Optional.empty())) {
            cart = new Cart(id);
        } else {
            cart = savedCart.get();
        }
        
        cart.getItems().add(item);
        
        return cartRepository.save(cart);
    }

    @GetMapping("/{id}")
    public Cart findById(@PathVariable int id) {        
        return cartRepository.findById(id)
                             .orElseThrow(
                                 () -> new NoSuchElementException());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        cartRepository.deleteById(id);
    }
}
