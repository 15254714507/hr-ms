package com.hrms.support.manager;

import com.hrms.api.domain.condition.RecruitersCondition;
import com.hrms.api.domain.entity.Recruiters;
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
 * @date 2020/5/12 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class RecruitersManagerTest {

    @Resource
    RecruitersManager recruitersManager;


    public void inset() {
        Recruiters recruiters = new Recruiters();
        recruiters.setName("11");
        recruiters.setPositions("11");
        recruiters.setType("11");
        recruiters.setResumeId("11");
        recruiters.setCreateUser("11");
        recruiters.setUpdateUser("11");
        Long isSuc = recruitersManager.insert(recruiters);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        inset();
        RecruitersCondition recruitersCondition = new RecruitersCondition();
        List<Recruiters> recruitersList = recruitersManager.list(recruitersCondition);
        Assert.assertTrue(recruitersList.size() > 0);

    }

    @Test
    @Transactional
    public void getByIdTest() {
        inset();
        RecruitersCondition recruitersCondition = new RecruitersCondition();
        List<Recruiters> recruitersList = recruitersManager.list(recruitersCondition);
        Recruiters recruiters = recruitersManager.getById(recruitersList.get(0).getId());
        Assert.assertNotNull(recruiters);
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        inset();
        RecruitersCondition recruitersCondition = new RecruitersCondition();
        List<Recruiters> recruitersList = recruitersManager.list(recruitersCondition);

        Long isSuc = recruitersManager.deleteById(recruitersList.get(0).getId());
        Assert.assertEquals(1, isSuc.intValue());

        Recruiters recruiters = recruitersManager.getById(recruitersList.get(0).getId());
        Assert.assertNull(recruiters);
    }

    @Test
    @Transactional
    public void updateByIdTest() {
        inset();
        RecruitersCondition recruitersCondition = new RecruitersCondition();
        List<Recruiters> recruitersList = recruitersManager.list(recruitersCondition);
        Recruiters recruiters = recruitersList.get(0);
        recruiters.setName("22");
        recruiters.setPositions("22");
        recruiters.setType("22");
        recruiters.setResumeId("22");
        Long isSuc = recruitersManager.updateById(recruiters);
        Assert.assertEquals(1, isSuc.intValue());

        recruiters = recruitersManager.getById(recruitersList.get(0).getId());
        Assert.assertEquals("22", recruiters.getName());
        Assert.assertEquals("22", recruiters.getPositions());
        Assert.assertEquals("22", recruiters.getType());
        Assert.assertEquals("22", recruiters.getResumeId());
    }
}
