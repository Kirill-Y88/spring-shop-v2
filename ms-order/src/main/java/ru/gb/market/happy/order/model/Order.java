package ru.gb.market.happy.order.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> orderItemList;

    @Column(name = "price")
    private int price;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order(Cart cart, Long userId) {
        this.orderItemList = new ArrayList<>();
        this.userId = userId;
        this.price = cart.getPrice();
        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem(ci);
            oi.setOrderId(this);
            this.orderItemList.add(oi);
        }
    }

}
