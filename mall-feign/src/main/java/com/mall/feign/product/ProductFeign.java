package com.mall.feign.product;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "mall-product")
public interface ProductFeign {

    @PostMapping("/product/find")
    String getProduct();
}
