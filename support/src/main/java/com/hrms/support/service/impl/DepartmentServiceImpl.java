package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 孔超
 * @date 2020/4/13 23:08
 */
@Slf4j
@Service(interfaceClass = DepartmentService.class)
public class DepartmentServiceImpl implements DepartmentService {
}
