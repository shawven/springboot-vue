package com.wqb.website.domains.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 平台分类表
 * </p>
 *
 * @author lxb
 * @since 2019-05-16
 */
@TableName("platform_type")
public class PlatformType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 栏目ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 栏目名称
     */
    private String name;

    /**
     * 所属平台：0财税官网，1云服务软件，2商城
     */
    private Integer platform;

    /**
     * 状态：0禁用，1启用
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PlatformType{" +
        "id=" + id +
        ", name=" + name +
        ", platform=" + platform +
        ", status=" + status +
        "}";
    }
}
