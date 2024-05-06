package com.lxzn.userservce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxzn.userservce.common.BaseResponse;
import com.lxzn.userservce.common.ErrorCode;
import com.lxzn.userservce.exception.BusinessException;
import com.lxzn.userservce.model.domain.UserInfo;
import com.lxzn.userservce.model.request.RequestLoginUser;
import com.lxzn.userservce.model.request.RequestRegisterUser;
import com.lxzn.userservce.service.UserInfoService;
import com.lxzn.userservce.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.lxzn.userservce.constant.UserConstant.USER_STATE;

/**
 * 用户接口
 *
 * @author lixin
 */
@RestController
@RequestMapping("/user")
public class UserinfoController {
    @Resource
    UserInfoService userInfoService;

    /**
     * 用户注册
     *
     * @param requestRegisterUser
     * @return 统一封装返回对象
     */
    @PostMapping("/userRegister")
    public BaseResponse<Integer> userRegister(@RequestBody RequestRegisterUser requestRegisterUser) {
        if (requestRegisterUser == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserAccount(requestRegisterUser.getUserAccount());
        userInfo.setUserPassword(requestRegisterUser.getUserPassword());
        Integer integer = userInfoService.userRegister(userInfo, requestRegisterUser.getCheckPassword());
        return ResultUtils.success(integer);
    }

    /**
     * 用户登陆
     *
     * @param requestLoginUser
     * @param httpServletRequest
     * @return 统一封装返回对象
     */
    @PostMapping("/login")
    public BaseResponse<UserInfo> userRegister(@RequestBody RequestLoginUser requestLoginUser, HttpServletRequest httpServletRequest) {
        if (requestLoginUser == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserAccount(requestLoginUser.getUserAccount());
        userInfo.setUserPassword(requestLoginUser.getUserPassword());
        return ResultUtils.success(userInfoService.userLogin(userInfo, httpServletRequest));
    }

    /**
     * 用户查询
     *
     * @param userSearch
     * @param httpServletRequest
     * @return 统一封装返回对象
     */
    @PostMapping("/userSearch")
    public BaseResponse<List<UserInfo>> userRegister(String userSearch, HttpServletRequest httpServletRequest) {
        if (getUserState(httpServletRequest)) throw new BusinessException(ErrorCode.NULL_ERROR);

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        List<UserInfo> desensitivityList;
        if (StringUtils.isNotBlank(userSearch)) {
            queryWrapper.like("user_name", userSearch);
            List<UserInfo> list = userInfoService.list(queryWrapper);
            desensitivityList = list.stream().map(user -> userInfoService.userDesensitization(user)).collect(Collectors.toList());
            return ResultUtils.success(desensitivityList);
        }
        List<UserInfo> resList = userInfoService.list();
        desensitivityList = resList.stream().map(user -> userInfoService.userDesensitization(user)).collect(Collectors.toList());
        return ResultUtils.success(desensitivityList);
    }

    /**
     * 用户删除
     *
     * @param userId
     * @param httpServletRequest
     * @return 统一封装返回对象
     */
    @PostMapping("/userDelete")
    public BaseResponse<Boolean> userRegister(int userId, HttpServletRequest httpServletRequest) {
        if (userId <= 1) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(userInfoService.removeById(userId));
    }

    /**
     * 用户退出登陆
     *
     * @param httpServletRequest
     * @return 统一封装返回对象
     */
    @PostMapping("/userLogout")
    public void userRegister(HttpServletRequest httpServletRequest) {
        httpServletRequest.removeAttribute(USER_STATE);
    }

    /**
     * 获取用户态,判断是否管理员
     *
     * @param httpServletRequest
     * @return
     */
    private static boolean getUserState(HttpServletRequest httpServletRequest) {
        UserInfo user = (UserInfo) httpServletRequest.getSession().getAttribute(USER_STATE);
        return user == null || user.getUserPermissions() != 0;
    }
}
