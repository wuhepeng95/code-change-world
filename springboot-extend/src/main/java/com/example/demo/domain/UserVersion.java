package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserVersion {
    //    @TableId(value = "ID_STR", type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @Version
    private Integer version;
}