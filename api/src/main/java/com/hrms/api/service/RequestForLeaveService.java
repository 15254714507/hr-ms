package com.hrms.api.service;

import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.entity.RequestForLeave;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/4 0:50
 */
public interface RequestForLeaveService {
    /**
     * 获得申请假的信息的集合
     *
     * @param requestForLeaveCondition
     * @return
     * @throws DaoException
     */
    public List<RequestForLeave> list(RequestForLeaveCondition requestForLeaveCondition) throws DaoException;
}
