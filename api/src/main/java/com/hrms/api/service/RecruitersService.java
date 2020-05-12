package com.hrms.api.service;

import com.hrms.api.domain.condition.RecruitersCondition;
import com.hrms.api.domain.entity.Recruiters;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/12 16:59
 */
public interface RecruitersService {
    /**
     * 添加新的应聘者信息
     *
     * @param recruiters
     * @return
     * @throws DaoException
     */
    public Long insert(Recruiters recruiters) throws DaoException;

    /**
     * 删除应聘者信息
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得应聘者的集合
     *
     * @param recruitersCondition
     * @return
     * @throws DaoException
     */
    public List<Recruiters> list(RecruitersCondition recruitersCondition) throws DaoException;
}
