package com.wqb.website.services;

/**
 * @author Shoven
 * @since 2019-05-17 14:08
 */
public interface CloudAccountRpcService {

    /**
     * 根据手机号检查账号状态
     * 0：可试用
     * 1：正式用户
     * 2：被禁用
     * 3：试用已过期
     * @param phone
     * @return
     */
    int checkAccountStatusByPhone(String phone);
}
