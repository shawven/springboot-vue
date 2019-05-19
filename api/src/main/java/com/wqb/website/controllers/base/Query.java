package com.wqb.website.controllers.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @author Shoven
 * @since 2019-05-19 23:56
 */
public class Query<T> extends Page<T> {

    private T like;

    private T gt;

    private T lt;

    public T getLike() {
        return like;
    }

    public void setLike(T like) {
        this.like = like;
    }

    public T getGt() {
        return gt;
    }

    public void setGt(T gt) {
        this.gt = gt;
    }

    public T getLt() {
        return lt;
    }

    public void setLt(T lt) {
        this.lt = lt;
    }
}
