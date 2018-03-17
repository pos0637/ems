package com.furongsoft.base.services;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.base.exceptions.BaseException;

import java.io.Serializable;

/**
 * 基础服务
 *
 * @param <T> 类型
 * @author Alex
 */
public abstract class BaseServices<T> {
    private final BaseMapper<T> baseMapper;

    protected BaseServices(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 获取
     *
     * @param id 索引
     * @return 对象
     * @throws BaseException 异常
     */
    public T get(Serializable id) throws BaseException {
        return baseMapper.selectById(id);
    }

    /**
     * 新增
     *
     * @param object 对象
     * @throws BaseException 异常
     */
    public void add(T object) throws BaseException {
        baseMapper.insert(object);
    }

    /**
     * 更新
     *
     * @param object 对象
     * @throws BaseException 异常
     */
    public void edit(T object) throws BaseException {
        baseMapper.updateById(object);
    }

    /**
     * 删除
     *
     * @param id 对象索引
     * @throws BaseException 异常
     */
    public void del(Serializable id) throws BaseException {
        baseMapper.deleteById(id);
    }
}
