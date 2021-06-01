package ru.gb.market.happy.order.services;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.happy.order.model.Cart;
import ru.gb.market.happy.order.model.CartItem;
import ru.gb.market.happy.order.repositories.CartRepository;
import ru.gb.market.happy.router.dto.CartDto;
import ru.gb.market.happy.router.dto.ProductDto;
import ru.gb.market.happy.router.feignclients.ProductFeignClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public void addToCart(UUID cartId, Long productId) {
        CartDto cartDto = findById(cartId);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        CartItem cartItem = cart.getItemByProductId(productId);
        if (cartItem != null) {
            cartItem.incrementQuantity();
            cart.recalculate();
            return;
        }
        ProductDto productDto = productFeignClient.findProductById(productId);
        cart.add(new CartItem(productDto));
    }

    @Transactional
    public void clearCart(UUID cartId) {
        CartDto cartDto = findById(cartId);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        cart.clear();
    }

    @Transactional
    public UUID getCartForUser(Long userId, UUID cartUuid) {
        if (userId != null && cartUuid != null) {
            CartDto cartDto = findById(cartUuid);
            Cart cart = modelMapper.map(cartDto, Cart.class);
            Optional<Cart> oldCart = findByUserId(userId);
            if (oldCart.isPresent()) {
                cart.merge(oldCart.get());
                cartRepository.delete(oldCart.get());
            }
            cart.setUserId(userId);
        }
        if (userId == null) {
            Cart cart = (Cart)cartRepository.save(new Cart());
            return cart.getId();
        }
        Optional<Cart> cart = findByUserId(userId);
        if (cart.isPresent()) {
            return cart.get().getId();
        }
        Cart newCart = new Cart();
        newCart.setUserId(userId);
        cartRepository.save(newCart);
        return newCart.getId();
    }

    public List<CartDto> findAllCarts(){
        return cartRepository.findAll().stream().map(this::CartToDto).collect(Collectors.toList());
    }

    public CartDto findById(UUID id) {
        return modelMapper.map(cartRepository.findById(id).get(), CartDto.class);
    }

    public Optional<Cart> findByUserId(Long id) {
        return cartRepository.findByUserId(id);
    }

    public CartDto CartToDto (Cart cart){
        return modelMapper.map(cart, CartDto.class);
    }

}
