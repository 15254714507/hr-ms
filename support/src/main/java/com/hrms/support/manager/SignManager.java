package com.hrms.support.manager;

import com.hrms.api.domain.condition.SignCondition;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/2 22:01
 */
public interface SignManager {
    /**
     * 根据id获取签到信息
     *
     * @param id 用户id
     * @return 返回签到对象
     * @throws DaoException
     */
    public Sign getById(Long id) throws DaoException;

    /**
     * 添加签到信息
     *
     * @param sign 要签到的对象
     * @return 返回是否成功 1是成功，否则其他的都会报错
     * @throws DaoException
     */
    public Long insert(Sign sign) throws DaoException;

    /**
     * 修改签到信息
     *
     * @param sign
     * @return
     * @throws DaoException
     */
    public Long updateById(Sign sign) throws DaoException;

    /**
     * 删除此签到信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;


    /**
     * 获得签到对象的集合
     *
     * @param signCondition
     * @return
     * @throws DaoException
     */
    public List<Sign> list(SignCondition signCondition) throws DaoException;

    /**
     * 指定哪年哪月那日的签到信息
     *
     * @param signCondition
     * @return
     * @throws DaoException
     */
    public List<Sign> listByYearMonthDay(SignCondition signCondition) throws DaoException;
}
