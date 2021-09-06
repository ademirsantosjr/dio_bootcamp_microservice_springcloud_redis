package one.dio.experts.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import one.dio.experts.shoppingcart.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer>{
    
}