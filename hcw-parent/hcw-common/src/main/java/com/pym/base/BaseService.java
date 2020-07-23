package com.pym.base;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    T queryById(Long id);
    boolean update(T entity);
    boolean create(T entity);
    boolean delete(Long id);
    PageInfo<T> query(Map<String, Object> map);
}
