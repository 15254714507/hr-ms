package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.RecruitmentNeedsCondition;
import com.hrms.api.domain.entity.RecruitmentNeeds;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.RecruitmentNeedsService;
import com.hrms.support.manager.RecruitmentNeedsManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/10 20:54
 */
@Slf4j
@Service(interfaceClass = RecruitmentNeedsService.class)
public class RecruitmentNeedsServiceImpl implements RecruitmentNeedsService {
    @Resource
    RecruitmentNeedsManager recruitmentNeedsManager;

    @Override
    public RecruitmentNeeds getById(Long id) throws DaoException {
        return recruitmentNeedsManager.getById(id);
    }

    @Override
    public Long insert(RecruitmentNeeds recruitmentNeeds) throws DaoException {
        return recruitmentNeedsManager.insert(recruitmentNeeds);
    }

    @Override
    public Long deleteById(Long id) throws DaoException {
        return recruitmentNeedsManager.deleteById(id);
    }

    @Override
    public List<RecruitmentNeeds> list(RecruitmentNeedsCondition recruitmentNeedsCondition) throws DaoException {
        return recruitmentNeedsManager.list(recruitmentNeedsCondition);
    }
}
