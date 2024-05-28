package com.gkxsoj.model.dto.problems;

import com.gkxsoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 * @author lixin
 * @date 2024/5/13 17:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemsQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = -8725517402134809598L;
    /**
     * 题目id
     */
    private long id;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 题目标题
     */
    private String title;
}
