package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.User;
import com.hrms.api.service.EmployeesService;
import com.hrms.webapp.sumbit.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author 孔超
 * @date 2020/4/12 15:54
 */
@Slf4j
@Controller
@RequestMapping("")
public class LoginController {

    @Reference//这不再用	@Autowired，因为你没实现类怎么注入
    private EmployeesService employeesService;

    /**
     * 前往登录页面
     *
     * @return
     */
    @RequestMapping("/")
    public String gotoLogin(HttpSession session, Model model) {
        Employees employees = (Employees) session.getAttribute("employees");
        if (employees == null) {
            return "Login";
        }
        model.addAttribute("employees", employees);
        return "main/main";

    }

    /**
     * 登录
     *
     * @param loginForm
     * @param bindingResult
     * @param session
     * @param model
     * @return
     */
    @PostMapping("login.do")
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "Login";
        }
        User user = getUser(loginForm);
        try {
            Employees employees = employeesService.login(user);
            session.setAttribute("employees", employees);
            model.addAttribute("employees", employees);
            if (employees == null) {
                return "Login";
            }
        } catch (Exception e) {
            log.error("发生系统异常", e);
            return "Login";
        }
        return "main/main";
    }

    /**
     * loginForm转换成user
     *
     * @param loginForm
     * @return
     */
    private User getUser(LoginForm loginForm) {
        User user = new User();
        user.setUsername(loginForm.getUsername());
        user.setPassword(loginForm.getPassword());
        return user;
    }
}
