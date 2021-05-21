package ru.gb.market.happy.order;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.market.happy.order.model.Order;
import ru.gb.market.happy.order.model.OrderItem;
import ru.gb.market.happy.order.repositories.OrderItemRepository;
import ru.gb.market.happy.order.repositories.OrderRepository;
import ru.gb.market.happy.router.dto.OrderItemDto;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoryTest {

@Autowired
    private OrderItemRepository orderItemRepository;

@Autowired
    private OrderRepository orderRepository;

@Test
    public void findAllOrderItemTest(){
    List<OrderItem> orderItemList = orderItemRepository.findAll();
    Assertions.assertEquals(7,orderItemList.size());
}


//и тут этот запрос по внешнему ключу не проходит
@Test
    public void findAllByOrderItemIdTest(){
    List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(1L);
    Assertions.assertEquals(2,orderItemList.size());
}
@Test
    public void findAllOrderItemByProductIdTest(){
    List<OrderItem> orderItemList = orderItemRepository.findAllByProductId(3L);
    Assertions.assertEquals(2,orderItemList.size());
}

@Test
    public void findAllOrderByUserIdTest(){
    List<Order> orderList = orderRepository.findAllByUserId(1L);
    Assertions.assertEquals(2,orderList.size());
}


}
