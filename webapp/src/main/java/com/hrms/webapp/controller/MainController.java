package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.NoticeCondition;
import com.hrms.api.domain.condition.SignCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.Notice;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.service.NoticeService;
import com.hrms.api.service.SignService;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/19 16:34
 */
@Slf4j
@Controller
@RequestMapping("")
public class MainController {
    @Reference
    SignService signService;
    @Reference
    NoticeService noticeService;

    /**
     * 前往首页
     *
     * @return
     */
    @RequestMapping("/home.do")
    public String gotoHome(Model model) {
        NoticeCondition noticeCondition = new NoticeCondition();
        noticeCondition.setDeadline(LocalDateTimeFactory.getLocalDate());
        try {
            List<Notice> noticeList = noticeService.list(noticeCondition);
            model.addAttribute("noticeList", noticeList);
        } catch (Exception e) {
            log.error("获得首页的通知列表出现系统异常");
            return "error/404";
        }
        return "main/home";

    }

    @PostMapping("/querySign.do")
    @ResponseBody
    public Result querySign(HttpSession session) {
        Result result = null;
        SignCondition signCondition = new SignCondition();
        Employees employees = (Employees) session.getAttribute("employees");
        signCondition.setUsername(employees.getUsername());
        signCondition.setYear(LocalDateTimeFactory.getLocalDate().getYear());
        signCondition.setMonth(LocalDateTimeFactory.getLocalDate().getMonthValue());
        signCondition.setDay(LocalDateTimeFactory.getLocalDate().getDayOfMonth());
        try {
            List<Sign> signList = signService.listByYearMonthDay(signCondition);
            //上班签到成功返回的是1 上班没有签到是0
            if (signList != null && signList.size() > 0) {
                result = new Result(1, getSignStatus(signList.get(0)));
            } else {
                result = new Result(1, 0);
            }
        } catch (Exception e) {
            log.error("查询是否已签到时出现系统异常 signCondition{}", JSON.toJSONString(signCondition), e);
            result = new Result(-1, "签到状态查询失败，请刷新后重试");
        }
        return result;
    }

    /**
     * 获得签到的状态，0是未签到、1已上班、2下班签到，3已下班
     *
     * @param sign
     * @return
     */
    private Integer getSignStatus(Sign sign) {
        //上班签到后还要有下班签到。
        if (sign.getGetOffWork() != null) {
            return 3;
        }
        //晚上18点开始下班签到,否则说明还在已上班
        if (LocalDateTimeFactory.getLocalDateTime().getHour() >= 18) {
            return 2;
        } else {
            return 1;
        }
    }
    @RequestMapping("/gotoNoticeContent.do")
    public String gotoNoticeContent(Long id,Model model){
        if(id==null||id<1){
            return "error/404";
        }
        try{
            Notice notice = noticeService.getById(id);
            if(notice == null){
                return "error/404";
            }
            model.addAttribute("notice",notice);
        }catch (Exception e){
            log.error("根据id获得notice对象发生系统异常 id{}",id,e);
            return "error/404";
        }
        return "main/noticeContent";
    }
}
