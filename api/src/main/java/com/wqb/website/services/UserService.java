package com.wqb.website.services;

import com.wqb.website.domains.User;

/**
 * @author Shoven
 * @since 2019-05-17 16:18
 */
public interface UserService {

    User getCurrentUser();

    /**
     * 通过用户名查找用户信息
     *
     * @return
     */
    User getOneByUserName(String userName);
}
