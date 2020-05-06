package com.hrms.support.manager;

import com.hrms.api.domain.condition.WagesCondition;
import com.hrms.api.domain.entity.Wages;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/6 2:57
 */
public interface WagesManager {
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
     * 删除此工资信息 逻辑删除
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;


    /**
     * 获得工资对象的集合
     *
     * @param wagesCondition
     * @return
     * @throws DaoException
     */
    public List<Wages> list(WagesCondition wagesCondition) throws DaoException;

    /**
     * 指定哪年哪月的工资信息
     *
     * @param wagesCondition
     * @return
     * @throws DaoException
     */
    public List<Wages> listByYearMonth(WagesCondition wagesCondition) throws DaoException;
}
