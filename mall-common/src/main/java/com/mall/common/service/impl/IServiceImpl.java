package com.mall.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.mapper.IMapper;
import com.mall.common.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IServiceImpl<M extends IMapper<T>, T> implements IService<T> {
    @Resource
    private M baseMapper;

    @Override
    public T selectById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public boolean update(T t) {
        return baseMapper.update(t);
    }

    @Override
    public boolean deleteById(String id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public boolean insertBach(List<T> list) {
        return baseMapper.insertBach(list);
    }

    @Override
    public boolean updateBach(List<T> list) {
        return baseMapper.updateBach(list);
    }

    @Override
    public boolean insertOrUpdate(T t) {
        return baseMapper.insertOrUpdate(t);
    }

    @Override
    public PageInfo<T> selectPage(Integer pageNum, Integer pageSize, Map<String, Objects> map) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<T> pageInfo = new PageInfo<>(baseMapper.selectPage(map));
        return pageInfo;
    }
}
