package com.hrms.api.service;

import com.hrms.api.domain.condition.SignCondition;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.Result;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/3 1:28
 */
public interface SignService {
    /**
     * 根据id获取签到信息
     *
     * @param id 用户id
     * @return 返回签到对象
     * @throws DaoException
     */
    public Sign getById(Long id) throws DaoException;

    /**
     * 添加新的签到记录
     *
     * @param sign
     * @return
     * @throws DaoException
     */
    public Result insert(Sign sign) throws DaoException;

    /**
     * 修改签到信息
     *
     * @param sign
     * @return
     * @throws DaoException
     */
    public Long updateById(Sign sign) throws DaoException;

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

    /**
     * 晚上18点以后的签到下班服务
     *
     * @param sign
     * @return
     * @throws DaoException
     */
    public Result nightSign(Sign sign) throws DaoException;
}
