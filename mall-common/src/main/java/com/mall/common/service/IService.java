package com.mall.common.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IService<T> {

    T selectById(String id);

    boolean insert(T t);

    boolean update(T t);

    boolean deleteById(String id);

    boolean insertBach(List<T> list);

    boolean updateBach(List<T> list);

    boolean insertOrUpdate(T t);

    PageInfo<T> selectPage(Integer pageNum, Integer pageSize, Map<String, Objects> map);
}
