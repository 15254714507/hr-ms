package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.service.UserJobService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 孔超
 * @date 2020/4/13 23:12
 */
@Slf4j
@Service(interfaceClass = UserJobService.class)
public class UserJobServiceImpl implements UserJobService {
}
