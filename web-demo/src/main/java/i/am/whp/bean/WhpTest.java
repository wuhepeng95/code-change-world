package i.am.whp.bean;

import java.util.Date;

/**
 * Created by whp on 2019-01-21
 */
public class WhpTest {
    public int id;
    public String message;
    public Date createTime;

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
}
