package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.dto.DepartmentJob;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.RegisterNewEmployee;
import com.hrms.api.service.JobService;
import com.hrms.api.service.RegisterNewEmployeeService;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/19 19:47
 */
@Slf4j
@Controller
@RequestMapping("")
public class OnboardingController {
    @Reference
    JobService jobService;
    @Reference
    RegisterNewEmployeeService registerNewEmployeeService;

    /**
     * 前往入职登记新员工页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/gotoAddEmployees.do")
    public String gotoAddEmployees(Model model) {
        try {
            List<DepartmentJob> departmentJobList = jobService.listAllDepartment();
            model.addAttribute("departmentJobList", departmentJobList);
        } catch (Exception e) {
            log.error("跳转到入职登记页面时系统发生异常", e);
            return "error/404";
        }
        return "onboarding/addEmployees";
    }

    @PostMapping("/saveNewEmployees.do")
    @ResponseBody
    public Result saveNewEmployees(@Valid RegisterNewEmployee registerNewEmployee, BindingResult bindingResult, HttpSession session) {
        //提交表单后的操作
        Result result = null;
        if (bindingResult.hasErrors()) {
            return new Result(1, "添加的新员工信息不完整");
        }
        fillEmployeesUser(registerNewEmployee, session);
        try {
            result = registerNewEmployeeService.insert(registerNewEmployee);
        } catch (Exception e) {
            log.warn("添加待审核的新员工出现异常 {}", JSON.toJSONString(registerNewEmployee), e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    /**
     * 填充创建者和修改者信息的
     *
     * @param registerNewEmployee
     * @param session
     */
    private void fillEmployeesUser(RegisterNewEmployee registerNewEmployee, HttpSession session) {
        Employees employees = (Employees) session.getAttribute("employees");
        registerNewEmployee.setCreateUser(employees.getUsername());
        registerNewEmployee.setUpdateUser(employees.getUsername());
    }

}
