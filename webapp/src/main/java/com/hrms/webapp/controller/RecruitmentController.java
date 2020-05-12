package com.hrms.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hrms.api.domain.condition.RecruitersCondition;
import com.hrms.api.domain.condition.RecruitmentNeedsCondition;
import com.hrms.api.domain.dto.DepartmentJob;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.dto.FileDocument;
import com.hrms.api.domain.entity.Recruiters;
import com.hrms.api.domain.entity.RecruitmentNeeds;
import com.hrms.api.service.JobService;
import com.hrms.api.service.RecruitersService;
import com.hrms.api.service.RecruitmentNeedsService;
import com.hrms.api.until.Result;
import com.hrms.webapp.mongoDBService.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    @Reference
    RecruitersService recruitersService;

    @Resource
    FileService fileService;


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

    @RequestMapping("/gotoRecruitersList.do")
    public String gotoRecruitersList() {
        return "recruitment/recruitersList";
    }

    @PostMapping("/getRecruitersList.do")
    @ResponseBody
    public List<Recruiters> getRecruitersList() {
        List<Recruiters> recruitersList = null;
        RecruitersCondition recruitersCondition = new RecruitersCondition();
        try {
            recruitersList = recruitersService.list(recruitersCondition);
        } catch (Exception e) {
            log.error("获得应聘者的集合出现系统异常", e);
        }
        return recruitersList;
    }

    @PostMapping("/deleteRecruiters.do")
    @ResponseBody
    public Result deleteRecruiters(Long id) {
        if (id == null || id < 1) {
            return new Result(0, "不符合要求");
        }
        Result result = null;
        try {
            Long isSuc = recruitersService.deleteById(id);
            if (isSuc != 1) {
                result = new Result(0, "没有此应聘者");
            } else {
                result = new Result(1, "删除成功");
            }
        } catch (Exception e) {
            log.error("删除应聘者时发生系统异常");
            result = new Result(-1, "系统异常，刷新后重试");
        }
        return result;
    }

    @RequestMapping("/gotoAddRecruiters.do")
    public String gotoAddRecruiters() {
        return "recruitment/addRecruiters";
    }

    @PostMapping("/saveRecruiters.do")
    @ResponseBody
    public Result saveRecruiters(@RequestParam("file") MultipartFile file, @Valid Recruiters recruiters, BindingResult bindingResult, HttpSession session) {
        if (file == null || file.isEmpty()) {
            return new Result(0, "请上传简历");
        }
        String resumeId = uploadFile(file);
        if (resumeId == null) {
            return new Result(0, "简历上传失败,请重新上传");
        }
        recruiters.setResumeId(resumeId);
        Employees employees = (Employees) session.getAttribute("employees");
        recruiters.setCreateUser(employees.getUsername());
        recruiters.setUpdateUser(employees.getUsername());
        Result result = null;
        try {
            Long isSuc = recruitersService.insert(recruiters);
            if (isSuc != 1) {
                result = new Result(0, "添加失败，请重新添加");
            } else {
                result = new Result(1, "添加成功");
            }
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * 上传简历文件
     *
     * @param file
     * @return 返回的是文件的Id，如果没有就是空
     */
    private String uploadFile(MultipartFile file) {
        try {
            String fileMd5 = getMD5(file.getInputStream());
            FileDocument fileDocument = fileService.saveFile(fileMd5, file);
            return fileDocument.getId();
        } catch (IOException e) {
            log.error("IOE异常", e);
        } catch (Exception e) {
            log.error("上传文件系统异常，", e);
        }
        return null;
    }

    @RequestMapping("/gotoTest.do")
    public String gotoTest(Model model) {
        List<FileDocument> fileDocumentList = fileService.listFilesByPage(1, 10);
        model.addAttribute("fileDocumentList", fileDocumentList);
        return "recruitment/text";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "recruitment/upload";
    }

    /**
     * 流转换成MD5字符串
     *
     * @param inputStream
     * @return
     */
    private String getMD5(InputStream inputStream) {
        BigInteger md5 = null;
        try {
            byte[] buffer = inputStreamToByte(inputStream);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer, 0, buffer.length);
            byte[] b = md.digest();
            md5 = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        assert md5 != null;
        return md5.toString(16);
    }

    /**
     * 流转换成byte数组
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    private byte[] inputStreamToByte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        //buff用于存放循环读取的临时数据
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }
}
