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
 * @TableName problems
 */
@TableName(value ="problems")
@Data
public class Problems implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 题目描述
     */
    private String pbDesc;

    /**
     * 提交数
     */
    private Integer commitCount;

    /**
     * 输入输出描述(json)
     */
    private String pbInOutDesc;

    /**
     * 输入输出案例(json)
     */
    private String pbInOutCase;

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
        Problems other = (Problems) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPbDesc() == null ? other.getPbDesc() == null : this.getPbDesc().equals(other.getPbDesc()))
            && (this.getCommitCount() == null ? other.getCommitCount() == null : this.getCommitCount().equals(other.getCommitCount()))
            && (this.getPbInOutDesc() == null ? other.getPbInOutDesc() == null : this.getPbInOutDesc().equals(other.getPbInOutDesc()))
            && (this.getPbInOutCase() == null ? other.getPbInOutCase() == null : this.getPbInOutCase().equals(other.getPbInOutCase()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPbDesc() == null) ? 0 : getPbDesc().hashCode());
        result = prime * result + ((getCommitCount() == null) ? 0 : getCommitCount().hashCode());
        result = prime * result + ((getPbInOutDesc() == null) ? 0 : getPbInOutDesc().hashCode());
        result = prime * result + ((getPbInOutCase() == null) ? 0 : getPbInOutCase().hashCode());
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
        sb.append(", title=").append(title);
        sb.append(", userId=").append(userId);
        sb.append(", pbDesc=").append(pbDesc);
        sb.append(", commitCount=").append(commitCount);
        sb.append(", pbInOutDesc=").append(pbInOutDesc);
        sb.append(", pbInOutSpl=").append(pbInOutCase);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}