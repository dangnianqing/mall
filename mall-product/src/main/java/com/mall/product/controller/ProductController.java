package com.mall.product.controller;

import com.mall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;

/**
 * @Author ：zhanghaijun
 * @Date ：Created in  2022/11/4 14:55
 * @Description：
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/find")
    public Boolean find() {
        return productService.updateLock("1");
    }
}
