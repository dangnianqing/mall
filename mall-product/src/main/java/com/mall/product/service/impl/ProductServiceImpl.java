package com.mall.product.service.impl;

import com.mall.common.service.impl.IServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.mall.product.entity.Product;
import com.mall.product.mapper.ProductMapper;
import com.mall.product.service.ProductService;

@Service
public class ProductServiceImpl extends IServiceImpl<ProductMapper, Product> implements ProductService {




}
