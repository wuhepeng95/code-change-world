package i.am.whp.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class PhoneNumberDeviceRelation implements Serializable {

    private Long id;

    private Long phoneNumberId;

    private Long phoneDeviceId;

    private Long createUserId;

    private Integer isDeleted;

    private Date createTime;

    private Date lastUpdateTime;


}



