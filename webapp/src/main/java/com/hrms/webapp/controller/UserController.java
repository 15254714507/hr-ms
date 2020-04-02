package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

/**
 * @author lenovo
 */
@Controller
public class UserController {
    @Reference//这不再用	@Autowired，因为你没实现类怎么注入
    private UserService userService;

    @RequestMapping("/")
    public String findAll(Model model) throws Exception {
        String a=userService.test();
        model.addAttribute("a",a);
        return "index";

    }
}
