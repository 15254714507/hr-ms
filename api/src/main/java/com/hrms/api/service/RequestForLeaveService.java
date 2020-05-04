package com.hrms.api.service;

import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.entity.RequestForLeave;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.Result;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/4 0:50
 */
public interface RequestForLeaveService {
    /**
     * 添加新的请假的信息
     *
     * @param requestForLeave
     * @return 返回信息
     * @throws DaoException
     */
    public Result insert(RequestForLeave requestForLeave) throws DaoException;

    /**
     * 修改请假信息
     *
     * @param requestForLeave
     * @return
     * @throws DaoException
     */
    public Long updateById(RequestForLeave requestForLeave) throws DaoException;

    /**
     * 删除请假信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得申请假的信息的集合
     *
     * @param requestForLeaveCondition
     * @return
     * @throws DaoException
     */
    public List<RequestForLeave> list(RequestForLeaveCondition requestForLeaveCondition) throws DaoException;
}
