package com.hrms.support.manager;

import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.entity.RequestForLeave;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/3 22:41
 */
public interface RequestForLeaveManager {
    /**
     * 根据id获得指定的请假信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public RequestForLeave getById(Long id) throws DaoException;

    /**
     * 添加新的请假的信息
     *
     * @param requestForLeave
     * @return
     * @throws DaoException
     */
    public Long insert(RequestForLeave requestForLeave) throws DaoException;

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
