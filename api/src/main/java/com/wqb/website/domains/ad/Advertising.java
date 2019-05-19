package com.wqb.website.domains.ad;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 广告
 * </p>
 *
 * @author lxb
 * @since 2019-05-16
 */
@TableName("advertising")
public class Advertising implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 广告名称
     */
    private String name;

    /**
     * 广告位置id
     */
    private Integer siteId;

    /**
     * 图片路径
     */
    private String picPath;

    /**
     * 链接地址
     */
    private String link;

    /**
     * 序号
     */
    private Integer sortNo;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 是否启用：0不启用，1启用
     */
    private Integer isEnabled;

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

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        return "Advertising{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", siteId=" + siteId +
                ", picPath='" + picPath + '\'' +
                ", link='" + link + '\'' +
                ", sortNo=" + sortNo +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
