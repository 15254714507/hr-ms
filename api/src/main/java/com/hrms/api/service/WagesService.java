package com.hrms.api.service;

import com.hrms.api.domain.condition.WagesCondition;
import com.hrms.api.domain.entity.Wages;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.Result;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/7 16:44
 */
public interface WagesService {
    /**
     * 根据id获取工资信息
     *
     * @param id 用户id
     * @return 返回工资对象
     * @throws DaoException
     */
    public Wages getById(Long id) throws DaoException;

    /**
     * 添加新的工资信息
     *
     * @param wages
     * @return 返回是否成功 1是成功，否则其他的都会报错
     * @throws DaoException
     */
    public Long insert(Wages wages) throws DaoException;

    /**
     * 修改工资信息
     *
     * @param wages
     * @return
     * @throws DaoException
     */
    public Long updateById(Wages wages) throws DaoException;

    /**
     * 指定哪年哪月的工资信息
     *
     * @param wagesCondition
     * @return
     * @throws DaoException
     */
    public List<Wages> listByYearMonth(WagesCondition wagesCondition) throws DaoException;

    /**
     * 获得待核对的工资信息
     *
     * @param wagesCondition
     * @return
     * @throws DaoException
     */
    public List<Wages> listCheckWages(WagesCondition wagesCondition) throws DaoException;

    /**
     * 提交核对后的工资信息
     *
     * @param wages
     * @return
     */
    public Result submitCheckWages(Wages wages);
}
