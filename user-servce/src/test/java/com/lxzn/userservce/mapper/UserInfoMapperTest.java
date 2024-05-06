package com.lxzn.userservce.mapper;

import com.lxzn.userservce.model.domain.UserInfo;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;


@SpringBootTest
public class UserInfoMapperTest {

    @Resource
    UserInfoMapper userInfoMapper;
    @Test
    void userInfoTest(){
        List<UserInfo> userInfos = userInfoMapper.selectList(null);
        Assert.assertEquals(1,userInfos.size());
        userInfos.forEach(System.out::println);
    }
}