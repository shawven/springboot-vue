package com.wqb.website.controllers;


import com.wqb.website.controllers.base.BaseController;
import com.wqb.website.controllers.base.DeleteMethod;
import com.wqb.website.controllers.base.SelectPageMethod;
import com.wqb.website.domains.Partner;
import com.wqb.website.domains.TrialCustomer;
import com.wqb.website.services.PartnerService;
import com.wqb.website.services.base.BaseService;
import com.wqb.website.supports.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * <p>
 * 加盟合作 前端控制器
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
@RestController
@RequestMapping("/partner")
public class PartnerController implements SelectPageMethod<Partner>, DeleteMethod<Partner> {

    @Autowired
    private PartnerService partnerService;

    @PostMapping("contact")
    public ResponseEntity saveCopartnerInfo(@Valid Partner partner) {
        partnerService.saveCopartnerInfo(partner);
        return ResponseUtils.ok("保存信息成功");
    }

    /**
     * 根据主键id更新记录
     *
     * @param entity 实体类
     * @return
     */
    @PutMapping
    public ResponseEntity process(Partner entity) {
        entity.setUpdateTime(new Date());
        if (!partnerService.updateById(entity)) {
            return ResponseUtils.unprocesable("更新失败！", entity);
        }
        return ResponseUtils.created("更新成功！", entity);
    }
}
