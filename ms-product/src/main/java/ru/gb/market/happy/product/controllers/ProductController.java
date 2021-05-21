package ru.gb.market.happy.product.controllers;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.happy.core.exceptions.MessageError;
import ru.gb.market.happy.router.dto.OrderItemDto;
import ru.gb.market.happy.router.dto.ProductDto;
import ru.gb.market.happy.product.model.Product;
import ru.gb.market.happy.product.repositories.specifications.ProductSpecifications;
import ru.gb.market.happy.product.services.ProductService;
import ru.gb.market.happy.router.feignclients.OrderFeignClient;
import ru.gb.market.happy.router.feignclients.ProductFeignClient;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final OrderFeignClient orderFeignClient;

    @GetMapping("/hello")
    public String hello(){
        return "hello Product";
    }

    @RequestMapping("/getAllOrdersByProduct/{id}")
    public List<OrderItemDto> getAllOrdersByProductId(@PathVariable Long id){
        return orderFeignClient.getAllOrderItemsByProductId(id);
    }

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }

        return productService.findAll(ProductSpecifications.build(params), page, 10);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id).orElseThrow(() -> new MessageError());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void updateProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
