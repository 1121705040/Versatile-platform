package com.lxzn.userservce.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 用户唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 星球编号
     */
    private Integer planetNumber;

    /**
     * 用户手机号码
     */
    private String userPhoneNumber;

    /**
     * 用户性别 --0女 --1男

     */
    private Integer userGender;

    /**
     * 用户状态 --0正常 --1删除
     */
    @TableLogic
    private Integer userStatus;

    /**
     * 用户权限 --0管理员 --1普通用户
     */
    private Integer userPermissions;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserInfo other = (UserInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserAccount() == null ? other.getUserAccount() == null : this.getUserAccount().equals(other.getUserAccount()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getUserAvatar() == null ? other.getUserAvatar() == null : this.getUserAvatar().equals(other.getUserAvatar()))
            && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
            && (this.getPlanetNumber() == null ? other.getPlanetNumber() == null : this.getPlanetNumber().equals(other.getPlanetNumber()))
            && (this.getUserPhoneNumber() == null ? other.getUserPhoneNumber() == null : this.getUserPhoneNumber().equals(other.getUserPhoneNumber()))
            && (this.getUserGender() == null ? other.getUserGender() == null : this.getUserGender().equals(other.getUserGender()))
            && (this.getUserStatus() == null ? other.getUserStatus() == null : this.getUserStatus().equals(other.getUserStatus()))
            && (this.getUserPermissions() == null ? other.getUserPermissions() == null : this.getUserPermissions().equals(other.getUserPermissions()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserAccount() == null) ? 0 : getUserAccount().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getUserAvatar() == null) ? 0 : getUserAvatar().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getPlanetNumber() == null) ? 0 : getPlanetNumber().hashCode());
        result = prime * result + ((getUserPhoneNumber() == null) ? 0 : getUserPhoneNumber().hashCode());
        result = prime * result + ((getUserGender() == null) ? 0 : getUserGender().hashCode());
        result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
        result = prime * result + ((getUserPermissions() == null) ? 0 : getUserPermissions().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userAvatar=").append(userAvatar);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", planetNumber=").append(planetNumber);
        sb.append(", userPhoneNumber=").append(userPhoneNumber);
        sb.append(", userGender=").append(userGender);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", userPermissions=").append(userPermissions);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}