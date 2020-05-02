package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.SignCondition;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.SignService;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.api.until.Result;
import com.hrms.support.manager.SignManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/3 1:29
 */
@Slf4j
@Service(interfaceClass = SignService.class)
public class SignServiceImpl implements SignService {
    @Resource
    SignManager signManager;

    @Override
    public Result insert(Sign sign) throws DaoException {
        sign.setWorkTime(LocalDateTimeFactory.getLocalDateTime());
        Long isSuc = signManager.insert(sign);
        if (isSuc != 1) {
            return new Result(0, "已签到，请勿重复签到");
        }
        return new Result(1, "签到成功");
    }

    @Override
    public List<Sign> listByYearMonthDay(SignCondition signCondition) throws DaoException {
        return signManager.listByYearMonthDay(signCondition);
    }
}
