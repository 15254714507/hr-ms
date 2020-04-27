package com.hrms.api.service;

import com.hrms.api.domain.entity.DimissionUser;
import com.hrms.api.exception.ServiceException;
import com.hrms.api.until.Result;

/**
 * @author 孔超
 * @date 2020/4/27 18:37
 */
public interface DimissionUserService {
    /**
     * 根据账号获得这个账号的离职需要的信息
     *
     * @param username
     * @return
     */
    public DimissionUser getDimissionUserMsgByUsername(String username);

    /**
     * 添加离职员工
     *
     * @param dimissionUser
     * @return
     * @throws ServiceException
     */
    public Result insert(DimissionUser dimissionUser);
}
