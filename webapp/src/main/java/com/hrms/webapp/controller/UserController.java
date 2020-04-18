package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hrms.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
@Controller
public class UserController {
    @Reference//这不再用	@Autowired，因为你没实现类怎么注入
    private UserService userService;

    @RequestMapping("/222")
    public String findAll(Model model, HttpSession session) throws Exception {

        return "index";

    }
}
