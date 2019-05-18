package com.wqb.website.controllers.base;

import com.wqb.website.services.base.BaseService;
import com.wqb.website.supports.util.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Shoven
 * @since 2019-05-17 15:46
 */
public interface DeleteMethod<T> extends ControllerMethod<T> {

    /**
     * 根据id删除记录
     *
     * @param id 主键id
     * @param ids 主键id字符串 (ids=11,22,33)
     * @return
     */
    @DeleteMapping
    default ResponseEntity delete(@RequestParam(value = "id", required = false) Serializable id,
                                 @RequestParam(value = "ids", required = false) String ids) {
        BaseService<T> service = getBaseService();
        if (id != null) {
            if (!service.removeById(id)) {
                return ResponseUtils.unprocesable("删除失败！");
            }
            return ResponseUtils.noContent();
        }

        if (StringUtils.isNotEmpty(ids)) {
            String[] arr = StringUtils.split(ids, ",");
            if (!service.removeByIds(Arrays.asList(arr))) {
                return ResponseUtils.unprocesable("批量删除失败！");
            }
            return ResponseUtils.noContent();
        }

        return ResponseUtils.unprocesable("没有要删除的记录！");
    }
}
