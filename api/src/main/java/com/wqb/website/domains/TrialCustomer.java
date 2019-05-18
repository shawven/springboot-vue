package com.wqb.website.domains;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lxb
 * @since 2019-05-15
 */
@TableName("trial_customer")
public class TrialCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 联系人
     */
    @NotBlank
    private String contacts;

    /**
     * 联系电话
     */
    @NotBlank
    @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式错误")
    private String phone;

    /**
     * 状态：0试用客户，1正式客户，2已过期
     */
    private Integer status;

    /**
     * 回访状态：0未处理，1已处理
     */
    private Integer followUp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getFollowUp() {
        return followUp;
    }

    public void setFollowUp(Integer followUp) {
        this.followUp = followUp;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TrialCustomer{" +
                "id=" + id +
                ", contacts=" + contacts +
                ", phone=" + phone +
                ", status=" + status +
                ", followUp=" + followUp +
                ", remark=" + remark +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
