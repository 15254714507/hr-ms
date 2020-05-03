package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.entity.RequestForLeave;
import com.hrms.api.service.RequestForLeaveService;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/4 0:41
 */
@Slf4j
@Controller
@RequestMapping("")
public class AttendanceController {
    @Reference
    RequestForLeaveService requestForLeaveService;

    @RequestMapping("/gotoRequestVacationList.do")
    public String gotoRequestVacationList() {
        return "attendance/requestVacationList";
    }

    @PostMapping("/getVacationList.do")
    @ResponseBody
    public List<RequestForLeave> getVacationList() {
        List<RequestForLeave> requestForLeaveList = null;
        RequestForLeaveCondition requestForLeaveCondition = new RequestForLeaveCondition();
        try {
            requestForLeaveList = requestForLeaveService.list(requestForLeaveCondition);
        } catch (Exception e) {
            log.error("获得假期列表出现系统异常", e);
        }
        return requestForLeaveList;
    }

    @PostMapping("/getVacationUserName.do")
    @ResponseBody
    public Result getVacationUserName(String username) {
        return null;
    }
}
