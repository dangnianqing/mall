package com.mall.product.service.impl;

import com.mall.common.service.impl.IServiceImpl;
import com.mall.common.utils.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import com.mall.product.entity.Product;
import com.mall.product.mapper.ProductMapper;
import com.mall.product.service.ProductService;

@Service
public class ProductServiceImpl extends IServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Boolean updateLock(String s) {
        Product product = this.selectById(s);
        Integer yearCode = Integer.valueOf(product.getYearCode());
        String uuid = UUID.randomUUID().toString();
        RedisLock redisLock = new RedisLock(redisTemplate, product.getId(), uuid);
        try {
            if (redisLock.lock()) {
                if (yearCode > 2000) {
                    product.setYearCode(String.valueOf(yearCode - 1));
                    this.update(product);
                    return true;
                }
            }
        } catch (Exception e) {
            redisLock.unLock();
        } finally {
            redisLock.unLock();
        }
        return false;
    }
}
