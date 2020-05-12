package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.RecruitersCondition;
import com.hrms.api.domain.entity.Recruiters;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.RecruitersService;
import com.hrms.support.manager.RecruitersManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/12 17:01
 */
@Slf4j
@Service(interfaceClass = RecruitersService.class)
public class RecruitersServiceImpl implements RecruitersService {
    @Resource
    RecruitersManager recruitersManager;

    @Override
    public Long insert(Recruiters recruiters) throws DaoException {
        return recruitersManager.insert(recruiters);
    }

    @Override
    public Long deleteById(Long id) {
        return recruitersManager.deleteById(id);
    }

    @Override
    public List<Recruiters> list(RecruitersCondition recruitersCondition) throws DaoException {
        return recruitersManager.list(recruitersCondition);
    }
}
