package com.gkxsoj.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gkxsoj.annotation.AuthCheck;
import com.gkxsoj.common.BaseResponse;
import com.gkxsoj.common.DeleteRequest;
import com.gkxsoj.common.ErrorCode;
import com.gkxsoj.common.ResultUtils;
import com.gkxsoj.constant.UserConstant;
import com.gkxsoj.exception.BusinessException;
import com.gkxsoj.exception.ThrowUtils;
import com.gkxsoj.model.dto.problems.*;
import com.gkxsoj.model.vo.ProblemsVO;
import com.gkxsoj.model.entity.Problems;
import com.gkxsoj.model.entity.User;
import com.gkxsoj.service.ProblemsService;
import com.gkxsoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/problems")
@Slf4j
public class ProblemsController {

    @Resource
    private ProblemsService problemsService;

    @Resource
    private UserService userService;

    /**
     * 创建
     *
     * @param problemsAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addProblems(@RequestBody ProblemsAddRequest problemsAddRequest, HttpServletRequest request) {
        if (problemsAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Problems problems = new Problems();
        BeanUtils.copyProperties(problemsAddRequest, problems);
        ProblemInOutDesc problemInOutDesc = problemsAddRequest.getProblemInOutDesc();
        ProblemInOutCase problemInOutCase = problemsAddRequest.getProblemInOutCase();
        problems.setPbInOutDesc(JSONUtil.toJsonStr(problemInOutDesc));
        problems.setPbInOutCase(JSONUtil.toJsonStr(problemInOutCase));
        problemsService.validProblems(problems, true);
        User loginUser = userService.getLoginUser(request);
        problems.setUserId(loginUser.getId());
        boolean result = problemsService.save(problems);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newPostId = problems.getId();
        return ResultUtils.success(newPostId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteProblems(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Problems oldPost = problemsService.getById(id);
        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldPost.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = problemsService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param problemsUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateProblems(@RequestBody ProblemsUpdateRequest problemsUpdateRequest) {
        if (problemsUpdateRequest == null || problemsUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Problems problems = new Problems();
        BeanUtils.copyProperties(problemsUpdateRequest, problems);
        // 参数校验
        problemsService.validProblems(problems, false);
        long id = problemsUpdateRequest.getId();
        // 判断是否存在
        Problems oldProblem = problemsService.getById(id);
        ThrowUtils.throwIf(oldProblem == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = problemsService.updateById(problems);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<ProblemsVO> getProblemsVOById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Problems problems = problemsService.getById(id);
        if (problems == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(problemsService.getProblemsVO(problems, request));
    }

    /**
     * 分页获取列表（仅管理员）
     *
     * @param problemsQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Problems>> listProblemsByPage(@RequestBody ProblemsQueryRequest problemsQueryRequest) {
        long current = problemsQueryRequest.getCurrent();
        long size = problemsQueryRequest.getPageSize();
        Page<Problems> problemsPage = problemsService.page(new Page<>(current, size),
                problemsService.getQueryWrapper(problemsQueryRequest));
        return ResultUtils.success(problemsPage);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param problemsQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<ProblemsVO>> listProblemsVOByPage(@RequestBody ProblemsQueryRequest problemsQueryRequest,
            HttpServletRequest request) {
        long current = problemsQueryRequest.getCurrent();
        long size = problemsQueryRequest.getPageSize();
        System.out.println("测试热加载");
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Problems> problemsPage = problemsService.page(new Page<>(current, size),
                problemsService.getQueryWrapper(problemsQueryRequest));
        return ResultUtils.success(problemsService.getProblemsVOPage(problemsPage, request));
    }

}
