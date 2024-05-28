package com.gkxsoj.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gkxsoj.common.ErrorCode;
import com.gkxsoj.constant.CommonConstant;
import com.gkxsoj.exception.BusinessException;
import com.gkxsoj.exception.ThrowUtils;
import com.gkxsoj.mapper.ProblemsMapper;
import com.gkxsoj.model.dto.problems.ProblemsQueryRequest;
import com.gkxsoj.model.entity.Problems;
import com.gkxsoj.model.vo.ProblemsVO;
import com.gkxsoj.service.ProblemsService;
import com.gkxsoj.service.UserService;
import com.gkxsoj.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lixin
 * @date 2024/5/13 11:20
 */
@Service
public class ProblemsServiceImpl extends ServiceImpl<ProblemsMapper, Problems>
        implements ProblemsService {
    @Resource
    private UserService userService;

    @Override
    public void validProblems(Problems problems, boolean add) {
        if (problems == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String title = problems.getTitle();
        String pbDesc = problems.getPbDesc();
        String pbInOutDesc = problems.getPbInOutDesc();
        String pbInOutCase = problems.getPbInOutCase();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(title, pbDesc, pbInOutDesc, pbInOutCase), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(title) && title.length() > 128) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(pbDesc) && pbDesc.length() > 1024) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述过长");
        }
        if (StringUtils.isNotBlank(pbInOutDesc) && pbInOutDesc.length() > 2048) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "输入输出描述过长");
        }
        if (StringUtils.isNotBlank(pbInOutCase) && pbInOutCase.length() > 4096) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "输入输出案例过长");
        }
    }

    /**
     * 获取查询包装类
     *
     * @param problemsQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<Problems> getQueryWrapper(ProblemsQueryRequest problemsQueryRequest) {
        QueryWrapper<Problems> queryWrapper = new QueryWrapper<>();
        if (problemsQueryRequest == null) {
            return queryWrapper;
        }
        String sortField = problemsQueryRequest.getSortField();
        String sortOrder = problemsQueryRequest.getSortOrder();
        Long id = problemsQueryRequest.getId();
        String title = problemsQueryRequest.getTitle();
        Long userId = problemsQueryRequest.getUserId();
        // 拼接查询条件
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
//        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
//        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }


    @Override
    public ProblemsVO getProblemsVO(Problems problems, HttpServletRequest request) {
        ProblemsVO problemsVO = ProblemsVO.objToVo(problems);
        return problemsVO;
    }

    @Override
    public Page<ProblemsVO> getProblemsVOPage(Page<Problems> problemsPage, HttpServletRequest request) {
        List<Problems> problemsList = problemsPage.getRecords();
        Page<ProblemsVO> problemsVOPage = new Page<>(problemsPage.getCurrent(), problemsPage.getSize(), problemsPage.getTotal());
        if (CollUtil.isEmpty(problemsList)) {
            return problemsVOPage;
        }
        // 填充信息
        List<ProblemsVO> problemsVOList = problemsList.stream().map(problems -> {
            ProblemsVO problemsVO = ProblemsVO.objToVo(problems);
            return problemsVO;
        }).collect(Collectors.toList());
        problemsVOPage.setRecords(problemsVOList);
        return problemsVOPage;
    }

}
