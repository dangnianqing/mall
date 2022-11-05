package com.mall.product.controller;

import com.mall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Object find() {
        int a=111;
        System.out.println(a);
        return productService.selectPage(1,10,null);
    }
}
