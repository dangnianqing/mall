package com.mall.product.mapper;

import com.mall.common.mapper.IMapper;


import com.mall.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends IMapper<Product> {

}