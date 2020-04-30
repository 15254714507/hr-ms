package com.hrms.support.manager;

import com.hrms.api.domain.condition.PerformanceCondition;
import com.hrms.api.domain.entity.Performance;
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
 * @date 2020/4/30 2:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class PerformanceManagerTest {
    @Resource
    PerformanceManager performanceManager;

    public void insert() {
        Performance performance = new Performance();
        performance.setUserId(11111L);
        performance.setCreateUser("kc");
        performance.setUpdateUser("kc");
        Long isSuc = performanceManager.insert(performance);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        insert();
        PerformanceCondition performanceCondition = new PerformanceCondition();
        performanceCondition.setUserId(11111L);
        List<Performance> performanceList = performanceManager.list(performanceCondition);
        Assert.assertEquals(1, performanceList.size());
    }

    @Test
    @Transactional
    public void getByIdTest() {
        insert();
        PerformanceCondition performanceCondition = new PerformanceCondition();
        performanceCondition.setUserId(11111L);
        List<Performance> performanceList = performanceManager.list(performanceCondition);
        Performance performance = performanceManager.getById(performanceList.get(0).getId());
        Assert.assertNotNull(performance);
    }

    @Test
    @Transactional
    public void updateByIdTest() {
        insert();
        PerformanceCondition performanceCondition = new PerformanceCondition();
        performanceCondition.setUserId(11111L);
        List<Performance> performanceList = performanceManager.list(performanceCondition);
        Performance performance = performanceManager.getById(performanceList.get(0).getId());

        performance.setAuditUser("kc");
        performance.setGoal("111");
        performance.setStatus(true);
        Long isSuc = performanceManager.updateById(performance);
        Assert.assertEquals(1, isSuc.intValue());


    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        insert();
        PerformanceCondition performanceCondition = new PerformanceCondition();
        performanceCondition.setUserId(11111L);
        List<Performance> performanceList = performanceManager.list(performanceCondition);
        Long isSuc = performanceManager.deleteById(performanceList.get(0).getId());
        Assert.assertEquals(1, isSuc.intValue());
        Performance performance = performanceManager.getById(performanceList.get(0).getId());
        Assert.assertNull(performance);
    }

    @Test
    @Transactional
    public void listByYearMonthTest() {
        insert();
        PerformanceCondition performanceCondition = new PerformanceCondition();
        performanceCondition.setUserId(11111L);
        performanceCondition.setYear(2020);
        performanceCondition.setMonth(4);
        List<Performance> performanceList = performanceManager.list(performanceCondition);
        Assert.assertTrue(performanceList.size() > 0);
    }
}
