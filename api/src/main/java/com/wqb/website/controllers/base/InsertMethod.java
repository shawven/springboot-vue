package com.wqb.website.controllers.base;

import com.wqb.website.supports.util.ResponseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author Shoven
 * @since 2019-05-17 15:43
 */
public interface InsertMethod<T> extends ControllerMethod<T> {

    /**
     * 添加记录
     *
     * @param entity 实体类
     * @return
     */
    @PostMapping
    default ResponseEntity insert(@Valid T entity) {
        if (!getBaseService().save(entity)) {
            return ResponseUtils.unprocesable("添加失败！", entity);
        }
        return ResponseUtils.created("添加成功！", entity);
    }
}
