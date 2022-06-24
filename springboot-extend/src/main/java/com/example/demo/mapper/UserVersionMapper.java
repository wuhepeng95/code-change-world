package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.UserVersion;
import org.apache.ibatis.annotations.Param;

public interface UserVersionMapper extends BaseMapper<UserVersion> {

    UserVersion queryById(@Param("id") Long id);
}