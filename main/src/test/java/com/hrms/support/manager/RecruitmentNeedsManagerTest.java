package com.hrms.support.manager;

import com.hrms.api.domain.condition.RecruitmentNeedsCondition;
import com.hrms.api.domain.entity.RecruitmentNeeds;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/10 17:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class RecruitmentNeedsManagerTest {
    @Resource
    RecruitmentNeedsManager recruitmentNeedsManager;

    public void insert() {
        RecruitmentNeeds recruitmentNeeds = new RecruitmentNeeds();
        recruitmentNeeds.setDepartmentName("11");
        recruitmentNeeds.setJobName("11");
        recruitmentNeeds.setNum(11);
        recruitmentNeeds.setJobRequirements("11");
        recruitmentNeeds.setJobResponsibilities("11");
        recruitmentNeeds.setTypeOfEmployees("11");
        recruitmentNeeds.setCreateUser("11");
        recruitmentNeeds.setUpdateUser("11");
        Long isSuc = recruitmentNeedsManager.insert(recruitmentNeeds);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        insert();
        RecruitmentNeedsCondition recruitmentNeedsCondition = new RecruitmentNeedsCondition();
        List<RecruitmentNeeds> recruitmentNeedsList = recruitmentNeedsManager.list(recruitmentNeedsCondition);
        Assert.assertTrue(recruitmentNeedsList.size() > 0);
    }

    @Test
    @Transactional
    public void getByIdTest() {
        insert();
        RecruitmentNeedsCondition recruitmentNeedsCondition = new RecruitmentNeedsCondition();
        List<RecruitmentNeeds> recruitmentNeedsList = recruitmentNeedsManager.list(recruitmentNeedsCondition);
        RecruitmentNeeds recruitmentNeeds = recruitmentNeedsManager.getById(recruitmentNeedsList.get(0).getId());
        Assert.assertNotNull(recruitmentNeeds);
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        insert();
        RecruitmentNeedsCondition recruitmentNeedsCondition = new RecruitmentNeedsCondition();
        List<RecruitmentNeeds> recruitmentNeedsList = recruitmentNeedsManager.list(recruitmentNeedsCondition);
        Long isSuc = recruitmentNeedsManager.deleteById(recruitmentNeedsList.get(0).getId());
        Assert.assertEquals(1, isSuc.intValue());

        RecruitmentNeeds recruitmentNeeds = recruitmentNeedsManager.getById(recruitmentNeedsList.get(0).getId());
        Assert.assertNull(recruitmentNeeds);
    }

    @Test
    @Transactional
    public void updateByIdTest() {
        insert();
        RecruitmentNeedsCondition recruitmentNeedsCondition = new RecruitmentNeedsCondition();
        List<RecruitmentNeeds> recruitmentNeedsList = recruitmentNeedsManager.list(recruitmentNeedsCondition);
        RecruitmentNeeds recruitmentNeeds = recruitmentNeedsList.get(0);
        recruitmentNeeds.setDepartmentName("22");
        recruitmentNeeds.setJobName("22");
        recruitmentNeeds.setNum(22);
        recruitmentNeeds.setJobRequirements("22");
        recruitmentNeeds.setJobResponsibilities("22");
        recruitmentNeeds.setTypeOfEmployees("22");
        Long isSuc = recruitmentNeedsManager.updateById(recruitmentNeeds);
        Assert.assertEquals(1, isSuc.intValue());
        recruitmentNeeds = recruitmentNeedsManager.getById(recruitmentNeedsList.get(0).getId());

        Assert.assertEquals("22",recruitmentNeeds.getDepartmentName());
        Assert.assertEquals("22",recruitmentNeeds.getJobName());
        Assert.assertEquals(22,recruitmentNeeds.getNum().intValue());
        Assert.assertEquals("22",recruitmentNeeds.getJobRequirements());
        Assert.assertEquals("22",recruitmentNeeds.getJobResponsibilities());
        Assert.assertEquals("22",recruitmentNeeds.getTypeOfEmployees());
        Assert.assertEquals("22",recruitmentNeeds.getDepartmentName());
    }
}
