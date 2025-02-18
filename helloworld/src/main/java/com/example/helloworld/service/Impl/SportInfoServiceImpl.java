package com.example.helloworld.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.helloworld.entity.SportInfo;
import com.example.helloworld.mapper.SportInfoMapper;
import com.example.helloworld.service.SportInfoService;
import org.springframework.stereotype.Service;

@Service
public class SportInfoServiceImpl extends ServiceImpl<SportInfoMapper, SportInfo> implements SportInfoService {
    // 默认实现已由 MyBatis-Plus 提供
}
