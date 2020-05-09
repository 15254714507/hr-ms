package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hrms.api.domain.condition.WagesCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.Wages;
import com.hrms.api.service.WagesService;
import com.hrms.api.until.Result;
import com.hrms.webapp.sumbit.CheckWagesForm;
import com.hrms.webapp.until.WordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 孔超
 * @date 2020/5/7 19:42
 */
@Slf4j
@Controller
@RequestMapping("")
public class WagesController {
    @Reference
    WagesService wagesService;

    /**
     * 前往核对工资信息页面
     *
     * @return
     */
    @RequestMapping("/gotoCheckWagesList.do")
    public String gotoCheckWages() {
        return "wages/checkWagesList";
    }

    @PostMapping("/getCheckWagesList.do")
    @ResponseBody
    public List<Wages> getCheckWagesList() {
        List<Wages> wagesList = null;
        LocalDate date = LocalDate.now();
        date = date.minusMonths(1);
        WagesCondition wagesCondition = new WagesCondition();
        wagesCondition.setYear(date.getYear());
        wagesCondition.setMonth(date.getMonthValue());
        try {
            wagesList = wagesService.listCheckWages(wagesCondition);
        } catch (Exception e) {
            log.error("获得上个月需要核对的工资信息出现系统异常wagesCondition{}", wagesCondition, e);
        }
        return wagesList;
    }

    /**
     * 前往核对工资页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/gotoCheckWages.do")
    public String gotoCheckWages(Long id, Model model) {
        try {
            Wages wages = wagesService.getById(id);
            if (wages == null) {
                log.warn("没有此工资信息的 id{}", id);
                return "error/404";
            }
            model.addAttribute("wages", wages);
        } catch (Exception e) {
            log.error("获得需要核对的工资信息出现系统异常 id{}", id, e);
            return "error/404";
        }
        return "wages/checkWages";
    }

    /**
     * 提交核对完成后的工资信息
     *
     * @param checkWagesForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping("/checkWages.do")
    @ResponseBody
    public Result checkWages(@Valid CheckWagesForm checkWagesForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new Result(0, "信息不完整");
        }
        Employees employees = (Employees) session.getAttribute("employees");
        Wages wages = tansCheckWagesForm(checkWagesForm, employees.getUsername());
        Result result = null;
        try {
            result = wagesService.submitCheckWages(wages);
        } catch (Exception e) {
            log.error("提交核对后的工资信息发生系统异常，wages{}", wages, e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    /**
     * checkWagesForm转换成wages
     *
     * @param checkWagesForm
     * @param updateUser
     * @return
     */
    private Wages tansCheckWagesForm(CheckWagesForm checkWagesForm, String updateUser) {
        Wages wages = new Wages();
        wages.setId(checkWagesForm.getId());
        wages.setDaysOfRecess(checkWagesForm.getDaysOfRecess());
        wages.setDaysOfLeave(checkWagesForm.getDaysOfLeave());
        wages.setDaysOfSickLeave(checkWagesForm.getDaysOfSickLeave());
        wages.setDaysOfAbsenteeism(checkWagesForm.getDaysOfAbsenteeism());
        wages.setUpdateUser(updateUser);
        wages.setStatus(1);
        return wages;
    }

    @RequestMapping("/gotoReedWages.do")
    public String gotoReedWages(Long id, Model model) {
        try {
            Wages wages = wagesService.getById(id);
            if (wages == null) {
                log.warn("没有此工资信息的 id{}", id);
                return "error/404";
            }
            model.addAttribute("wages", wages);
        } catch (Exception e) {
            log.error("获得需要重新编辑的工资信息出现系统异常 id{}", id, e);
            return "error/404";
        }
        return "wages/reedWages";
    }

    /**
     * 提交核对完成后的工资信息
     *
     * @param checkWagesForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping("/reedWages.do")
    @ResponseBody
    public Result reedWages(@Valid CheckWagesForm checkWagesForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors() || checkWagesForm.getBaseSalary() == null || checkWagesForm.getPerformanceSalary() == null) {
            return new Result(0, "信息不完整");
        }
        Employees employees = (Employees) session.getAttribute("employees");
        Wages wages = tansCheckWagesForm(checkWagesForm, employees.getUsername());
        wages.setBaseSalary(checkWagesForm.getBaseSalary());
        wages.setPerformanceSalary(checkWagesForm.getPerformanceSalary());
        Result result = null;
        try {
            result = wagesService.reedCheckWages(wages);
        } catch (Exception e) {
            log.error("提交重新编辑后的工资信息发生系统异常，wages{}", wages, e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    @RequestMapping("/gotoWagesList.do")
    public String gotoWagesList() {
        return "wages/wagesList";
    }

    @PostMapping("/getWagesList.do")
    @ResponseBody
    public List<Wages> getWagesList() {
        List<Wages> wagesList = null;
        WagesCondition wagesCondition = new WagesCondition();
        wagesCondition.setStatus(1);
        try {
            wagesList = wagesService.list(wagesCondition);
        } catch (Exception e) {
            log.error("获得所有工资信息状态为1的集合出现系统异常", e);
        }
        return wagesList;
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/downWages.do")
    @ResponseBody
    public ResponseEntity<byte[]> downWages(Long id) {
        byte[] fileByte = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            Map<String, Object> dataMap = getDataMap(id);
            fileByte = WordUtil.createWordByte(dataMap, "payBar.ftl", "工资条.doc");
            headers.setContentDispositionFormData("attachment", URLEncoder.encode("工资条.doc", "utf-8"));
        } catch (Exception e) {
            log.error("下载离职证明失败 id:{}", id, e);
        }
        return new ResponseEntity<byte[]>(fileByte, headers, HttpStatus.CREATED);
    }

    /**
     * 获得打印数据
     *
     * @param id
     * @return
     */
    private Map<String, Object> getDataMap(Long id) {
        Map<String, Object> dataMap = new HashMap<String, Object>(3);
        Wages wages = wagesService.getById(id);
        if (wages == null) {
            return dataMap;
        }
        String wagesDate = wages.getWagesDate().getYear() + "年" + wages.getWagesDate().getMonthValue() + "月";
        dataMap.put("wagesDate", wagesDate);
        dataMap.put("wages", wages);
        return dataMap;
    }
}
