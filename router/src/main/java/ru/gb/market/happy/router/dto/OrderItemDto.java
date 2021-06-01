package ru.gb.market.happy.router.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productTitle;
    private int price;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
