package com.mall.product.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.mall.common.exception.ExcelException;
import com.mall.common.utils.ExcelUtil;
import com.mall.product.entity.Product;
import com.mall.product.excel.ProductExcel;
import com.mall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
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
    @GetMapping("/excel")
    public void excel(HttpServletResponse response) throws ExcelException {
        List<ProductExcel>list=productService.selectExcel();
         ExcelUtil.writeExcel(response,list,"商品导出","sheet1", ExcelTypeEnum.XLSX);
    }

    @PostMapping("/find")
    public Boolean find() {
        Product product = new Product();
        product.setId("1111111111");
        product.setProductNumber("DQ19X8009E1111111111");
        product.setProductName("自动扣G字扣D1137");
        product.setCategoryClass1("20");
        product.setCategoryClass2("2001");
        product.setCategoryClass3("200105");
        product.setCategoryClass4("200105001");
        product.setYearCode("2019");
        product.setSeasonCode("X");
        product.setTrademarkCode("DD");
        product.setTrademarkName("大东");
        product.setSupplierCode("DD");
        product.setSupplierName("大东");
        return productService.insert(product);
    }
}
