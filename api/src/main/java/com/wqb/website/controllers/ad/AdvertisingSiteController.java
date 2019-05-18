package com.wqb.website.controllers.ad;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.wqb.website.controllers.base.BaseController;
import com.wqb.website.domains.ad.AdvertisingSite;

/**
 * <p>
 * 广告位置 前端控制器
 * </p>
 *
 * @author lxb
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/advertisingSite")
public class AdvertisingSiteController extends BaseController<AdvertisingSite> {

}
