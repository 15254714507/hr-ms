package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.dto.DepartmentJob;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.dto.EmployeesWages;
import com.hrms.api.domain.entity.UserJob;
import com.hrms.api.service.EmployeesService;
import com.hrms.api.service.JobService;
import com.hrms.api.service.UserJobService;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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
    @Reference
    UserJobService userJobService;
    @Reference
    JobService jobService;

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

    /**
     * 前往职员岗位薪资页面
     *
     * @return
     */
    @RequestMapping("/gotoEmployeesJobWages.do")
    public String gotoEmployeesJobWages() {

        return "personnel/employeesJobWages";
    }

    @PostMapping("/getEmployeesWagesList.do")
    @ResponseBody
    public List<EmployeesWages> getEmployeesWagesList() {
        List<EmployeesWages> employeesWagesList = null;
        try {
            employeesWagesList = employeesService.listEmployeesWages();
        } catch (Exception e) {
            log.error("获得职员的岗位薪资关系列表出现系统异常", e);
        }
        return employeesWagesList;
    }

    @RequestMapping("/gotoUpdateEmployeesJobWages.do")
    public String gotoUpdateEmployeesJobWages(Long id, Model model) {
        if (id == null) {
            return "error/404";
        }
        UserJob userJob = null;
        List<DepartmentJob> departmentJobList = null;
        try {
            userJob = userJobService.getById(id);
            departmentJobList = jobService.listAllDepartment();
        } catch (Exception e) {
            log.error("前往修改员工岗位工资页面时发生系统异常 userJobId{}", id);
            return "error/404";
        }
        if (userJob == null || departmentJobList == null || departmentJobList.size() < 1) {
            log.error("获得岗位薪资信息为空 id{}", id);
            return "error/404";
        }
        model.addAttribute("userJob", userJob);
        model.addAttribute("departmentJobList", departmentJobList);
        return "personnel/updateEmployeesJobWages";
    }

    @PostMapping("/updateEmployeesJobWages.do")
    @ResponseBody
    public Result updateEmployeesJobWages(@Valid UserJob userJob, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Result(0, "填写信息的格式不正确");
        }
        Result result = null;
        try {
            Long isSuc = userJobService.updateById(userJob);
            if (isSuc != 1) {
                result = new Result(0, "没有此人的岗位和薪酬");
            } else {
                result = new Result(1, "修改成功");
            }
        } catch (Exception e) {
            log.error("修改员工的岗位和薪资出现系统异常 userJob{}", JSON.toJSONString(userJob), e);
            return new Result(-1, "出现系统异常,请刷新后重试");
        }
        return result;
    }
}
