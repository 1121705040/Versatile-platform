package com.gkxsoj.model.dto.problems;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目输入输出说明
 *
 * @author lixin
 * @date 2024/5/13 16:45
 */
@Data
public class ProblemInOutDesc implements Serializable {
    private static final long serialVersionUID = -3198252561425184291L;
    /**
     * 输入描述
     */
    private String inputDesc;
    /**
     * 输出描述
     */
    private String outputDesc;

}
