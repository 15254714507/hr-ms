package com.hrms.support.manager;

import com.hrms.api.domain.condition.SignCondition;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.until.LocalDateTimeFactory;
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
 * @date 2020/5/2 23:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class SignManagerTest {
    @Resource
    SignManager signManager;


    public void insert() {
        Sign sign = new Sign();
        sign.setUsername("222");
        sign.setWorkTime(LocalDateTimeFactory.getLocalDateTime());
        sign.setCreateUser("System");
        sign.setUpdateUser("System");
        Long isSuc = signManager.insert(sign);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        insert();
        SignCondition signCondition = new SignCondition();
        signCondition.setUsername("222");
        List<Sign> signList = signManager.list(signCondition);
        Assert.assertTrue(signList.size() > 0);
    }

    @Test
    @Transactional
    public void getByIdTest() {
        insert();
        SignCondition signCondition = new SignCondition();
        signCondition.setUsername("222");
        List<Sign> signList = signManager.list(signCondition);
        Assert.assertTrue(signList.size() > 0);
        Sign sign = signManager.getById(signList.get(0).getId());
        Assert.assertNotNull(sign);
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        insert();
        SignCondition signCondition = new SignCondition();
        signCondition.setUsername("222");
        List<Sign> signList = signManager.list(signCondition);

        Long isSuc = signManager.deleteById(signList.get(0).getId());
        Assert.assertEquals(1, isSuc.intValue());

        Sign sign = signManager.getById(signList.get(0).getId());
        Assert.assertNull(sign);

    }

    @Test
    @Transactional
    public void updateByIdTest() {
        insert();
        SignCondition signCondition = new SignCondition();
        signCondition.setUsername("222");
        List<Sign> signList = signManager.list(signCondition);
        Sign sign = signList.get(0);

        sign.setGetOffWork(LocalDateTimeFactory.getLocalDateTime());
        sign.setWorkOvertime(5.5);
        sign.setStatus(true);
        sign.setDescription("111");
        Long isSuc = signManager.updateById(sign);
        Assert.assertEquals(1, isSuc.intValue());

        sign = signManager.getById(signList.get(0).getId());
        Assert.assertNotNull(sign.getGetOffWork());
        Assert.assertEquals(5.5, sign.getWorkOvertime(), 1);
    }

    @Test
    @Transactional
    public void listByYearMonthDayTest() {
        insert();
        SignCondition signCondition = new SignCondition();
        signCondition.setUsername("222");
        signCondition.setYear(LocalDateTimeFactory.getLocalDate().getYear());
        signCondition.setMonth(LocalDateTimeFactory.getLocalDate().getMonthValue());
        signCondition.setDay(LocalDateTimeFactory.getLocalDate().getDayOfMonth());
        List<Sign> signList = signManager.list(signCondition);
        Assert.assertTrue(signList.size()>0);
    }
}
