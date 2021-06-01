package ru.gb.market.happy.order.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.happy.core.interfaces.ITokenService;
import ru.gb.market.happy.core.model.UserInfo;
import ru.gb.market.happy.order.services.CartService;
import ru.gb.market.happy.router.dto.CartDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private CartService cartService;

    //+
    @PostMapping
    public UUID createCart(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token){
        if(token == null){
            return cartService.getCartForUser(null, null);
        }
        UserInfo userInfo = tokenService.parseToken(token);
        return cartService.getCartForUser(userInfo.getUserId(), null);
    }

    @GetMapping("{uuid}")
    public CartDto getCurrentCart(@PathVariable UUID uuid) {
        return cartService.findById(uuid);
    }

    //-+
    @PostMapping("/add")
    public void addProductToCart(@RequestParam(name = "uuid") UUID uuid, @RequestParam(name = "product_id") Long productId) {
        cartService.addToCart(uuid, productId);
    }

    //+
    @GetMapping("/allCarts")
    public List<CartDto> findAllCarts(){
        return cartService.findAllCarts();
    }

    @PostMapping("/clear")
    public void clearCart(@RequestParam UUID uuid) {
        cartService.clearCart(uuid);
    }


}
