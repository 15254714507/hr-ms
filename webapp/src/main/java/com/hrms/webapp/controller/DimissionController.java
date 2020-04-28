package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.DimissionUserCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.DimissionUser;
import com.hrms.api.service.DimissionUserService;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.api.until.Result;
import com.hrms.webapp.until.WordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 前往离职审核页面
     *
     * @return
     */
    @RequestMapping("/gotoAuditDimission.do")
    public String gotoAuditDimission() {
        return "dimission/auditDimission";
    }

    @PostMapping("/getAuditDimissionUserList.do")
    @ResponseBody
    public List<DimissionUser> getAuditDimissionUserList(Model model) {
        List<DimissionUser> dimissionUserList = null;
        DimissionUserCondition dimissionUserCondition = new DimissionUserCondition();
        dimissionUserCondition.setSteps(1);
        try {
            dimissionUserList = dimissionUserService.list(dimissionUserCondition);
        } catch (Exception e) {
            log.error("获取需要审核的离职申请出现系统异常", e);
        }
        return dimissionUserList;
    }

    @PostMapping("/passDimissionUser.do")
    @ResponseBody
    public Result passDimissionUser(Long id, HttpSession session) {
        Result result = null;
        Employees employees = (Employees) session.getAttribute("employees");
        DimissionUser dimissionUser = fillPassDimissionUser(id, employees.getUsername());
        try {
            Long isSuc = dimissionUserService.updateById(dimissionUser);
            if (isSuc != 1) {
                result = new Result(0, "要通过离职申请不存在");
            } else {
                result = new Result(1, "审核通过");
            }
        } catch (Exception e) {
            log.error("审核通过离职申请时发生系统异常 dimissionUser {}", JSON.toJSONString(dimissionUser), e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    private DimissionUser fillPassDimissionUser(Long id, String updateUser) {
        DimissionUser dimissionUser = new DimissionUser();
        dimissionUser.setId(id);
        dimissionUser.setSteps(2);
        dimissionUser.setUpdateUser(updateUser);
        dimissionUser.setApprovalUser(updateUser);
        //默认意见
        dimissionUser.setApprovalComments("同意");
        return dimissionUser;
    }

    @PostMapping("/deleteDimissionUser.do")
    @ResponseBody
    public Result deleteDimissionUser(Long id) {
        Result result = null;
        try {
            result = dimissionUserService.deleteById(id);
        } catch (Exception e) {
            log.error("删除离职申请时发生系统异常，id{}", id, e);
            result = new Result(-1, "发生系统异常，请刷新后重试");
        }
        return result;
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/downDimission.do")
    @ResponseBody
    public ResponseEntity<byte[]> downDimission(Long id) {
        byte[] fileByte = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            Map<String, Object> dataMap = getDataMap(id);
            fileByte = WordUtil.createWordByte(dataMap, "proofOfSeparation.ftl", "离职证明.doc");
            headers.setContentDispositionFormData("attachment", URLEncoder.encode("离职证明.doc", "utf-8"));
            updateDimissionUser(id);
        } catch (Exception e) {
            log.error("下载离职证明失败 id:{}", id, e);
        }
        return new ResponseEntity<byte[]>(fileByte, headers, HttpStatus.CREATED);
    }

    private Map<String, Object> getDataMap(Long id) {
        Map<String, Object> dataMap = new HashMap<String, Object>(2);
        DimissionUser dimissionUser = dimissionUserService.getById(id);
        if (dimissionUser == null || dimissionUser.getSteps() != 2) {
            return null;
        }
        dataMap.put("dimissionUser", dimissionUser);
        if ("实习".equals(dimissionUser.getTypesOfEmployees())) {
            dataMap.put("type", "实习");
        } else {
            dataMap.put("type", "离职");
        }
        return dataMap;
    }

    /**
     * 打印完离职证明，流程步骤就结束了，就要变成4
     */
    private void updateDimissionUser(Long id) {
        DimissionUser dimissionUser = new DimissionUser();
        dimissionUser.setId(id);
        dimissionUser.setSteps(3);
        Long isSuc = dimissionUserService.updateById(dimissionUser);
        if (isSuc != 1) {
            log.error("离职证明打印完后，流程变为完成时出现异常");
        }
    }


}
