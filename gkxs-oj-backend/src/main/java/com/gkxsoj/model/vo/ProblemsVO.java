package com.gkxsoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.gkxsoj.model.dto.problems.ProblemInOutCase;
import com.gkxsoj.model.dto.problems.ProblemInOutDesc;
import com.gkxsoj.model.entity.Problems;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author lixin
 * @date 2024/5/13 17:52
 */
@Data
public class ProblemsVO implements Serializable {
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
    private ProblemInOutDesc problemInOutDesc;

    /**
     * 输入和输入的案例
     */
    private ProblemInOutCase problemInOutCase;

    private static final long serialVersionUID = 1L;
    /**
     * 包装类转对象
     *
     * @param problemsVO
     * @return
     */
    public static Problems voToObj(ProblemsVO problemsVO) {
        if (problemsVO == null) {
            return null;
        }
        Problems problems = new Problems();
        BeanUtils.copyProperties(problemsVO, problems);
        ProblemInOutDesc problemInOutDesc = problemsVO.getProblemInOutDesc();
        ProblemInOutCase problemInOutCase = problemsVO.getProblemInOutCase();
        problems.setPbInOutDesc(JSONUtil.toJsonStr(problemInOutDesc));
        problems.setPbInOutCase(JSONUtil.toJsonStr(problemInOutCase));
        return problems;
    }

    /**
     * 对象转包装类
     * @param problems
     * @return
     */
    public static ProblemsVO objToVo(Problems problems) {
        if (problems == null) {
            return null;
        }
        ProblemsVO problemsVO = new ProblemsVO();
        BeanUtils.copyProperties(problems, problemsVO);
        problemsVO.setProblemInOutDesc(JSONUtil.toBean(problems.getPbInOutDesc(), ProblemInOutDesc.class));
        problemsVO.setProblemInOutCase(JSONUtil.toBean(problems.getPbInOutCase(), ProblemInOutCase.class));
        return problemsVO;
    }
}
