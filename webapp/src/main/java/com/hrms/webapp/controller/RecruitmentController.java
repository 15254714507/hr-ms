package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hrms.api.domain.condition.RecruitmentNeedsCondition;
import com.hrms.api.domain.dto.DepartmentJob;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.RecruitmentNeeds;
import com.hrms.api.service.JobService;
import com.hrms.api.service.RecruitmentNeedsService;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/10 20:36
 */
@Slf4j
@Controller
@RequestMapping("")
public class RecruitmentController {
    @Reference
    RecruitmentNeedsService recruitmentNeedsService;
    @Reference
    JobService jobService;

    @RequestMapping("/gotoRecruitmentNeedsList.do")
    public String gotoRecruitmentNeedsList() {
        return "recruitment/recruitmentNeedsList";
    }

    @PostMapping("/getRecruitmentNeedsList.do")
    @ResponseBody
    public List<RecruitmentNeeds> getRecruitmentNeedsList() {
        List<RecruitmentNeeds> recruitmentNeedsList = null;
        RecruitmentNeedsCondition recruitmentNeedsCondition = new RecruitmentNeedsCondition();
        try {
            recruitmentNeedsList = recruitmentNeedsService.list(recruitmentNeedsCondition);
        } catch (Exception e) {
            log.error("获得招聘需求的的集合", e);
        }
        return recruitmentNeedsList;
    }

    @RequestMapping("/gotoReleaseRecruitment.do")
    public String gotoReleaseRecruitment(Model model) {
        try {
            List<DepartmentJob> departmentJobList = jobService.listAllDepartment();
            if (departmentJobList.size() < 1) {
                log.error("获得部门岗位全部信息时没有");
                return "error/404";
            }
            model.addAttribute("departmentJobList", departmentJobList);
        } catch (Exception e) {
            log.error("前往添加新的招聘信息时出现系统异常", e);
            return "error/404";
        }
        return "recruitment/releaseRecruitment";
    }

    @PostMapping("/saveRecruitmentNeeds.do")
    @ResponseBody
    public Result saveRecruitmentNeeds(@Valid RecruitmentNeeds recruitmentNeeds, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new Result(0, "信息有误,刷新后重试");
        }
        Result result = null;
        fillRecruitmentNeeds(recruitmentNeeds, session);
        try {
            recruitmentNeedsService.insert(recruitmentNeeds);
            result = new Result(1, "申请成功");
        } catch (Exception e) {
            log.error("添加新的招聘信息时出现系统异常", e);
            result = new Result(-1, "系统异常,请刷新后重试");
        }
        return result;
    }

    /**
     * 填充RecruitmentNeeds
     *
     * @param recruitmentNeeds
     * @param session
     */
    public void fillRecruitmentNeeds(RecruitmentNeeds recruitmentNeeds, HttpSession session) {
        Employees employees = (Employees) session.getAttribute("employees");
        recruitmentNeeds.setCreateUser(employees.getUsername());
        recruitmentNeeds.setUpdateUser(employees.getUsername());
    }

    @PostMapping("/deleteRecruitmentNeeds.do")
    @ResponseBody
    public Result deleteRecruitmentNeeds(Long id) {
        if (id == null || id < 0) {
            return new Result(0, "信息不全,不能删除");
        }
        Result result = null;
        try {
            Long isSuc = recruitmentNeedsService.deleteById(id);
            if (isSuc != 1) {
                result = new Result(0, "此招聘信息不存在");
            } else {
                result = new Result(1, "删除成功");
            }
        } catch (Exception e) {
            log.error("删除招聘信息出现系统异常 id{}", id, e);
            result = new Result(-1, "系统异常，请刷新后重试");
        }
        return result;
    }

    @RequestMapping("/gotoRecruitmentNeedsDetails.do")
    public String gotoRecruitmentNeedsDetails(Long id, Model model) {
        if (id == null || id < 0) {
            return "error/404";
        }
        try {
            RecruitmentNeeds recruitmentNeeds = recruitmentNeedsService.getById(id);
            if (recruitmentNeeds == null) {
                return "error/404";
            }
            model.addAttribute("recruitmentNeeds", recruitmentNeeds);
        } catch (Exception e) {
            log.error("查看招聘信息细则时出现系统异常 id{}", id, e);
            return "error/404";
        }
        return "recruitment/recruitmentNeedsDetails";
    }

}
