package com.wqb.website.controllers.base;

import com.wqb.website.services.base.BaseService;

/**
 * @author Shoven
 * @since 2019-05-17 15:40
 */
public interface ControllerMethod<T> {

    /**
     * 获取服务类
     *
     * @return
     */
    BaseService<T> getBaseService();
}
