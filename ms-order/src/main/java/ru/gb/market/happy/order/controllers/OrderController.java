package ru.gb.market.happy.order.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.happy.core.interfaces.ITokenService;
import ru.gb.market.happy.core.model.UserInfo;
import ru.gb.market.happy.order.model.Order;
import ru.gb.market.happy.order.services.CartService;
import ru.gb.market.happy.order.services.OrderService;
import ru.gb.market.happy.router.dto.OrderDto;
import ru.gb.market.happy.router.dto.OrderItemDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam UUID cartUuid, @RequestParam String address) {
        UserInfo userInfo = tokenService.parseToken(token);
        Order order = orderService.createFromUserCart(userInfo.getUserId(), cartUuid);
        cartService.clearCart(cartUuid);
        return modelMapper.map(order, OrderDto.class);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        UserInfo userInfo = tokenService.parseToken(token);
        return orderService.findAllOrdersByUserId(userInfo.getUserId());
    }

    //+
    @GetMapping("/order")
    public List<OrderDto> getAllOrders(){
        return orderService.findAllOrder();
    }
    //+
    @GetMapping("/usersOrder/{id}")
    public List<OrderDto> getOrdersByUserId (@PathVariable Long id){
        return orderService.findAllOrdersByUserId(id);
    }
    //+
    @GetMapping("/order/{id}")
    public Optional<OrderDto> getOrderByOrderId (@PathVariable Long id){
        return orderService.findOrderByOrderId(id);
    }

    //+
    @GetMapping("/orderItems")
    public List<OrderItemDto>  getAllOrderItems (){
        return orderService.findAllOrderItems();
    }
//---------------
    @GetMapping("/orderItems/{id}")
    public List<OrderItemDto>  getAllOrderItemsByOrderId (@PathVariable Long id){
        return orderService.findAllOrderItemsByOrderId(id);
    }
    //+
    @GetMapping("/orderItems/productId/{id}")
    public List<OrderItemDto> getAllOrderItemsByProductId (@PathVariable Long id){
        return orderService.findAllOrderItemsByProductId(id);
    }

}
