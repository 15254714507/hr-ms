package com.hrms.support.scheduleTask;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.condition.SignCondition;
import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.entity.RequestForLeave;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.domain.entity.User;
import com.hrms.api.service.RequestForLeaveService;
import com.hrms.api.service.SignService;
import com.hrms.api.service.UserService;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 考勤表的定时任务
 *
 * @author 孔超
 * @date 2020/5/3 17:21
 */
@Slf4j
@Component
@EnableScheduling
public class SignScheduleTask {

    private static final String CREATE_USER = "System";
    @Resource
    UserService userService;
    @Resource
    SignService signService;
    @Resource
    RequestForLeaveService requestForLeaveService;

    /**
     * 考勤表的定时任务，每天晚上11点半执行，把当天的考勤整理完
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    @Scheduled(cron = "0 30 23 * * ?")
    public void signScheduleTaskLogin() {
        //如果是星期6或者星期7就不用整理考勤
        if (LocalDateTimeFactory.getLocalDate().getDayOfWeek().getValue() > 5) {
            return;
        }
        UserCondition userCondition = new UserCondition();
        List<User> userList = userService.list(userCondition);
        for (User user : userList) {
            finishSign(user);
        }
    }

    /**
     * 签到记录整理
     *
     * @param user
     */
    private void finishSign(User user) {
        SignCondition signCondition = new SignCondition();
        signCondition.setUsername(user.getUsername());
        LocalDate today = LocalDateTimeFactory.getLocalDate();
        signCondition.setYear(today.getYear());
        signCondition.setMonth(today.getMonthValue());
        signCondition.setDay(today.getDayOfMonth());
        List<Sign> signList = signService.listByYearMonthDay(signCondition);
        //存在签到记录
        if (signList != null && signList.size() > 0) {
            //没有第一次上班签到说明按旷工算了
            if (signList.get(0).getWorkTime() == null) {
                return;
            }
            yesSign(signList.get(0));
        } else {
            //不存在签到记录时
            noSign(user);
        }
    }

    /**
     * 不存在签到记录时
     *
     * @param user
     */
    private void noSign(User user) {
        RequestForLeaveCondition requestForLeaveCondition = new RequestForLeaveCondition();
        requestForLeaveCondition.setUsername(user.getUsername());
        requestForLeaveCondition.setStartDate(LocalDateTimeFactory.getLocalDate());
        requestForLeaveCondition.setEndDate(LocalDateTimeFactory.getLocalDate());
        List<RequestForLeave> requestForLeaveList = requestForLeaveService.list(requestForLeaveCondition);
        //创建一个空白的签到信息
        insertSign(user.getUsername());
        //如果有请假记录
        if (requestForLeaveList != null && requestForLeaveList.size() > 0) {
            updateSignVacation(requestForLeaveList.get(0));
        } else {
            updateSignAbsenteeism(user.getUsername());
        }
    }

    /**
     * 根据账号创建一个新的空白的当天的签到记录
     *
     * @param username
     */
    @Transactional(rollbackFor = Exception.class)
    void insertSign(String username) {
        Sign sign = new Sign();
        sign.setUsername(username);
        sign.setCreateUser(CREATE_USER);
        sign.setUpdateUser(CREATE_USER);
        try {
            Result result = signService.insert(sign);
            if (result.getCode() != 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } catch (Exception e) {
            log.error("创建空白签到信息(用于旷工记录)时发生系统异常username{}", username, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    private List<Sign> getSignList(String username) {
        SignCondition signCondition = new SignCondition();
        signCondition.setUsername(username);
        LocalDate today = LocalDateTimeFactory.getLocalDate();
        signCondition.setYear(today.getYear());
        signCondition.setMonth(today.getMonthValue());
        signCondition.setDay(today.getDayOfMonth());
        return signService.listByYearMonthDay(signCondition);
    }

    /**
     * 根据申请的假修改签到信息
     *
     * @param requestForLeave
     */
    private void updateSignVacation(RequestForLeave requestForLeave) {
        List<Sign> signList = getSignList(requestForLeave.getUsername());
        if (signList == null || signList.size() < 1) {
            log.error("查询新建的空白签到信息没有 username{}", requestForLeave.getUsername());
            return;
        }
        Sign sign = signList.get(0);
        sign.setStatus(false);
        if (requestForLeave.getDaysOfRecess() != null && requestForLeave.getDaysOfRecess() > 0) {
            sign.setDescription("调休");
        }
        if (requestForLeave.getDaysOfLeave() != null && requestForLeave.getDaysOfLeave() > 0) {
            sign.setDescription("事假");
        }
        if (requestForLeave.getDaysOfSickLeave() != null && requestForLeave.getDaysOfSickLeave() > 0) {
            sign.setDescription("病假");
        }
        sign.setUpdateUser(CREATE_USER);
        updateSign(sign);
    }

    /**
     * 修改签到信息为旷工
     */
    private void updateSignAbsenteeism(String username) {
        List<Sign> signList = getSignList(username);
        if (signList == null || signList.size() < 1) {
            log.error("查询新建的空白签到信息没有username{}", username);
            return;
        }
        Sign sign = signList.get(0);
        sign.setStatus(true);
        sign.setDescription("旷工");
        updateSign(sign);
    }

    /**
     * 存在签到记录时
     *
     * @param sign
     */
    private void yesSign(Sign sign) {
        //早上上班时间
        LocalDateTime startTime = LocalDateTime.of(sign.getWorkTime().getYear(), sign.getWorkTime().getMonthValue(), sign.getWorkTime().getDayOfMonth(), 9, 0, 0);
        //说明是早上9点以后上班的，迟到了
        if (sign.getWorkTime().isAfter(startTime)) {
            sign.setStatus(true);
            if (sign.getDescription() == null || "".equals(sign.getDescription())) {
                sign.setDescription("迟到");
            } else {
                sign.setDescription(sign.getDescription() + "、迟到");
            }
        }
        if (sign.getGetOffWork() == null) {
            sign.setStatus(true);
            sign.setDescription("下班签到异常");
        } else {
            //标准下班时间
            LocalDateTime endTime = LocalDateTime.of(sign.getWorkTime().getYear(), sign.getWorkTime().getMonthValue(), sign.getWorkTime().getDayOfMonth(), 18, 0, 0);
            //早退
            if (sign.getGetOffWork().isBefore(endTime)) {
                sign.setStatus(true);
                if (sign.getDescription() == null || "".equals(sign.getDescription())) {
                    sign.setDescription("早退");
                } else {
                    sign.setDescription(sign.getDescription() + "、早退");
                }
            } else {
                //计算加班时长，只要大于下午6点就算加班 但是只按小时来算
                Duration duration = Duration.between(endTime, sign.getGetOffWork());
                int workOvertime = (int) duration.toMinutes() / 60;
                sign.setWorkOvertime((double) workOvertime);
            }
        }
        sign.setUpdateUser(CREATE_USER);
        updateSign(sign);
    }

    /**
     * 修改签到表
     *
     * @param sign
     */
    @Transactional(rollbackFor = Exception.class)
    void updateSign(Sign sign) {
        try {
            Long isSuc = signService.updateById(sign);
            if (isSuc != 1) {
                log.error("修改签到表数据失败sign{}", JSON.toJSONString(sign));
            }
        } catch (Exception e) {
            log.error("晚上整理签到表时出现系统异常 sign{}", JSON.toJSONString(sign), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
