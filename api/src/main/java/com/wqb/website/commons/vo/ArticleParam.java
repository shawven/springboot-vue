package com.wqb.website.commons.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
* @Author: lxb
* @Date: 2019-05-16
* @Description: 文章接口参数类
*/
@Data
public class ArticleParam {

    /**
     * 栏目列表IDs
     */
    private Set<Integer> typeIds;

    /**
     * 文章是否启用：0不启用，1启用
     */
    private Integer isEnabled=1;

    /**
     * 平台列表IDs
     */
    private Set<Integer> platformIds;

    /**
     * 文章主题
     */
    private String subject;

    /**
     * 页数
     */
    private long size=10L;

    /**
     * 当前页
     */
    private long current=1L;

    private boolean sortNoDirection;

    private boolean createTimeDirection;

    private Sort sort;

    @Data
    private static class Sort {
        private String sortNo;
        private String createTime;
    }

    public String getSortNoDirection() {
        return StringUtils.isNotBlank(sort.getSortNo()) && sort.getSortNo().equals("desc")
                ? "desc"
                : "asc";
    }

    public String getCreateTimeDirection() {
        return StringUtils.isNotBlank(sort.getCreateTime()) && sort.getCreateTime().equals("asc")
                ? "asc"
                : "desc";
    }
}
