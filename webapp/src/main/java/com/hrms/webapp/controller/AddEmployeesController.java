package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hrms.api.domain.dto.DepartmentJob;
import com.hrms.api.domain.entity.RegisterNewEmployee;
import com.hrms.api.service.JobService;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/19 19:47
 */
@Slf4j
@Controller
@RequestMapping("")
public class AddEmployeesController {
    @Reference
    JobService jobService;

    @RequestMapping("/gotoAddEmployees.do")
    public String gotoAddEmployees(Model model) {
        try {
            List<DepartmentJob> departmentJobList = jobService.listAllDepartment();
            model.addAttribute("departmentJobList", departmentJobList);
        } catch (Exception e) {
            log.error("跳转到入职登记页面时系统发生异常", e);
            return "error/404";
        }
        return "addEmployees/addEmployees";
    }

    @PostMapping("/saveNewEmployees.do")
    @ResponseBody
    public Result saveNewEmployees(@Valid RegisterNewEmployee registerNewEmployee) {
        //提交表单后的操作
        return null;

    }

}
