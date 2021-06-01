package ru.gb.market.happy.router.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId;
    private int price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
