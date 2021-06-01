package ru.gb.market.happy.router.feignclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.happy.router.dto.ProductDto;


@FeignClient(value ="ms-product" , path = "/happy")
public interface ProductFeignClient {

    @GetMapping("/api/v1/products/{id}")
    public ProductDto findProductById(@PathVariable Long id);

    @PostMapping("/api/v1/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveNewProduct(@RequestBody ProductDto product);

    @PutMapping("/api/v1/products")
    public ProductDto updateProduct(@RequestBody ProductDto product);

}
