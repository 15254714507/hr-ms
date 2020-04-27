package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.DimissionUser;
import com.hrms.api.service.DimissionUserService;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author 孔超
 * @date 2020/4/26 2:41
 */
@Slf4j
@Controller
@RequestMapping("")
public class DimissionController {
    @Reference
    DimissionUserService dimissionUserService;

    @RequestMapping("/gotoDimissionApplication.do")
    public String gotoDimissionApplication() {
        return "dimission/dimissionApplication";
    }

    @PostMapping("/getDimissionUserMsg.do")
    @ResponseBody
    public Result getDimissionUserMsg(String username) {
        if (username == null || "".equals(username)) {
            return new Result(1, null);
        }
        Result result = null;
        try {
            DimissionUser dimissionUser = dimissionUserService.getDimissionUserMsgByUsername(username);
            result = new Result(1, dimissionUser);
        } catch (Exception e) {
            log.error("查询离职员工信息时出现错误 username {}", username, e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    @PostMapping("/saveDimissionUser.do")
    @ResponseBody
    public Result saveDimissionUser(@Valid DimissionUser dimissionUser, HttpSession session) {
        Result result = null;
        Employees employees = (Employees) session.getAttribute("employees");
        fillDimissionUser(dimissionUser, employees.getUsername());
        try {
            result = dimissionUserService.insert(dimissionUser);
        } catch (Exception e) {
            log.error("申请离职时发生系统异常 dimissionUser {}", JSON.toJSONString(dimissionUser), e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    /**
     * 填充DimissionUser创建者和离职时间
     *
     * @param dimissionUser
     * @param createUser
     */
    private void fillDimissionUser(DimissionUser dimissionUser, String createUser) {
        dimissionUser.setCreateUser(createUser);
        dimissionUser.setUpdateUser(createUser);
        dimissionUser.setDateOfSeparation(LocalDateTimeFactory.getLocalDate());
    }

}
