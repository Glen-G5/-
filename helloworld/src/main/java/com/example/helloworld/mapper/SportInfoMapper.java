package com.example.helloworld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.helloworld.entity.SportInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SportInfoMapper extends BaseMapper<SportInfo> {
    // 继承 BaseMapper 后，默认提供 CRUD 方法
}
