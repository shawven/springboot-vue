package com.wqb.website.mappers.ad;

import com.wqb.website.commons.vo.AdvertisingVO;
import com.wqb.website.domains.ad.Advertising;
import com.wqb.website.mappers.base.BaseMapper;

import java.io.Serializable;

/**
 * <p>
 * 广告 Mapper 接口
 * </p>
 *
 * @author lxb
 * @since 2019-05-16
 */
public interface AdvertisingMapper extends BaseMapper<Advertising> {

    AdvertisingVO selectAd(Serializable id);

    AdvertisingVO selectAds();
}
