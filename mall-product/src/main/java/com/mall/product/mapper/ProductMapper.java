package com.mall.product.mapper;

import com.mall.common.mapper.IMapper;


import com.mall.product.entity.Product;
import com.mall.product.excel.ProductExcel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper extends IMapper<Product> {

    List<ProductExcel> selectExcel();
}