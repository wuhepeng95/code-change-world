package i.am.whp.model;

import java.io.Serializable;
import java.util.Date;

public class PhoneNumberDeviceRelation implements Serializable {

    private Long id;

    private Long phoneNumberId;

    private Long phoneDeviceId;

    private Long createUserId;

    private Integer isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setPhoneNumberId(Long phoneNumberId) {
        this.phoneNumberId = phoneNumberId;
    }

    public Long getPhoneNumberId() {
        return this.phoneNumberId;
    }

    public void setPhoneDeviceId(Long phoneDeviceId) {
        this.phoneDeviceId = phoneDeviceId;
    }

    public Long getPhoneDeviceId() {
        return this.phoneDeviceId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() {
        return this.createUserId;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return this.isDeleted;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }


}



