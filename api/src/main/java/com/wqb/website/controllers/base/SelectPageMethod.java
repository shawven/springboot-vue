package com.wqb.website.controllers.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqb.website.supports.util.ResponseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Shoven
 * @since 2019-05-17 15:48
 */
public interface SelectPageMethod<T> extends ControllerMethod<T> {

    /**
     * 查询分页记录
     *
     * @param page 分页对象
     * @param entity 实体类
     * @return
     */
    @GetMapping
    default ResponseEntity selectPage(Page<T> page, T entity){
        IPage pageData = getBaseService().page(page, entity);
        if (page.getTotal() == 0) {
            return ResponseUtils.ok("暂无数据！", pageData);
        }
        return ResponseUtils.ok(pageData);
    }

}
