package ru.gb.market.happy.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "ru.gb.market.happy")
@EnableFeignClients(basePackages = "ru.gb.market.happy.router")
public class MsProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductApplication.class, args);
    }

}
