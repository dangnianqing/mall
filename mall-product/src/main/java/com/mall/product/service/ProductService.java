package com.mall.product.service;


import com.mall.common.service.IService;
import com.mall.product.entity.Product;
import com.mall.product.excel.ProductExcel;

import java.util.List;

public interface ProductService extends IService<Product> {


    Boolean updateLock(String s);

    List<ProductExcel> selectExcel();
}
