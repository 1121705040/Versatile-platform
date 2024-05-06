package com.lxzn.userservce.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 * @author lixin
 */
@Data
public class RequestRegisterUser implements Serializable {

    private static final long serialVersionUID = 889025512347194128L;
    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 校验密码
     */
    private String checkPassword;

}
