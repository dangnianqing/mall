package com.mall.product.controller;

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

    @PostMapping("/find")
    public String find() {
        return "product";
    }
}
