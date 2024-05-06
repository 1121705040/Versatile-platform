package com.lxzn.userservce.service.impl;

import com.lxzn.userservce.model.domain.UserInfo;
import com.lxzn.userservce.service.UserInfoService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
public class UserInfoServiceImplTest {

    @Resource
    private UserInfoService userInfoService;

    @Test
    void registerTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserAccount("");
        userInfo.setUserPassword("");
        String checkPassword = "";
        Integer res = userInfoService.userRegister(userInfo,checkPassword);
        Assertions.assertEquals(-1,res);

        userInfo.setUserAccount("12321ff");
        userInfo.setUserPassword("1233123");
        checkPassword = "1233123";
        Integer res1 = userInfoService.userRegister(userInfo,checkPassword);
        Assertions.assertEquals(-1,res);

        userInfo.setUserAccount("dsafdasfff123123dsafdasfff123123213213");
        userInfo.setUserPassword("1231233");
        checkPassword = "1233123";
        Integer res2 = userInfoService.userRegister(userInfo,checkPassword);
        Assertions.assertEquals(-1,res);

        userInfo.setUserAccount("dsafdasff");
        userInfo.setUserPassword("dsafdasfff123123dsafdasfff123123213213");
        checkPassword = "1233123";
        Integer res3 = userInfoService.userRegister(userInfo,checkPassword);
        Assertions.assertEquals(-1,res);

        userInfo.setUserAccount("dsa");
        userInfo.setUserPassword("1231233");
        checkPassword = "1233123";
        Integer res4 = userInfoService.userRegister(userInfo,checkPassword);
        Assertions.assertEquals(-1,res);

        userInfo.setUserAccount("dsaeqwewewq");
        userInfo.setUserPassword("12");
        checkPassword = "1233123";
        Integer res5 = userInfoService.userRegister(userInfo,checkPassword);
        Assertions.assertEquals(-1,res);

        userInfo.setUserAccount("ddd123123");
        userInfo.setUserPassword("1231233");
        checkPassword = "1231233";
        Integer res6 = userInfoService.userRegister(userInfo,checkPassword);
        Assertions.assertEquals(-1,res);

        userInfo.setUserAccount("ddd1231123");
        userInfo.setUserPassword("1231233");
        Integer res7 = userInfoService.userRegister(userInfo,checkPassword);
        Assertions.assertEquals(-1,res);
    }
}