package com.wqb.website.domains.ad;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 广告位置
 * </p>
 *
 * @author lxb
 * @since 2019-05-16
 */
@TableName("advertising_site")
public class AdvertisingSite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 广告位名称
     */
    private String name;

    /**
     * 所属平台：0财税官网，1云服务软件，2商城
     */
    private Integer platform;

    /**
     * 宽度
     */
    private Double width;

    /**
     * 高度
     */
    private Double height;

    /**
     * 备注
     */
    private String remark;

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
    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AdvertisingSite{" +
        "id=" + id +
        ", name=" + name +
        ", platform=" + platform +
        ", width=" + width +
        ", height=" + height +
        ", remark=" + remark +
        "}";
    }
}
