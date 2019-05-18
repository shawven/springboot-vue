package com.wqb.website.services;

import com.wqb.website.domains.Partner;
import com.wqb.website.services.base.BaseService;

/**
 * <p>
 * 加盟合作 服务类
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
public interface PartnerService extends BaseService<Partner> {

    /**
     * 保存合作伙伴信息
     *
     * @param partner
     */
    void saveCopartnerInfo(Partner partner);
}
