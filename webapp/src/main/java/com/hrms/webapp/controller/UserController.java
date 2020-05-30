package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.User;
import com.hrms.api.service.UserService;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
@Slf4j
@RequestMapping("")
@Controller
public class UserController {
    @Reference//这不再用	@Autowired，因为你没实现类怎么注入
    private UserService userService;

    @RequestMapping("/gotoPersonalInformation.do")
    public String gotoPersonalInformation(HttpSession session, Model model) {
        Employees employees = (Employees) session.getAttribute("employees");
        model.addAttribute("employees", employees);
        return "main/personalInformation";
    }

    @PostMapping("/updateUserHeadShot.do")
    @ResponseBody
    public Result updateUserHeadShot(@RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        if (file != null) {
            String originalName = file.getOriginalFilename();
            //获取文件.再的位置，好组成新的名称时就直接在末尾添加上格式就行了
            int index = originalName.lastIndexOf(".");
            //组成一个新的图片名称，用uuid这样避免重复
            String newFileName = UUID.randomUUID() + originalName.substring(index);
            //获取应用所在的服务的文件路径
            ServletContext application = session.getServletContext();
            String path = application.getRealPath("/");
            File imagFile = new File(path + "static/head_shot/" + newFileName);
            System.out.println(path + "static/head_shot/" + newFileName);
            //写到指定路径了
//            file.transferTo(imagFile);
            //现在图片已经上传到Tomcat的此项目名下的uploadimage文件夹下

        }
        return new Result(1, "11");
    }

    @PostMapping("/updateUserPassword.do")
    @ResponseBody
    public Result updateUserPassword(String password, HttpSession session) {
        if (password == null || password.length() < 1) {
            return new Result(0, "请输入新密码");
        }
        Result result = null;
        Employees employees = (Employees) session.getAttribute("employees");
        User user = new User();
        user.setId(employees.getUserId());
        user.setPassword(password);
        user.setCreateUser(employees.getUsername());
        user.setUpdateUser(employees.getUsername());
        try {
            Long isSuc = userService.updateById(user);
            if (isSuc != 1) {
                result = new Result(0, "您的账号不存在，请联系运营人员");
            } else {
                result = new Result(1, "修改密码成功，半小时后生效");
            }
        } catch (Exception e) {
            log.error("修改用户密码时出现系统异常 user{}", JSON.toJSONString(user), e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }
}
