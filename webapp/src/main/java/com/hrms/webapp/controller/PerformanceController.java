package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.PerformanceCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.dto.UserPerformance;
import com.hrms.api.domain.entity.Performance;
import com.hrms.api.service.PerformanceService;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/30 21:49
 */
@Slf4j
@Controller
@RequestMapping("")
public class PerformanceController {
    @Reference
    PerformanceService performanceService;

    /**
     * 前往绩效考核项添加页面
     *
     * @return
     */
    @RequestMapping("/gotoAddAssessment.do")
    public String gotoAddAssessment() {
        return "performance/addAssessment";
    }

    @PostMapping("/getNotAddPerformanceList.do")
    @ResponseBody
    public List<UserPerformance> getNotAddPerformanceList() {
        List<UserPerformance> userPerformanceList = null;
        PerformanceCondition performanceCondition = new PerformanceCondition();
        performanceCondition.setYear(LocalDateTimeFactory.getLocalDate().getYear());
        performanceCondition.setMonth(LocalDateTimeFactory.getLocalDate().getMonth().getValue());
        performanceCondition.setStatus(false);
        try {
            userPerformanceList = performanceService.listUserPerformance(performanceCondition);
        } catch (Exception e) {
            log.error("获得当月状态为0的绩效信息时出现系统异常", e);
        }
        return userPerformanceList;
    }

    @PostMapping("/savePerformanceGoal.do")
    @ResponseBody
    public Result savePerformanceGoal(@Valid Performance performance, HttpSession session) {
        Result result = null;
        Employees employees = (Employees) session.getAttribute("employees");
        performance.setUpdateUser(employees.getUsername());
        performance.setStatus(true);
        try {
            Long isSuc = performanceService.updateById(performance);
            if (isSuc != 1) {
                result = new Result(0, "没有此人的绩效信息");
            } else {
                result = new Result(1, "添加考核项成功");
            }
        } catch (Exception e) {
            log.error("绩效添加考核项时出现系统异常performance{}", JSON.toJSONString(performance), e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    @RequestMapping("/gotoPerformanceKpi.do")
    public String gotoPerformanceKpi() {
        return "performance/addKpi";
    }

    @PostMapping("/getAddPerformanceKpiList.do")
    @ResponseBody
    public List<UserPerformance> getAddPerformanceKpiList() {
        List<UserPerformance> userPerformanceList = null;
        LocalDate lastDay = LocalDateTimeFactory.getLocalDate().with(TemporalAdjusters.lastDayOfMonth());
//        //每个月的最后两天才允许填写kpi
//        if (LocalDateTimeFactory.getLocalDate().getDayOfMonth() < lastDay.getDayOfMonth() - 1) {
//            return null;
//        }
        PerformanceCondition performanceCondition = new PerformanceCondition();
        performanceCondition.setYear(LocalDateTimeFactory.getLocalDate().getYear());
        performanceCondition.setMonth(LocalDateTimeFactory.getLocalDate().getMonth().getValue());
        performanceCondition.setStatus(true);
        try {
            userPerformanceList = performanceService.listUserPerformance(performanceCondition);
        } catch (Exception e) {
            log.error("获得当月状态为0的绩效信息时出现系统异常", e);
        }
        return userPerformanceList;
    }

    @PostMapping("/savePerformanceKpi.do")
    @ResponseBody
    public Result savePerformanceKpi(@Valid Performance performance, HttpSession session) {
        Result result = null;
        Employees employees = (Employees) session.getAttribute("employees");
        performance.setUpdateUser(employees.getUsername());
        performance.setAuditUser(employees.getUsername());
        try {
            Long isSuc = performanceService.updateById(performance);
            if (isSuc != 1) {
                result = new Result(0, "没有此人的绩效信息");
            } else {
                result = new Result(1, "当月KPI设置完成");
            }
        } catch (Exception e) {
            log.error("绩效添加kpi时出现系统异常performance{}", JSON.toJSONString(performance), e);
            return new Result(-1, "系统异常，请刷新后重试");
        }
        return result;

    }

}
