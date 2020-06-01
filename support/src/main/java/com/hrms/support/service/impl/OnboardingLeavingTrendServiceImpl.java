package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.OnboardingLeavingTrendCondition;
import com.hrms.api.domain.entity.OnboardingLeavingTrend;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.OnboardingLeavingTrendService;
import com.hrms.support.manager.OnboardingLeavingTrendManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/6/2 0:48
 */
@Slf4j
@Service(interfaceClass = OnboardingLeavingTrendService.class)
public class OnboardingLeavingTrendServiceImpl implements OnboardingLeavingTrendService {
    @Resource
    OnboardingLeavingTrendManager onboardingLeavingTrendManager;

    @Override
    public Long insert(OnboardingLeavingTrend onboardingLeavingTrend) throws DaoException {
        return onboardingLeavingTrendManager.insert(onboardingLeavingTrend);
    }

    @Override
    public List<OnboardingLeavingTrend> listNewOneYear() throws DaoException {
        OnboardingLeavingTrendCondition onboardingLeavingTrendCondition = new OnboardingLeavingTrendCondition();
        //这是12个月的记录
        onboardingLeavingTrendCondition.setRows(12);
        return onboardingLeavingTrendManager.list(onboardingLeavingTrendCondition);
    }
}
