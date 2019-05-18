package com.wqb.website.services.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Shoven
 * @date 2018-11-08 10:23
 */
public abstract class BaseServiceImpl<T> extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T> {
    @Override
    public boolean remove(T entity) {
        return remove(new QueryWrapper<>(entity));
    }

    @Override
    public boolean update(T entity, T condition) {
        return update(entity, new UpdateWrapper<>(condition));
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> idList) {
        return (List<T>)super.listByIds(idList);
    }

    @Override
    public List<T> listByMap(Map<String, Object> columnMap) {
        return (List<T>)super.listByMap(columnMap);
    }

    @Override
    public T getOne(T entity)  {
        return getOne(entity, false);
    }

    @Override
    public T getOne(T entity, boolean throwable) {
        return getOne(new QueryWrapper<>(entity), throwable);
    }

    @Override
    public Map<String, Object> getMap(T entity) {
        return getMap(new QueryWrapper<>(entity));
    }

    @Override
    public Object getObj(T entity) {
        return getObj(new QueryWrapper<>(entity), Function.identity());
    }

    @Override
    public int count(T entity) {
        return count(new QueryWrapper<>(entity));
    }

    @Override
    public List<T> list(T entity) {
        return list(new QueryWrapper<>(entity));
    }

    @Override
    public IPage<T> page(IPage<T> page, T entity) {
        return page(page, new QueryWrapper<>(entity));
    }

    @Override
    public List<Map<String, Object>> listMaps(T entity) {
        return listMaps(new QueryWrapper<>(entity));
    }

    @Override
    public List<Object> listObjs(T entity) {
        return listObjs(new QueryWrapper<>(entity));
    }

    @Override
    public <R> List<R> listObjs(T entity, Function<? super Object, R> mapper) {
        return listObjs(new QueryWrapper<>(entity), mapper);
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<T> page, T entity) {
        return pageMaps(page, new QueryWrapper<>(entity));
    }
}
