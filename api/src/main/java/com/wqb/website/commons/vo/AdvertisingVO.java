package com.wqb.website.commons.vo;

import com.wqb.website.domains.ad.Advertising;
import lombok.Data;

@Data
public class AdvertisingVO extends Advertising {

    private static final long serialVersionUID = 1L;


    /**
     * 广告位名称
     */
    private String siteName;

    /**
     * 所属平台：0财税官网，1云服务软件，2商城
     */
    private Integer platform;
}
