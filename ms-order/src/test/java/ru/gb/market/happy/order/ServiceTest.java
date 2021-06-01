package ru.gb.market.happy.order;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.gb.market.happy.core.config.RedisConfig;
import ru.gb.market.happy.order.model.Order;
import ru.gb.market.happy.order.repositories.OrderItemRepository;
import ru.gb.market.happy.order.repositories.OrderRepository;
import ru.gb.market.happy.order.services.OrderService;
import ru.gb.market.happy.router.dto.OrderDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {OrderService.class, RedisConfig.class})
public class ServiceTest {

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    OrderItemRepository orderItemRepository;




@Test
    public void findOrderByOrderIdTest(){
    Order order = new Order();
    order.setId(1L);
    order.setUserId(1L);
    order.setPrice(100);

    Mockito.doReturn(Optional.of(order)).when(orderRepository).findById(1L);

    Optional<OrderDto> orderDtoTest2 = orderService.findOrderByOrderId(1L);
    Assertions.assertEquals(100,orderDtoTest2.get().getPrice());

}

@Test
    public void findAllOrderTest(){
    Order order3 = new Order();
    order3.setId(1L);
    order3.setUserId(1L);
    order3.setPrice(100);

    Order order2 = new Order();
    order2.setId(2L);
    order2.setUserId(4L);
    order2.setPrice(200);

    List<Order> orderList = new ArrayList<>();
    orderList.add(order3);
    orderList.add(order2);

    Mockito.doReturn(orderList).when(orderRepository).findAll();

    List<OrderDto> orderDtoList =  orderService.findAllOrder();
    Assertions.assertEquals(2,orderDtoList.size());


}



}
