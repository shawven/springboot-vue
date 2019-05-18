package com.wqb.website.services;

import com.wqb.website.domains.TrialCustomer;
import com.wqb.website.services.base.BaseService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
public interface TrialCustomerService extends BaseService<TrialCustomer> {

    /**
     * 保存试用意图
     *
     * @param phone
     * @return
     */
    Map savePurposeOfTryOut(TrialCustomer phone);


}
