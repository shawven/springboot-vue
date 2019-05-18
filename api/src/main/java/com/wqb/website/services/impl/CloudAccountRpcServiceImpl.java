package com.wqb.website.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.wqb.website.services.CloudAccountRpcService;
import com.wqb.website.supports.exceptions.BizException;
import com.wqb.website.supports.exceptions.WqbException;
import com.wqb.website.supports.property.CloudAccountProperties;
import com.wqb.website.supports.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Shoven
 * @since 2019-05-17 14:12
 */
@Slf4j
@Service
public class CloudAccountRpcServiceImpl implements CloudAccountRpcService {

    @Autowired
    private CloudAccountProperties cloudAccountProperties;


    @Override
    public int checkAccountStatusByPhone(String phone) {
        String rsp;
        try {
            rsp = HttpClientUtils.get(cloudAccountProperties.getCheckTriedUrl(), "?phone=" + phone);
            log.info("检查试用账号状态返回：" + rsp);
        } catch (IOException e) {
            throw new WqbException("检查试用账号状态失败" + e.getMessage());
        }

        JSONObject jsonObject = JSONObject.parseObject(rsp);
        Integer resultCode = jsonObject.getInteger("resultCode");

        if (resultCode == null) {
            throw new WqbException("检查试用账号状态没有返回resultCode");
        }

        return resultCode;
    }
}
