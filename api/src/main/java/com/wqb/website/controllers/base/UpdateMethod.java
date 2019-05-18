package com.wqb.website.controllers.base;

import com.wqb.website.supports.util.ResponseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;

/**
 * @author Shoven
 * @since 2019-05-17 15:44
 */
public interface UpdateMethod<T> extends ControllerMethod<T> {

    /**
     * 根据主键id更新记录
     *
     * @param entity 实体类
     * @return
     */
    @PutMapping
    default ResponseEntity update(@Valid T entity) {
        if (!getBaseService().updateById(entity)) {
            return ResponseUtils.unprocesable("更新失败！", entity);
        }
        return ResponseUtils.created("更新成功！", entity);
    }
}
