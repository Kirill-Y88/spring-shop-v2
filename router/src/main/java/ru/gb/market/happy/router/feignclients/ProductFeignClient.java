package ru.gb.market.happy.router.feignclients;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value ="ms-product" , path = "/happy")
public interface ProductFeignClient {

    @GetMapping("/api/v1/products/hello")
    public String hello();

}
