package com.hrms.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 孔超
 * @date 2020/4/19 16:34
 */
@Slf4j
@Controller
@RequestMapping("")
public class MainController {
    /**
     * 前往首页
     *
     * @return
     */
    @RequestMapping("/home.do")
    public String gotoHome() {

        return "main/home";

    }
}
