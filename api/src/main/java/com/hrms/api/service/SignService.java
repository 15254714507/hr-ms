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
