package com.wqb.website.services.impl;

import com.wqb.website.domains.Partner;
import com.wqb.website.services.PartnerService;
import com.wqb.website.services.base.BaseServiceImpl;
import com.wqb.website.supports.exceptions.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 加盟合作 服务实现类
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
@Service
public class PartnerServiceImpl extends BaseServiceImpl<Partner> implements PartnerService {

    @Override
    public void saveCopartnerInfo(Partner partner) {
        Date now = new Date();
        partner.setStatus(0);
        partner.setCreateTime(now);
        partner.setUpdateTime(now);

        save(partner);
    }
}
