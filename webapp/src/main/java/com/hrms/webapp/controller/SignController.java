package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.service.SignService;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 孔超
 * @date 2020/5/3 16:59
 */
@Slf4j
@Controller
@RequestMapping("")
public class SignController {
    @Reference
    SignService signService;


    /**
     * 上班签到
     *
     * @param session
     * @return
     */
    @PostMapping("/saveSign.do")
    @ResponseBody
    public Result saveSign(HttpSession session) {
        Result result = null;
        Sign sign = new Sign();
        Employees employees = (Employees) session.getAttribute("employees");
        sign.setCreateUser(employees.getUsername());
        sign.setUpdateUser(employees.getUsername());
        sign.setUsername(employees.getUsername());
        try {
            result = signService.insert(sign);
        } catch (Exception e) {
            log.error("创建新的签到时出现系统异常employees{}", JSON.toJSONString(employees), e);
            result = new Result(-1, "系统异常，请重新尝试");
        }
        return result;
    }

    /**
     * 下班签到
     *
     * @return
     */
    @PostMapping("/updateSignGetOffWork.do")
    @ResponseBody
    public Result updateSignGetOffWork(HttpSession session) {
        Result result = null;
        Sign sign = new Sign();
        Employees employees = (Employees) session.getAttribute("employees");
        sign.setUpdateUser(employees.getUsername());
        sign.setUsername(employees.getUsername());
        try {
            result = signService.nightSign(sign);
        } catch (Exception e) {
            log.error("添加晚上下班的签到记录时发生系统异常employees{}", JSON.toJSONString(employees), e);
            result = new Result(-1, "系统异常，请重新尝试");
        }
        return result;
    }
}
