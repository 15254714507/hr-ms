package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.service.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 人事管理
 *
 * @author 孔超
 * @date 2020/5/9 21:59
 */
@Slf4j
@Controller
@RequestMapping("")
public class PersonnelController {
    @Reference
    EmployeesService employeesService;

    /**
     * 前往全部职员列表
     *
     * @return
     */
    @RequestMapping("/gotoEmployeesList.do")
    public String gotoEmployeesList() {
        return "personnel/employeesList";
    }

    @PostMapping("/getEmployeesList.do")
    @ResponseBody
    public List<Employees> getEmployeesList() {
        List<Employees> employeesList = null;
        try {
            employeesList = employeesService.listEmployees();
        } catch (Exception e) {
            log.error("获得职员列表出现系统异常", e);
        }
        return employeesList;
    }
}
