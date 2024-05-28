package com.gkxsoj.model.dto.problems;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class ProblemsAddRequest implements Serializable {
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