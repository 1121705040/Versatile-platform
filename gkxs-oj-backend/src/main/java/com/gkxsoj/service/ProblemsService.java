package com.gkxsoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gkxsoj.model.dto.problems.ProblemsQueryRequest;
import com.gkxsoj.model.entity.Problems;
import com.gkxsoj.model.vo.ProblemsVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author ZhuanZ（无密码）
* @description 针对表【problems(题目表)】的数据库操作Service
* @createDate 2024-05-13 10:54:52
*/
public interface ProblemsService extends IService<Problems> {
    /**
     * 校验
     *
     * @param problems
     * @param add
     */
    void validProblems(Problems problems, boolean add);

    /**
     * 获取查询条件
     *
     * @param problemsQueryRequest
     * @return
     */
    QueryWrapper<Problems> getQueryWrapper(ProblemsQueryRequest problemsQueryRequest);

    /**
     * 获取帖子封装
     *
     * @param problems
     * @param request
     * @return
     */
    ProblemsVO getProblemsVO(Problems problems, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     * @param problemsPage
     * @param request
     * @return
     */
    Page<ProblemsVO> getProblemsVOPage(Page<Problems> problemsPage, HttpServletRequest request);
}
