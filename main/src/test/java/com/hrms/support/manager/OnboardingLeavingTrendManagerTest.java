package com.hrms.support.manager;

import com.hrms.api.domain.condition.OnboardingLeavingTrendCondition;
import com.hrms.api.domain.entity.OnboardingLeavingTrend;
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
 * @date 2020/6/1 23:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class OnboardingLeavingTrendManagerTest {
    @Resource
    OnboardingLeavingTrendManager onboardingLeavingTrendManager;


    public void insertTest() {
        OnboardingLeavingTrend onboardingLeavingTrend = new OnboardingLeavingTrend();
        onboardingLeavingTrend.setNumberOfEmployees(2);
        onboardingLeavingTrend.setNumberOfSeparations(2);
        onboardingLeavingTrend.setMonth(2);
        onboardingLeavingTrend.setCreateUser("kc");
        onboardingLeavingTrend.setUpdateUser("kc");
        Long isSuc = onboardingLeavingTrendManager.insert(onboardingLeavingTrend);
        Assert.assertEquals(1, isSuc.intValue());
    }
    @Test
    @Transactional
    public void listTest(){
       insertTest();
        OnboardingLeavingTrendCondition onboardingLeavingTrendCondition = new OnboardingLeavingTrendCondition();
        onboardingLeavingTrendCondition.setRows(1);
        List<OnboardingLeavingTrend> onboardingLeavingTrendList = onboardingLeavingTrendManager.list(onboardingLeavingTrendCondition);
        Assert.assertEquals(1,onboardingLeavingTrendList.size());
    }
    @Test
    @Transactional
    public void getByIdTest(){
        insertTest();
        OnboardingLeavingTrendCondition onboardingLeavingTrendCondition = new OnboardingLeavingTrendCondition();
        onboardingLeavingTrendCondition.setRows(1);
        List<OnboardingLeavingTrend> onboardingLeavingTrendList = onboardingLeavingTrendManager.list(onboardingLeavingTrendCondition);
        OnboardingLeavingTrend onboardingLeavingTrend = onboardingLeavingTrendManager.getById(onboardingLeavingTrendList.get(0).getId());
        Assert.assertNotNull(onboardingLeavingTrend);
    }
    @Test
    @Transactional
    public void deleteByIdTest(){
        insertTest();
        OnboardingLeavingTrendCondition onboardingLeavingTrendCondition = new OnboardingLeavingTrendCondition();
        onboardingLeavingTrendCondition.setRows(1);
        List<OnboardingLeavingTrend> onboardingLeavingTrendList = onboardingLeavingTrendManager.list(onboardingLeavingTrendCondition);
        Long isSuc = onboardingLeavingTrendManager.deleteById(onboardingLeavingTrendList.get(0).getId());
        Assert.assertEquals(1,isSuc.intValue());
        OnboardingLeavingTrend onboardingLeavingTrend = onboardingLeavingTrendManager.getById(onboardingLeavingTrendList.get(0).getId());
        Assert.assertNull(onboardingLeavingTrend);
    }
    @Test
    @Transactional
    public void updateByIdTest(){
        insertTest();
        OnboardingLeavingTrendCondition onboardingLeavingTrendCondition = new OnboardingLeavingTrendCondition();
        onboardingLeavingTrendCondition.setRows(1);
        List<OnboardingLeavingTrend> onboardingLeavingTrendList = onboardingLeavingTrendManager.list(onboardingLeavingTrendCondition);
        OnboardingLeavingTrend onboardingLeavingTrend = onboardingLeavingTrendList.get(0);
        onboardingLeavingTrend.setNumberOfSeparations(3);
        onboardingLeavingTrend.setNumberOfEmployees(3);
        onboardingLeavingTrend.setMonth(3);
        Long isSuc = onboardingLeavingTrendManager.updateById(onboardingLeavingTrend);
        Assert.assertEquals(1,isSuc.intValue());
        OnboardingLeavingTrend onboardingLeavingTrend1 = onboardingLeavingTrendManager.getById(onboardingLeavingTrendList.get(0).getId());
        Assert.assertEquals(3,onboardingLeavingTrend1.getNumberOfEmployees().intValue());
        Assert.assertEquals(3,onboardingLeavingTrend1.getNumberOfSeparations().intValue());
        Assert.assertEquals(3,onboardingLeavingTrend1.getMonth().intValue());



    }


}
