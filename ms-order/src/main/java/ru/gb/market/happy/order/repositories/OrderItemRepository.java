package ru.gb.market.happy.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.market.happy.order.model.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findAllByOrderId (Long id);

    @Query("select i from OrderItem i where i.orderId = ?1")
    List<OrderItem> findOrderItemsByOrderId (Long id);

    List<OrderItem> findAllByProductId(Long id);


}
