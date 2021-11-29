package i.am.whp.bean;

import java.util.Date;


/**
 * CREATE TABLE test.my_table (
 * id INT NOT NULL AUTO_INCREMENT,
 * name varchar(100) NULL,
 * status TINYINT NULL,
 * create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
 * primary key(id)
 * )
 * ENGINE=InnoDB
 * DEFAULT CHARSET=utf8mb4;
 */
public class MyTable {

    private Integer id;
    private String name;
    private Integer status;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public MyTable() {
    }

    public MyTable(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "MyTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}



