package ru.gb.market.happy.router.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productTitle;
    private Long price;
    private Long quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
