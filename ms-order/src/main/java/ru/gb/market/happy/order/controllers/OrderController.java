package ru.gb.market.happy.order.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.happy.order.services.OrderService;
import ru.gb.market.happy.router.dto.OrderDto;
import ru.gb.market.happy.router.dto.OrderItemDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public List<OrderDto> getAllOrders(){
        return orderService.findAllOrder();
    }

    @GetMapping("/usersOrder/{id}")
    public List<OrderDto> getOrdersByUserId (@PathVariable Long id){
        return orderService.findAllOrdersByUserId(id);
    }

    @GetMapping("/order/{id}")
    public Optional<OrderDto> getOrderByOrderId (@PathVariable Long id){
        return orderService.findOrderByOrderId(id);
    }


    @GetMapping("/orderItems")
    public List<OrderItemDto>  getAllOrderItems (){
        return orderService.findAllOrderItems();
    }

    @GetMapping("/orderItems/{id}")
    public List<OrderItemDto>  getAllOrderItemsByOrderId (@PathVariable Long id){
        return orderService.findAllOrderItemsByOrderId(id);
    }

    @GetMapping("/orderItems/productId/{id}")
    public List<OrderItemDto> getAllOrderItemsByProductId (@PathVariable Long id){
        return orderService.findAllOrderItemsByProductId(id);
    }

}
