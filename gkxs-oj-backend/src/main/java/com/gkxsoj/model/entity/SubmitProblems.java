package com.gkxsoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 题目表
 * @TableName submit_problems
 */
@TableName(value ="submit_problems")
@Data
public class SubmitProblems implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 题目id
     */
    private Long pbId;

    /**
     * 最大内存(kb)
     */
    private Integer maxMemory;

    /**
     * 最大执行时间(ms)
     */
    private Integer maxTime;

    /**
     * 使用的语言
     */
    private String runLanguage;

    /**
     * 用户代码
     */
    private String userCode;

    /**
     * 执行结果 1.正确 2.答错 3.编译错误 4.案例错误 5.内存限制超出 6.运行时错误 7.超出输出限制 8.超出时间限制
     */
    private Integer runResult;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

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
        SubmitProblems other = (SubmitProblems) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPbId() == null ? other.getPbId() == null : this.getPbId().equals(other.getPbId()))
            && (this.getMaxMemory() == null ? other.getMaxMemory() == null : this.getMaxMemory().equals(other.getMaxMemory()))
            && (this.getMaxTime() == null ? other.getMaxTime() == null : this.getMaxTime().equals(other.getMaxTime()))
            && (this.getRunLanguage() == null ? other.getRunLanguage() == null : this.getRunLanguage().equals(other.getRunLanguage()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getRunResult() == null ? other.getRunResult() == null : this.getRunResult().equals(other.getRunResult()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPbId() == null) ? 0 : getPbId().hashCode());
        result = prime * result + ((getMaxMemory() == null) ? 0 : getMaxMemory().hashCode());
        result = prime * result + ((getMaxTime() == null) ? 0 : getMaxTime().hashCode());
        result = prime * result + ((getRunLanguage() == null) ? 0 : getRunLanguage().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getRunResult() == null) ? 0 : getRunResult().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", pbId=").append(pbId);
        sb.append(", maxMemory=").append(maxMemory);
        sb.append(", maxTime=").append(maxTime);
        sb.append(", runLanguage=").append(runLanguage);
        sb.append(", userCode=").append(userCode);
        sb.append(", runResult=").append(runResult);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}