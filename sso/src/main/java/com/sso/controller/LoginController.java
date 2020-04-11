package com.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 孔超
 * @date 2020/4/9 0:01
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @GetMapping("/")
    public String index() {
        return "Login";
    }
}
