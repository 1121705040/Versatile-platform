package com.lxzn.userservce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxzn.userservce.model.domain.UserInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhuanZ（无密码）
 * @description 针对表【user_info】的数据库操作Service
 * @createDate 2024-04-29 21:42:37
 */
@Service
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 用户注册
     *
     * @param userInfo 包含用户名,密码
     * @param checkPassword 校验密码
     * @return 返回是否成功的状态, int类型
     */
    Integer userRegister(UserInfo userInfo,String checkPassword);

    /**
     * 用户登陆
     * @param userInfo 登陆参数
     * @param httpRequest 请求对象
     * @return 返回脱敏后的用户信息
     */
    UserInfo userLogin(UserInfo userInfo, HttpServletRequest httpRequest);

    UserInfo userDesensitization(UserInfo userRequest);
}
