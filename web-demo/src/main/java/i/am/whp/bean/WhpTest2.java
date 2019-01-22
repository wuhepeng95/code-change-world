package i.am.whp.bean;

import java.util.Date;

/**
 * Created by whp on 2019-01-21
 */
public class WhpTest2 {
    public int id;
    public String message;
    public Date createTime;
    public String newFiled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNewFiled() {
        return newFiled;
    }

    public void setNewFiled(String newFiled) {
        this.newFiled = newFiled;
    }

    @Override
    public String toString() {
        return "WhpTest2{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                ", newFiled='" + newFiled + '\'' +
                '}';
    }
}
