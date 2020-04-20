package com.hrms.support.manager;

import com.hrms.api.domain.condition.JobCondition;
import com.hrms.api.domain.entity.Job;
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
 * @date 2020/4/16 17:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class JobManagerTest {
    @Resource
    JobManager jobManager;

    public void insert() {
        Job job = new Job();
        job.setDepartmentId(10000L);
        job.setJobName("111");
        job.setLead(true);
        job.setCreateUser("kc");
        job.setUpdateUser("kc");
        Long isSuc = jobManager.insert(job);
        Assert.assertEquals(1L, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        insert();
        JobCondition jobCondition = new JobCondition();
        jobCondition.setJobName("111");
        jobCondition.setDepartmentId(10000L);
        List<Job> jobList = jobManager.list(jobCondition);
        Assert.assertEquals(1, jobList.size());
        Assert.assertEquals("111", jobList.get(0).getJobName());
        Assert.assertEquals(10000, jobList.get(0).getDepartmentId().intValue());
        Assert.assertTrue(jobList.get(0).getLead());
    }

    @Test
    @Transactional
    public void updateByIdTest() {
        insert();
        JobCondition jobCondition = new JobCondition();
        jobCondition.setJobName("111");
        jobCondition.setDepartmentId(10000L);
        List<Job> jobList = jobManager.list(jobCondition);

        Job job = new Job();
        job.setId(jobList.get(0).getId());
        job.setDepartmentId(220000L);
        job.setJobName("222");
        Long isSuc = jobManager.updateById(job);
        Assert.assertEquals(1, isSuc.intValue());

        job = new Job();
        job.setId(0L);
        Long isError = jobManager.updateById(job);
        Assert.assertEquals(0, isError.intValue());
    }

    @Test
    @Transactional
    public void insertTest() {
        insert();
        Job job = new Job();
        job.setDepartmentId(10000L);
        job.setJobName("111");
        Long isError = jobManager.insert(job);
        Assert.assertEquals(0, isError.intValue());
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        insert();
        JobCondition jobCondition = new JobCondition();
        jobCondition.setJobName("111");
        jobCondition.setDepartmentId(10000L);
        List<Job> jobList = jobManager.list(jobCondition);
        Assert.assertEquals(1, jobList.size());
        Long id = jobList.get(0).getId();

        Long isSuc = jobManager.deleteById(id);
        Assert.assertEquals(1, isSuc.intValue());

        Job job = jobManager.getById(id);
        Assert.assertNull(job);

    }

}
