package com.wqb.website.controllers.base;

import com.wqb.website.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shoven
 * @since 2018-11-09
 */
public abstract class BaseController<T> implements SelectAllMethod<T>, SelectOneMethod<T>, SelectPageMethod<T>,
        UpdateMethod<T>, DeleteMethod<T> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    private BaseService<T> service;

    @Override
    public BaseService<T> getBaseService() {
        return service;
    }
}
