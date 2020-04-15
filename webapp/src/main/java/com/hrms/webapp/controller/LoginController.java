package com.hrms.webapp.controller;

import com.hrms.webapp.sumbit.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
    /**
     * 前往登录页面
     *
     * @return
     */
    @RequestMapping("/")
    public String gotoLogin() {
        return "Login";
    }

    @PostMapping("login.do")
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "Login";
        }

        return "";
    }
}
