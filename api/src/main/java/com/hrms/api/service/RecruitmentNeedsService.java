package com.hrms.api.service;

import com.hrms.api.domain.condition.RecruitmentNeedsCondition;
import com.hrms.api.domain.entity.RecruitmentNeeds;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/10 20:53
 */
public interface RecruitmentNeedsService {
    /**
     * 根据id获得招聘需求
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public RecruitmentNeeds getById(Long id) throws DaoException;

    /**
     * 添加新的招聘需求
     *
     * @param recruitmentNeeds
     * @return
     * @throws DaoException
     */
    public Long insert(RecruitmentNeeds recruitmentNeeds) throws DaoException;

    /**
     * 删除招聘需求
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得招聘需求的集合
     *
     * @param recruitmentNeedsCondition
     * @return
     * @throws DaoException
     */
    public List<RecruitmentNeeds> list(RecruitmentNeedsCondition recruitmentNeedsCondition) throws DaoException;
}
