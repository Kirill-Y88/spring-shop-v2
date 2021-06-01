package ru.gb.market.happy.order.services;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.market.happy.order.model.Cart;
import ru.gb.market.happy.order.model.Order;
import ru.gb.market.happy.order.model.OrderItem;
import ru.gb.market.happy.order.repositories.OrderItemRepository;
import ru.gb.market.happy.order.repositories.OrderRepository;
import ru.gb.market.happy.router.dto.CartDto;
import ru.gb.market.happy.router.dto.OrderDto;
import ru.gb.market.happy.router.dto.OrderItemDto;
import ru.gb.market.happy.router.feignclients.ProductFeignClient;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private CartService cartService;

    @Transactional
    public Order createFromUserCart(Long userId, UUID cartUuid) {
        CartDto cartDto = cartService.findById(cartUuid);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        Order order = new Order(cart, userId);
        order = orderRepository.save(order);
        return order;
    }


    public List<OrderDto> findAllOrdersByUserId (Long id){
        return  orderRepository.findAllByUserId(id).stream().map(this::OrderToDto).collect(Collectors.toList());
    }

    public List<OrderDto> findAllOrder (){
        return  orderRepository.findAll().stream().map(this::OrderToDto).collect(Collectors.toList());
    }

    public Optional <OrderDto> findOrderByOrderId (Long id){
        return (orderRepository.findById(id)).map(this::OrderToDto);
    }

    @Transactional  //возвращает IllegalArgumentException
    public List<OrderItemDto> findAllOrderItemsByOrderId (Long id){
        return orderItemRepository.findOrderItemsByOrderId(id).stream().map(this::OrderItemToDto).collect(Collectors.toList());
        //return orderItemRepository.findAllByOrderId(id).stream().map(this::OrderItemToDto).collect(Collectors.toList());
    }

    public List<OrderItemDto> findAllOrderItems(){
        return orderItemRepository.findAll().stream().map(this::OrderItemToDto).collect(Collectors.toList());
    }


    public List<OrderItemDto> findAllOrderItemsByProductId(Long id){
        return orderItemRepository.findAllByProductId(id).stream().map(this::OrderItemToDto).collect(Collectors.toList());
    }


    public OrderDto OrderToDto (Order order){
        return modelMapper.map(order, OrderDto.class);
    }

    public Order OrderDtoToOrder (OrderDto orderDto){
        return modelMapper.map(orderDto, Order.class);
    }

    public OrderItemDto OrderItemToDto (OrderItem orderItem){
        return modelMapper.map(orderItem, OrderItemDto.class);
    }

    public OrderItem OrderItemDtoToOrder (OrderItemDto orderItemDto){
        return modelMapper.map(orderItemDto, OrderItem.class);
    }

}
