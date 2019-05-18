package com.wqb.website.controllers;


import com.wqb.website.controllers.base.DeleteMethod;
import com.wqb.website.controllers.base.InsertMethod;
import com.wqb.website.controllers.base.SelectPageMethod;
import com.wqb.website.domains.TrialCustomer;
import com.wqb.website.services.TrialCustomerService;
import com.wqb.website.services.base.BaseService;
import com.wqb.website.supports.util.ResponseUtils;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
@RestController
@RequestMapping("/trialCustomer")
public class TrialCustomerController implements SelectPageMethod<TrialCustomer>, DeleteMethod<TrialCustomer> {

    @Autowired
    private TrialCustomerService trialCustomerService;


    @GetMapping("/status")
    public ResponseEntity checkTriedStatus(@Valid TrialCustomer trialCustomer) {
        Map map = trialCustomerService.savePurposeOfTryOut(trialCustomer);
        return ResponseUtils.ok(map);
    }

    /**
     * 根据主键id更新记录
     *
     * @param entity 实体类
     * @return
     */
    @PutMapping
    public ResponseEntity update(TrialCustomer entity) {
        entity.setUpdateTime(new Date());
        if (!trialCustomerService.updateById(entity)) {
            return ResponseUtils.unprocesable("更新失败！", entity);
        }
        return ResponseUtils.created("更新成功！", entity);
    }


    @Override
    public BaseService<TrialCustomer> getBaseService() {
        return trialCustomerService;
    }
}
