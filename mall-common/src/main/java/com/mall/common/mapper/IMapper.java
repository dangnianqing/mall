package com.mall.common.mapper;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IMapper<T> {

    T selectById(String id);

    boolean insert(T t);

    boolean update(T t);

    boolean deleteById(String id);

    boolean insertBach(@Param("list") List<T> list);

    boolean updateBach(@Param("list") List<T> list);

    boolean insertOrUpdate(T t);

    List<T> selectPage(@Param("map") Map<String, Objects> map);

}
