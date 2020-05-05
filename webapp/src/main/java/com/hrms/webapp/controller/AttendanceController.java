package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.condition.SignCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.RequestForLeave;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.domain.entity.User;
import com.hrms.api.service.RequestForLeaveService;
import com.hrms.api.service.SignService;
import com.hrms.api.service.UserService;
import com.hrms.api.until.Result;
import com.hrms.webapp.sumbit.AttendanceExceptionForm;
import com.hrms.webapp.sumbit.RequestVacationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    UserService userService;
    @Reference
    RequestForLeaveService requestForLeaveService;
    @Reference
    SignService signService;

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
        if (username == null) {
            return new Result(1, null);
        }
        Result result = new Result(1, null);
        try {
            User user = userService.getByUsername(username);
            if (user != null) {
                result.setObject(user.getName());
            }
        } catch (Exception e) {
            log.error("根据账号获取用户姓名出现系统异常 username{}", username, e);
            result = new Result(-1, "获取用户姓名异常");
        }

        return result;
    }

    @RequestMapping("/gotoAddNewVacation.do")
    public String gotoAddNewVacation() {
        return "attendance/addRequestVacation";
    }

    @PostMapping("/saveNewVacation.do")
    @ResponseBody
    public Result saveNewVacation(@Valid RequestVacationForm requestVacationForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new Result(0, "信息不完全，请重新提交");
        }
        Result result = null;
        Employees employees = (Employees) session.getAttribute("employees");
        RequestForLeave requestForLeave = transRequestVacationForm(requestVacationForm, employees.getUsername());
        try {
            result = requestForLeaveService.insert(requestForLeave);
        } catch (Exception e) {
            log.error("添加新的假期申请出现系统异常 requestVacationForm{}", JSON.toJSONString(requestVacationForm), e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;

    }

    /**
     * requestVacationForm转换成requestForLeave
     *
     * @param requestVacationForm
     * @param createUser
     * @return
     */
    private RequestForLeave transRequestVacationForm(RequestVacationForm requestVacationForm, String createUser) {
        RequestForLeave requestForLeave = new RequestForLeave();
        requestForLeave.setUsername(requestVacationForm.getUsername());
        requestForLeave.setName(requestVacationForm.getName());
        requestForLeave.setStartDate(requestVacationForm.getStartDate());
        requestForLeave.setEndDate(requestVacationForm.getEndDate());
        requestForLeave.setDescription(requestVacationForm.getDescription());
        //调休
        if (requestVacationForm.getType() == 0) {
            requestForLeave.setDaysOfRecess(requestVacationForm.getDays().doubleValue());
        }
        //事假
        if (requestVacationForm.getType() == 1) {
            requestForLeave.setDaysOfLeave(requestVacationForm.getDays().doubleValue());
        }
        //病假
        if (requestVacationForm.getType() == 2) {
            requestForLeave.setDaysOfSickLeave(requestVacationForm.getDays().doubleValue());
        }
        requestForLeave.setCreateUser(createUser);
        requestForLeave.setUpdateUser(createUser);
        return requestForLeave;
    }

    @PostMapping("/deleteRequestVacation.do")
    @ResponseBody
    public Result deleteRequestVacation(Long id) {
        Result result = null;
        try {
            Long isSuc = requestForLeaveService.deleteById(id);
            if (isSuc != 1) {
                return new Result(0, "没有此请假申请");
            } else {
                result = new Result(1, "删除请假申请完成");
            }
        } catch (Exception e) {
            log.error("删除请假申请出现系统异常 id{}", id, e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    @PostMapping("/passRequestVacation.do")
    @ResponseBody
    public Result passRequestVacation(Long id, HttpSession session) {
        Result result = null;
        RequestForLeave requestForLeave = new RequestForLeave();
        requestForLeave.setId(id);
        requestForLeave.setStatus(true);
        Employees employees = (Employees) session.getAttribute("employees");
        requestForLeave.setAuditUser(employees.getUsername());
        requestForLeave.setUpdateUser(employees.getUsername());
        try {
            Long isSuc = requestForLeaveService.updateById(requestForLeave);
            if (isSuc != 1) {
                return new Result(0, "没有此请假申请");
            } else {
                result = new Result(1, "已通过请假申请");
            }
        } catch (Exception e) {
            log.error("通过请假申请出现系统异常 id{}", id, e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    /**
     * 前往考勤异常页面
     *
     * @return
     */
    @RequestMapping("/gotoAttendanceExceptionList.do")
    public String gotoAttendanceExceptionList() {
        return "attendance/attendanceExceptionList";
    }

    @PostMapping("/getAttendanceExceptionList.do")
    @ResponseBody
    public List<Sign> getAttendanceExceptionList() {
        List<Sign> signList = null;
        SignCondition signCondition = new SignCondition();
        signCondition.setStatus(true);
        try {
            signList = signService.list(signCondition);
        } catch (Exception e) {
            log.error("获得考勤异常列表出现系统异常", e);
        }
        return signList;
    }

    /**
     * 考勤异常通过
     *
     * @param id
     * @param session
     * @return
     */
    @PostMapping("/passSign.do")
    @ResponseBody
    public Result passSign(Long id, HttpSession session) {
        Result result = null;
        Sign sign = new Sign();
        sign.setId(id);
        sign.setStatus(false);
        Employees employees = (Employees) session.getAttribute("employees");
        sign.setUpdateUser(employees.getUsername());
        try {
            Long isSuc = signService.updateById(sign);
            if (isSuc != 1) {
                result = new Result(1, "此异常考勤通过");
            } else {
                result = new Result(0, "没有此考勤异常记录");
            }
        } catch (Exception e) {
            log.error("考勤异常确认正确时发生系统异常 id{}", id, e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    /**
     * 前往修改考勤异常的签到信息页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/gotoUpdateAttendanceException.do")
    public String gotoUpdateAttendanceException(Long id, Model model) {
        try {
            Sign sign = signService.getById(id);
            if (sign.getStatus()) {
                model.addAttribute("sign", sign);
            }
        } catch (Exception e) {
            log.error("前往修改考勤异常的签到出现系统异常 id{}", id, e);
            return "error/404";
        }
        return "attendance/updateAttendanceException";
    }

    @PostMapping("/updateAttendanceException.do")
    @ResponseBody
    public Result updateAttendanceException(@Valid AttendanceExceptionForm attendanceExceptionForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new Result(0, "信息不完整，请重新修改");
        }
        Employees employees = (Employees) session.getAttribute("employees");
        Sign sign = transToSign(attendanceExceptionForm, employees.getUsername());
        Result result = null;
        try {
            Long isSuc = signService.updateById(sign);
            if (isSuc != 1) {
                result = new Result(0, "没有此异常考勤记录");
            } else {
                result = new Result(1, "修改成功");
            }
        } catch (Exception e) {
            log.error("修改考勤异常的信息出现系统异常 sign{}", JSON.toJSONString(sign), e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    /**
     * attendanceExceptionForm转换成Sign
     *
     * @param attendanceExceptionForm
     * @param updateUser
     * @return
     */
    private Sign transToSign(AttendanceExceptionForm attendanceExceptionForm, String updateUser) {
        Sign sign = new Sign();
        sign.setId(attendanceExceptionForm.getId());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        sign.setWorkTime(LocalDateTime.parse(attendanceExceptionForm.getWorkTime().replaceAll("T", " ") + ":00", df));
        sign.setGetOffWork(LocalDateTime.parse(attendanceExceptionForm.getGetOffWork().replaceAll("T", " ") + ":00", df));
        sign.setDescription(attendanceExceptionForm.getReason());
        sign.setUpdateUser(updateUser);
        sign.setStatus(false);
        return sign;
    }

    @RequestMapping("/gotoAttendanceList.do")
    public String gotoAttendanceList() {
        return "attendance/attendanceList";
    }

    @RequestMapping("/getAttendanceList.do")
    @ResponseBody
    public List<Sign> getAttendanceList() {
        List<Sign> signList = null;
        SignCondition signCondition = new SignCondition();
        signCondition.setStatus(false);
        try {
            signList = signService.list(signCondition);
        } catch (Exception e) {
            log.error("获得考勤记录列表时发生系统异常", e);
        }
        return signList;
    }

}
