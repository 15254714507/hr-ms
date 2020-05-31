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
    public String gotoNoticeContent(Long id, Model model) {
        if (id == null || id < 1) {
            return "error/404";
        }
        try {
            Notice notice = noticeService.getById(id);
            if (notice == null) {
                return "error/404";
            }
            model.addAttribute("notice", notice);
        } catch (Exception e) {
            log.error("根据id获得notice对象发生系统异常 id{}", id, e);
            return "error/404";
        }
        return "main/noticeContent";
    }

    @RequestMapping("/gotoAddNotice.do")
    public String gotoAddNotice() {
        return "main/addNotice";
    }

    @PostMapping("/saveNotice.do")
    @ResponseBody
    public Result saveNotice(Notice notice, HttpSession session) {
        if (notice == null || notice.getTitle() == null || notice.getContent() == null || notice.getDeadline() == null) {
            return new Result(0, "信息不全，请重新填写");
        }
        Employees employees = (Employees) session.getAttribute("employees");
        notice.setCreateUser(employees.getUsername());
        notice.setUpdateUser(employees.getUsername());
        try {
            //插入不成功就会抛异常的
            noticeService.insert(notice);
            return new Result(1, "添加成功");
        } catch (Exception e) {
            log.error("添加新的通知失败 notice{}", notice, e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
    }

    @RequestMapping("/gotoNoticeList.do")
    public String gotoNoticeList() {
        return "main/noticeList";
    }

    @PostMapping("/getNoticeList.do")
    @ResponseBody
    public List<Notice> getNoticeList() {
        NoticeCondition noticeCondition = new NoticeCondition();
        List<Notice> noticeList = null;
        try {
            noticeList = noticeService.list(noticeCondition);
        } catch (Exception e) {
            log.error("获得通知历史列表出现系统异常", e);
        }
        return noticeList;
    }

    @PostMapping("/deleteNotice.do")
    @ResponseBody
    public Result deleteNotice(Long id) {
        if (id == null || id < 1) {
            return new Result(0, "删除的id不存在");
        }
        Result result = null;
        try {
            Long isSuc = noticeService.deleteById(id);
            if (isSuc != 1) {
                result = new Result(0, "没有此通知记录");
            } else {
                result = new Result(1, "删除成功");
            }
        } catch (Exception e) {
            log.error("删除通知出现系统异常 id{}", id);
        }
        return result;
    }
}
