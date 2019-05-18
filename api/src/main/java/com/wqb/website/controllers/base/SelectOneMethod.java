package com.wqb.website.controllers.base;

import com.wqb.website.supports.util.ResponseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

/**
 * @author Shoven
 * @since 2019-05-17 15:42
 */
public interface SelectOneMethod<T> extends ControllerMethod<T> {
    /**
     * 根据主键id查找一条记录
     *
     * @param id 主键
     * @return
     */
    @GetMapping("{id}")
    default ResponseEntity selectOne(@PathVariable Serializable id) {
        T t = getBaseService().getById(id);
        if (t == null) {
            return ResponseUtils.notFound("暂无数据！");
        }
        return ResponseUtils.ok("获取数据成功！", t);
    }
}
