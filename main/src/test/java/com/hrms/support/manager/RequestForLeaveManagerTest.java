package com.hrms.support.manager;

import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.entity.RequestForLeave;
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
 * @date 2020/5/3 23:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class RequestForLeaveManagerTest {
    @Resource
    RequestForLeaveManager requestForLeaveManager;


    public void insert() {
        RequestForLeave requestForLeave = new RequestForLeave();
        requestForLeave.setUsername("2222");
        requestForLeave.setAuditUser("kc");
        requestForLeave.setStartDate(LocalDateTimeFactory.getLocalDate());
        requestForLeave.setEndDate(LocalDateTimeFactory.getLocalDate());
        requestForLeave.setCreateUser("kc");
        requestForLeave.setUpdateUser("kc");
        requestForLeave.setDaysOfAbsenteeism(1.2);
        requestForLeave.setDaysOfLeave(1.2);
        requestForLeave.setDaysOfRecess(1.2);
        requestForLeave.setDaysOfSickLeave(1.2);
        requestForLeave.setDescription("222");
        Long isSuc = requestForLeaveManager.insert(requestForLeave);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        insert();
        RequestForLeaveCondition requestForLeaveCondition = new RequestForLeaveCondition();
        requestForLeaveCondition.setUsername("2222");
        requestForLeaveCondition.setStartDate(LocalDateTimeFactory.getLocalDate());
        requestForLeaveCondition.setEndDate(LocalDateTimeFactory.getLocalDate());
        requestForLeaveCondition.setStatus(false);
        List<RequestForLeave> requestForLeaveList = requestForLeaveManager.list(requestForLeaveCondition);
        Assert.assertEquals(1, requestForLeaveList.size());
    }

    @Test
    @Transactional
    public void getByIdTest() {
        insert();
        RequestForLeaveCondition requestForLeaveCondition = new RequestForLeaveCondition();
        requestForLeaveCondition.setUsername("2222");
        List<RequestForLeave> requestForLeaveList = requestForLeaveManager.list(requestForLeaveCondition);
        RequestForLeave requestForLeave = requestForLeaveManager.getById(requestForLeaveList.get(0).getId());
        Assert.assertNotNull(requestForLeave);

    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        insert();
        RequestForLeaveCondition requestForLeaveCondition = new RequestForLeaveCondition();
        requestForLeaveCondition.setUsername("2222");
        List<RequestForLeave> requestForLeaveList = requestForLeaveManager.list(requestForLeaveCondition);
        Long isSuc = requestForLeaveManager.deleteById(requestForLeaveList.get(0).getId());
        Assert.assertEquals(1, isSuc.intValue());
        RequestForLeave requestForLeave = requestForLeaveManager.getById(requestForLeaveList.get(0).getId());
        Assert.assertNull(requestForLeave);
    }

    @Test
    @Transactional
    public void updateByIdTest() {
        insert();
        RequestForLeaveCondition requestForLeaveCondition = new RequestForLeaveCondition();
        requestForLeaveCondition.setUsername("2222");
        List<RequestForLeave> requestForLeaveList = requestForLeaveManager.list(requestForLeaveCondition);
        RequestForLeave requestForLeave = requestForLeaveList.get(0);
        requestForLeave.setStatus(true);
        requestForLeave.setDescription("3333");
        requestForLeave.setDaysOfSickLeave(1.3);
        requestForLeave.setDaysOfRecess(1.3);
        requestForLeave.setDaysOfLeave(1.3);
        requestForLeave.setDaysOfAbsenteeism(1.3);
        Long isSuc = requestForLeaveManager.updateById(requestForLeave);
        Assert.assertEquals(1, isSuc.intValue());

        requestForLeave = requestForLeaveManager.getById(requestForLeaveList.get(0).getId());
        Assert.assertEquals(1.3, requestForLeave.getDaysOfAbsenteeism(), 1);
        Assert.assertEquals(1.3, requestForLeave.getDaysOfLeave(), 1);
        Assert.assertEquals(1.3, requestForLeave.getDaysOfRecess(), 1);
        Assert.assertEquals(1.3, requestForLeave.getDaysOfSickLeave(), 1);
        Assert.assertTrue(requestForLeave.getStatus());

    }
}
