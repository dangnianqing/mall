package com.mall.order.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ：zhanghaijun
 * @Date ：Created in  2022/11/4 14:17
 * @Description：
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/find")
    public String find() {
        return "product";
    }
}
