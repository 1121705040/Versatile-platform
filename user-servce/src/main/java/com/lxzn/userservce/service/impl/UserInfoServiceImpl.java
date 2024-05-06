package com.lxzn.userservce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxzn.userservce.common.ErrorCode;
import com.lxzn.userservce.exception.BusinessException;
import com.lxzn.userservce.model.domain.UserInfo;
import com.lxzn.userservce.service.UserInfoService;
import com.lxzn.userservce.mapper.UserInfoMapper;
import com.lxzn.userservce.utils.RuleValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.lxzn.userservce.constant.UserConstant.USER_STATE;

/**
 * @author ZhuanZ（无密码）
 * @description 针对表【user_info】的数据库操作Service实现
 * @createDate 2024-04-29 21:42:37
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {
    /**
     * 盐,打乱加密,搅屎棍,使得破解难度增加
     */
    private static final String SALT = "lx";

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Integer userRegister(UserInfo userInfo, String checkPassword) {
        //校验参数是否为空
        if (userInfo == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        if (StringUtils.isAllBlank(userInfo.getUserAccount(), userInfo.getUserPassword(), checkPassword)) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        //校验账号
        if (!RuleValidationUtils.checkAccount(userInfo.getUserAccount())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不符合要求");
        }
        //校验密码
        if (!RuleValidationUtils.checkPwd(userInfo.getUserPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不符合要求");
        }
        //校验两次输入密码是否相等
        if (!userInfo.getUserPassword().equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入密码不同");
        }
        //校验账号是否重复
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userInfo.getUserAccount());
        long count = userInfoMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
        //密码加密
        String newPwd = DigestUtils.md5DigestAsHex((SALT + userInfo.getUserPassword()).getBytes());
        userInfo.setUserPassword(newPwd);
        //用户名为账号
        userInfo.setUserName(userInfo.getUserAccount());
        //注册
        if (!this.save(userInfo)) {
            throw new BusinessException(ErrorCode.INTERNAL_ANOMALY, "注册失败,请重试!");
        }
        return userInfo.getUserId();
    }

    @Override
    public UserInfo userLogin(UserInfo userInfo, HttpServletRequest httpRequest) {
        //校验参数
        //校验参数是否为空
        if (userInfo == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        if (StringUtils.isAllBlank(userInfo.getUserAccount(), userInfo.getUserPassword())) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        //校验账号
        if (!RuleValidationUtils.checkAccount(userInfo.getUserAccount())) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        //校验密码
        if (!RuleValidationUtils.checkPwd(userInfo.getUserPassword())) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        //密码加密
        String newPwd = DigestUtils.md5DigestAsHex((SALT + userInfo.getUserPassword()).getBytes());
        userInfo.setUserPassword(newPwd);
        //查询用户信息
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userInfo.getUserAccount());
        queryWrapper.eq("user_password", newPwd);
        UserInfo userRequest = userInfoMapper.selectOne(queryWrapper);
        if (userRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        //返回值脱敏
        // TODO: 2024/5/1 封装一个返回对象信息脱敏
        UserInfo userInfo1 = userDesensitization(userRequest);
        //保存登陆态
        httpRequest.getSession().setAttribute(USER_STATE, userInfo1);
        return userInfo1;
    }

    @Override
    public UserInfo userDesensitization(UserInfo userRequest) {
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserId(userRequest.getUserId());
        userInfo1.setUserName(userRequest.getUserName());
        userInfo1.setUserAccount(userRequest.getUserAccount());
        userInfo1.setUserAvatar(userRequest.getUserAvatar());
        userInfo1.setUserEmail(userRequest.getUserEmail());
        userInfo1.setPlanetNumber(userRequest.getPlanetNumber());
        userInfo1.setUserPhoneNumber(userRequest.getUserPhoneNumber());
        userInfo1.setUserGender(userRequest.getUserGender());
        userInfo1.setUserStatus(userRequest.getUserStatus());
        userInfo1.setUserPermissions(userRequest.getUserPermissions());
        userInfo1.setCreateDate(userRequest.getCreateDate());
        return userInfo1;
    }
}




