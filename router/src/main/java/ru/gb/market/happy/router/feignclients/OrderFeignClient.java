package ru.gb.market.happy.router.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.market.happy.router.dto.OrderItemDto;

import java.util.List;

@FeignClient(value ="ms-order" , path = "/order")
public interface OrderFeignClient {

    @GetMapping("/api/v1/orders/orderItems/productId/{id}")
    public List<OrderItemDto> getAllOrderItemsByProductId (@PathVariable Long id);

}
