package com.bite.book.mapper;

import com.bite.book.model.UserInfo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    void getUserInfoByName() {
        UserInfo userInfo = userInfoMapper.getUserInfoByName("zhangsan");
        log.info("查询数据: {}", userInfo);
    }
}