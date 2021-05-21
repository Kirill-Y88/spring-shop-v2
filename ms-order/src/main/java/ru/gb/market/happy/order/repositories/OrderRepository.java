package ru.gb.market.happy.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.happy.order.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUserId (Long id);
}
