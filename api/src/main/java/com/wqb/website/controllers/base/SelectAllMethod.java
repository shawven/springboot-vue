package com.wqb.website.controllers.base;

import com.wqb.website.supports.util.ResponseUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Shoven
 * @since 2019-05-17 15:38
 */
public interface SelectAllMethod<T> extends ControllerMethod<T> {

    /**
     * 查询所有记录
     *
     * @return
     */
    @GetMapping("/all")
    default ResponseEntity selectAll(T entity) {
        List<T> list = getBaseService().list(entity);

        if (CollectionUtils.isEmpty(list)) {
            return ResponseUtils.ok("暂无数据！");
        }

        return ResponseUtils.ok("获取数据成功！", list);
    }
}
