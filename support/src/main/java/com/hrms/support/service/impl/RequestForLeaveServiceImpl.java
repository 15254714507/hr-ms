package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.entity.RequestForLeave;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.RequestForLeaveService;
import com.hrms.api.until.Result;
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
    public Result insert(RequestForLeave requestForLeave) throws DaoException {
        Long isSuc = requestForLeaveManager.insert(requestForLeave);
        if (isSuc != 1) {
            return new Result(0, "此用户在相同时间内已有请假信息");
        }
        return new Result(1, "请假申请成功");
    }

    @Override
    public List<RequestForLeave> list(RequestForLeaveCondition requestForLeaveCondition) throws DaoException {
        return requestForLeaveManager.list(requestForLeaveCondition);
    }
}
