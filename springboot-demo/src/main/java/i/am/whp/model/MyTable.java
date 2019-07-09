package i.am.whp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import i.am.whp.model.enums.Status;

import java.io.Serializable;
import java.util.Date;

public class MyTable implements Serializable {

    private Integer id;

    private String name;

    private Status status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}



