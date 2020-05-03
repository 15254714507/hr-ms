package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.entity.RequestForLeave;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.RequestForLeaveService;
import com.hrms.support.manager.RequestForLeaveManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/4 0:52
 */
@Slf4j
@Service(interfaceClass = RequestForLeaveService.class)
public class RequestForLeaveServiceImpl implements RequestForLeaveService {
    @Resource
    RequestForLeaveManager requestForLeaveManager;

    @Override
    public List<RequestForLeave> list(RequestForLeaveCondition requestForLeaveCondition) throws DaoException {
        return requestForLeaveManager.list(requestForLeaveCondition);
    }
}
