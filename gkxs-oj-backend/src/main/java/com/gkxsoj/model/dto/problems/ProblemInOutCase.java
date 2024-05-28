package com.gkxsoj.model.dto.problems;

import lombok.Data;

import java.io.Serializable;

/**
 * 案例的输入输出描述
 * @author lixin
 * @date 2024/5/13 16:45
 */
@Data
public class ProblemInOutCase implements Serializable {

    private static final long serialVersionUID = -853641425280067088L;
    /**
     * 案例输入描述
     */
    private String inputCase;
    /**
     * 案例输出描述
     */
    private String outputCase;
}
