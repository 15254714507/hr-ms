package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.RegisterNewEmployeeCondition;
import com.hrms.api.domain.dto.DepartmentJob;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.RegisterNewEmployee;
import com.hrms.api.domain.vo.RegisterNewEmployeeVO;
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
import java.util.ArrayList;
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
            log.error("添加待审核的新员工出现异常 {}", JSON.toJSONString(registerNewEmployee), e);
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

    /**
     * 前往审核新员工页面
     */
    @RequestMapping("/gotoAuditEmployees.do")
    public String gotoAuditEmployees(Model model) {
        return "onboarding/auditEmployees";
    }

    @RequestMapping("/getAuditRegisterNewEmployeeList.do")
    @ResponseBody
    public List<RegisterNewEmployeeVO> getAuditRegisterNewEmployeeList() {
        List<RegisterNewEmployeeVO> registerNewEmployeeVOList = null;
        RegisterNewEmployeeCondition registerNewEmployeeCondition = new RegisterNewEmployeeCondition();
        registerNewEmployeeCondition.setLead(true);
        try {
            List<RegisterNewEmployee> registerNewEmployeesList = registerNewEmployeeService.list(registerNewEmployeeCondition);
            registerNewEmployeeVOList = getAuditRegisterNewEmployeeVOList(registerNewEmployeesList);
        } catch (Exception e) {
            log.error("在待审核员工页面获取员工集合时发生系统异常", e);
        }

        return registerNewEmployeeVOList;
    }

    /**
     * 审核的List<RegisterNewEmployee>转成List<RegisterNewEmployeeVO>
     *
     * @param registerNewEmployeesList
     * @return
     */
    private List<RegisterNewEmployeeVO> getAuditRegisterNewEmployeeVOList(List<RegisterNewEmployee> registerNewEmployeesList) {
        List<RegisterNewEmployeeVO> registerNewEmployeeVOList = new ArrayList<>();
        for (RegisterNewEmployee registerNewEmployee : registerNewEmployeesList) {
            if (registerNewEmployee.getApprovalStatus() == 0) {
                registerNewEmployeeVOList.add(getRegisterNewEmployeeVO(registerNewEmployee));
            }
        }
        return registerNewEmployeeVOList;
    }

    /**
     * RegisterNewEmployee转成RegisterNewEmployeeVO
     *
     * @param registerNewEmployee
     * @return
     */
    private RegisterNewEmployeeVO getRegisterNewEmployeeVO(RegisterNewEmployee registerNewEmployee) {
        RegisterNewEmployeeVO registerNewEmployeeVO = new RegisterNewEmployeeVO();
        registerNewEmployeeVO.setId(registerNewEmployee.getId());
        registerNewEmployeeVO.setUsername(registerNewEmployee.getUsername());
        registerNewEmployeeVO.setName(registerNewEmployee.getName());
        registerNewEmployeeVO.setDepartmentName(registerNewEmployee.getDepartmentName());
        registerNewEmployeeVO.setJobName(registerNewEmployee.getJobName());
        registerNewEmployeeVO.setBaseSalary(registerNewEmployee.getBaseSalary());
        registerNewEmployeeVO.setPerformanceSalary(registerNewEmployee.getPerformanceSalary());
        registerNewEmployeeVO.setDegree(registerNewEmployee.getDegree());
        if (registerNewEmployee.getGender() == 0) {
            registerNewEmployeeVO.setGender("男");
        } else {
            registerNewEmployeeVO.setGender("女");
        }
        if (registerNewEmployee.getApprovalStatus() == null || registerNewEmployee.getApprovalStatus() == 0) {
            registerNewEmployeeVO.setApprovalStatus("未审核");
            return registerNewEmployeeVO;
        }
        if (registerNewEmployee.getApprovalStatus() == 1) {
            registerNewEmployeeVO.setApprovalStatus("审核通过");
        }
        if (registerNewEmployee.getApprovalStatus() == 2) {
            registerNewEmployeeVO.setApprovalStatus("审核不通过");
        }
        return registerNewEmployeeVO;
    }

    /**
     * 待审核员工信息通过
     *
     * @param id
     * @param session
     * @return
     */
    @PostMapping("/passNewEmployees.do")
    @ResponseBody
    public Result passNewEmployees(Long id, HttpSession session) {
        Employees employees = (Employees) session.getAttribute("employees");
        RegisterNewEmployee registerNewEmployee = fillPassApproval(id, employees.getUsername());
        Result result = null;
        try {
            result = registerNewEmployeeService.pass(registerNewEmployee);
        } catch (Exception e) {
            log.error("通过待审核的新员工时出现异常 {}", JSON.toJSONString(registerNewEmployee), e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;

    }

    /**
     * 填充通过后需要填充的信息
     *
     * @param id
     * @param updateUser
     * @return
     */
    private RegisterNewEmployee fillPassApproval(Long id, String updateUser) {
        RegisterNewEmployee registerNewEmployee = new RegisterNewEmployee();
        registerNewEmployee.setId(id);
        registerNewEmployee.setApprovalStatus(1);
        registerNewEmployee.setApprovalComments("通过");
        registerNewEmployee.setApprovalUser(updateUser);
        registerNewEmployee.setUpdateUser(updateUser);
        return registerNewEmployee;
    }

    /**
     * 待审核员工信息通过
     *
     * @param registerNewEmployee
     * @param session
     * @return
     */
    @PostMapping("/failNewEmployees.do")
    @ResponseBody
    public Result failNewEmployees(@Valid RegisterNewEmployee registerNewEmployee, HttpSession session) {
        Employees employees = (Employees) session.getAttribute("employees");
        fillFailApproval(registerNewEmployee, employees.getUsername());
        Result result = null;
        try {
            result = registerNewEmployeeService.updateById(registerNewEmployee);
            if (result == null) {
                result = new Result(1, "原因填写完成");
            }
        } catch (Exception e) {
            log.error("不通过待审核的新员工时修改原因时出现异常 {}", JSON.toJSONString(registerNewEmployee), e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;

    }

    /**
     * 填充拒绝通过的数据
     *
     * @param registerNewEmployee
     * @param updateUser
     */
    private void fillFailApproval(RegisterNewEmployee registerNewEmployee, String updateUser) {
        registerNewEmployee.setApprovalUser(updateUser);
        registerNewEmployee.setUpdateUser(updateUser);
        //2代表的是不通过
        registerNewEmployee.setApprovalStatus(2);
    }

    /**
     * 前往入职新员工列表页面
     */
    @RequestMapping("/gotoRegisterNewEmployeesList.do")
    public String gotoRegisterNewEmployeesList() {
        return "onboarding/employeesList";
    }

    @RequestMapping("/getRegisterNewEmployeeList.do")
    @ResponseBody
    public List<RegisterNewEmployeeVO> getRegisterNewEmployeeList(HttpSession session) {
        List<RegisterNewEmployeeVO> registerNewEmployeeVOList = null;
        RegisterNewEmployeeCondition registerNewEmployeeCondition = new RegisterNewEmployeeCondition();
        Employees employees = (Employees) session.getAttribute("employees");
        registerNewEmployeeCondition.setCreateUser(employees.getUsername());
        try {
            List<RegisterNewEmployee> registerNewEmployeesList = registerNewEmployeeService.list(registerNewEmployeeCondition);
            registerNewEmployeeVOList = getRegisterNewEmployeeVOList(registerNewEmployeesList);
        } catch (Exception e) {
            log.warn("在待审核员工页面获取员工集合时发生系统异常", e);
        }

        return registerNewEmployeeVOList;
    }

    /**
     * 新入职列表 转换成List<RegisterNewEmployeeVO>
     *
     * @param registerNewEmployeesList
     * @return
     */
    private List<RegisterNewEmployeeVO> getRegisterNewEmployeeVOList(List<RegisterNewEmployee> registerNewEmployeesList) {
        List<RegisterNewEmployeeVO> registerNewEmployeeVOList = new ArrayList<>();
        for (RegisterNewEmployee registerNewEmployee : registerNewEmployeesList) {
            RegisterNewEmployeeVO registerNewEmployeeVO = getRegisterNewEmployeeVO(registerNewEmployee);
            if (registerNewEmployee.getApprovalComments() == null || "".equals(registerNewEmployee.getApprovalComments())) {
                registerNewEmployeeVO.setApprovalComments("无");
            } else {
                registerNewEmployeeVO.setApprovalComments(registerNewEmployee.getApprovalComments());
            }
            if (registerNewEmployee.getApprovalUser() == null || "".equals(registerNewEmployee.getApprovalUser())) {
                registerNewEmployeeVO.setApprovalUser("无");
            } else {
                registerNewEmployeeVO.setApprovalUser(registerNewEmployee.getUpdateUser());
            }
            registerNewEmployeeVO.setUpdateTime(registerNewEmployee.getUpdateTime());
            registerNewEmployeeVOList.add(registerNewEmployeeVO);
        }

        return registerNewEmployeeVOList;

    }

    /**
     * 前往入职登记新员工页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/gotoUpdateEmployees.do")
    public String gotoUpdateEmployees(Long id, Model model) {
        try {
            List<DepartmentJob> departmentJobList = jobService.listAllDepartment();
            model.addAttribute("departmentJobList", departmentJobList);
            RegisterNewEmployee registerNewEmployee = registerNewEmployeeService.getById(id);
            model.addAttribute("registerNewEmployee", registerNewEmployee);
        } catch (Exception e) {
            log.error("跳转到入职登记页面时系统发生异常", e);
            return "error/404";
        }
        return "onboarding/updateEmployees";
    }

    /**
     * 修改待审核员工信息
     *
     * @param registerNewEmployee
     * @param session
     * @return
     */
    @PostMapping("/updateNewEmployees.do")
    @ResponseBody
    public Result updateNewEmployees(@Valid RegisterNewEmployee registerNewEmployee, HttpSession session) {
        Result result = null;
        Employees employees = (Employees) session.getAttribute("employees");
        registerNewEmployee.setUpdateUser(employees.getUsername());
        try {
            result = registerNewEmployeeService.updateById(registerNewEmployee);
            if (result == null) {
                result = new Result(1, "修改信息成功");
            }
        } catch (Exception e) {
            log.error("修改新入职员工信息时发生系统异常 {}", JSON.toJSONString(registerNewEmployee), e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    @PostMapping("/resubmitNewEmployees.do")
    @ResponseBody
    public Result resubmitNewEmployees(Long id, HttpSession session) {
        Result result = null;
        Employees employees = (Employees) session.getAttribute("employees");
        RegisterNewEmployee registerNewEmployee = new RegisterNewEmployee();
        registerNewEmployee.setId(id);
        registerNewEmployee.setApprovalStatus(0);
        registerNewEmployee.setUpdateUser(employees.getUsername());
        try {
            result = registerNewEmployeeService.updateById(registerNewEmployee);
            if (result == null) {
                result = new Result(1, "重新提交审核成功");
            }
        } catch (Exception e) {
            log.error("修改待审核员工的审核状态出现系统异常 id {}", id, e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    @PostMapping("/deleteNewEmployees.do")
    @ResponseBody
    public Result deleteNewEmployees(Long id) {
        Result result = null;
        try {
            Long isSuc = registerNewEmployeeService.deleteById(id);
            if (isSuc == 1) {
                result = new Result(1, "删除成功");
            } else {
                result = new Result(0, "删除失败，因为不存在");
            }
        } catch (Exception e) {
            log.error("删除待审核员工出现系统异常 id {}", id, e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

}
