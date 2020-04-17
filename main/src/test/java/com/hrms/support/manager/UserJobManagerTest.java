package com.hrms.support.manager;

import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.entity.UserJob;
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
 * @date 2020/4/17 17:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class UserJobManagerTest {
    @Resource
    UserJobManager userJobManager;


    public void insert() {
        UserJob userJob = new UserJob();
        userJob.setJobId(10000L);
        userJob.setUserId(10000L);
        userJob.setTypesOfEmployees("实习");
        userJob.setBaseSalary(2000);
        userJob.setPerformanceSalary(1000);
        userJob.setCreateUser("kc");
        userJob.setUpdateUser("kc");
        Long isSuc = userJobManager.insert(userJob);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        insert();
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setJobId(10000L);
        userJobCondition.setUserId(10000L);
        userJobCondition.setTypesOfEmployees("实习");
        userJobCondition.setBaseSalary(2000);
        userJobCondition.setPerformanceSalary(1000);
        List<UserJob> userJobList = userJobManager.list(userJobCondition);
        Assert.assertEquals(1, userJobList.size());

    }

    @Test
    @Transactional
    public void getByIdTest() {
        insert();
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setJobId(10000L);
        userJobCondition.setUserId(10000L);
        userJobCondition.setTypesOfEmployees("实习");
        userJobCondition.setBaseSalary(2000);
        userJobCondition.setPerformanceSalary(1000);
        List<UserJob> userJobList = userJobManager.list(userJobCondition);

        UserJob userJob = userJobManager.getById(userJobList.get(0).getId());
        Assert.assertNotNull(userJob);
    }

    @Test
    @Transactional
    public void updateByIdTest() {
        insert();
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setJobId(10000L);
        userJobCondition.setUserId(10000L);
        userJobCondition.setTypesOfEmployees("实习");
        userJobCondition.setBaseSalary(2000);
        userJobCondition.setPerformanceSalary(1000);
        List<UserJob> userJobList = userJobManager.list(userJobCondition);
        UserJob userJob = userJobList.get(0);
        userJob.setJobId(20000L);
        userJob.setUserId(20000L);
        userJobCondition.setTypesOfEmployees("全职");
        userJobCondition.setBaseSalary(3000);
        userJobCondition.setPerformanceSalary(2000);
        Long isSuc = userJobManager.updateById(userJob);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        insert();
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setJobId(10000L);
        userJobCondition.setUserId(10000L);
        userJobCondition.setTypesOfEmployees("实习");
        userJobCondition.setBaseSalary(2000);
        userJobCondition.setPerformanceSalary(1000);
        List<UserJob> userJobList = userJobManager.list(userJobCondition);

        Long isSuc = userJobManager.deleteById(userJobList.get(0).getId());
        Assert.assertEquals(1, isSuc.intValue());

        UserJob userJob = userJobManager.getById(userJobList.get(0).getId());
        Assert.assertNull(userJob);
    }

}
