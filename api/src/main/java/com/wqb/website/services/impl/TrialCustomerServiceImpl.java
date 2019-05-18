package com.wqb.website.services.impl;

import com.wqb.website.domains.TrialCustomer;
import com.wqb.website.services.CloudAccountRpcService;
import com.wqb.website.services.TrialCustomerService;
import com.wqb.website.services.base.BaseServiceImpl;
import com.wqb.website.supports.exceptions.BizException;
import com.wqb.website.supports.property.CloudAccountProperties;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
@Service
public class TrialCustomerServiceImpl extends BaseServiceImpl<TrialCustomer> implements TrialCustomerService {

    @Autowired
    private CloudAccountRpcService cloudAccountRpcService;

    @Autowired
    private CloudAccountProperties cloudAccountProperties;

    @Override
    public Map savePurposeOfTryOut(TrialCustomer trialCustomer) {
        String phone = trialCustomer.getPhone();

        int i = cloudAccountRpcService.checkAccountStatusByPhone(trialCustomer.getPhone());
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", i);

        switch (i) {
            // 可试用
            case 0:
                trialCustomer.setStatus(0);
                result.put("url", cloudAccountProperties.getTriedLoginUrl());
                break;
            // 正式用户
            case 1:
                trialCustomer.setStatus(1);
                result.put("url", cloudAccountProperties.getPaidLoginUrl());
                break;
            // 被禁用
            case 2:
                trialCustomer.setStatus(2);
                result.put("url", null);
                break;
            // 需要续费
            case 3:
            default:
                trialCustomer.setStatus(2);
                result.put("url", cloudAccountProperties.getBuyCloudAccountUrl());
                break;
        }

        Date now = new Date();
        trialCustomer.setCreateTime(now);
        trialCustomer.setUpdateTime(now);
        trialCustomer.setFollowUp(0);

        save(trialCustomer);

        return result;
    }
}
