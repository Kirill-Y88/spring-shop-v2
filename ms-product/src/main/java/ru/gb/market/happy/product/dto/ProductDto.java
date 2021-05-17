package ru.gb.market.happy.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.happy.product.model.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;

}
