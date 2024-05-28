package com.gkxsoj.model.dto.problems;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lixin
 * @date 2024/5/13 17:45
 */
@Data
public class ProblemsUpdateRequest implements Serializable {
    /**
     *题目id
     */
    private long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String pbDesc;

    /**
     * 输入和输出的描述
     */
    private ProblemInOutDesc  problemInOutDesc;

    /**
     * 输入和输入的案例
     */
    private ProblemInOutCase  problemInOutCase;

    private static final long serialVersionUID = 1L;
}
