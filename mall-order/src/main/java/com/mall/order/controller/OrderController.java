package com.mall.order.controller;

import com.mall.feign.product.ProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ：zhanghaijun
 * @Date ：Created in  2022/11/4 14:17
 * @Description：
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ProductFeign productFeign;

    @GetMapping("/find")
    public String find() {
        return productFeign.getProduct();
    }
}
