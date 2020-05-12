package com.hrms.support.manager;

import com.hrms.api.domain.condition.RecruitersCondition;
import com.hrms.api.domain.entity.Recruiters;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/12 13:15
 */
public interface RecruitersManager {
    /**
     * 根据id获得应聘者信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Recruiters getById(Long id) throws DaoException;

    /**
     * 添加新的应聘者信息
     *
     * @param recruiters
     * @return
     * @throws DaoException
     */
    public Long insert(Recruiters recruiters) throws DaoException;

    /**
     * 修改应聘者信息
     *
     * @param recruiters
     * @return
     * @throws DaoException
     */
    public Long updateById(Recruiters recruiters) throws DaoException;

    /**
     * 删除应聘者信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得应聘者的集合
     *
     * @param recruitersCondition
     * @return
     * @throws DaoException
     */
    public List<Recruiters> list(RecruitersCondition recruitersCondition) throws DaoException;
}
