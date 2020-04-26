package com.hrms.support.manager;

import com.hrms.api.domain.condition.DimissionUserCondition;
import com.hrms.api.domain.entity.DimissionUser;
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
 * @date 2020/4/26 22:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class DimissionUserManagerTest {
    @Resource
    DimissionUserManager dimissionUserManager;


    public void inset() {
        DimissionUser dimissionUser = new DimissionUser();
        dimissionUser.setUsername("22222");
        dimissionUser.setName("2222");
        dimissionUser.setJobName("2222");
        dimissionUser.setApprovalComments("2222");
        dimissionUser.setApprovalUser("2222");
        dimissionUser.setDateOfEntry(LocalDateTimeFactory.getLocalDate());
        dimissionUser.setDateOfSeparation(LocalDateTimeFactory.getLocalDate());
        dimissionUser.setDepartmentName("222");
        dimissionUser.setReasonsForSeparation("222");
        dimissionUser.setSteps(1);
        dimissionUser.setTypesOfEmployees("22");
        dimissionUser.setUpdateUser("22");
        dimissionUser.setCreateUser("22");
        Long isSuc = dimissionUserManager.insert(dimissionUser);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        inset();
        DimissionUserCondition dimissionUserCondition = new DimissionUserCondition();
        dimissionUserCondition.setUsername("22222");
        List<DimissionUser> dimissionUsers = dimissionUserManager.list(dimissionUserCondition);
        Assert.assertEquals(1,dimissionUsers.size());
    }
    @Test
    @Transactional
    public void getByIdTest(){
        inset();
        DimissionUserCondition dimissionUserCondition = new DimissionUserCondition();
        dimissionUserCondition.setUsername("22222");
        List<DimissionUser> dimissionUsers = dimissionUserManager.list(dimissionUserCondition);
        DimissionUser dimissionUser = dimissionUserManager.getById(dimissionUsers.get(0).getId());
        Assert.assertNotNull(dimissionUser);
    }
    @Test
    @Transactional
    public void deleteById(){
        inset();
        DimissionUserCondition dimissionUserCondition = new DimissionUserCondition();
        dimissionUserCondition.setUsername("22222");
        List<DimissionUser> dimissionUsers = dimissionUserManager.list(dimissionUserCondition);
        Long isSuc = dimissionUserManager.deleteById(dimissionUsers.get(0).getId());
        Assert.assertEquals(1,isSuc.intValue());
        DimissionUser dimissionUser = dimissionUserManager.getById(dimissionUsers.get(0).getId());
        Assert.assertNull(dimissionUser);
    }
    @Test
    @Transactional
    public void updateByIdTest(){
        inset();
        DimissionUserCondition dimissionUserCondition = new DimissionUserCondition();
        dimissionUserCondition.setUsername("22222");
        List<DimissionUser> dimissionUsers = dimissionUserManager.list(dimissionUserCondition);
        DimissionUser dimissionUser = dimissionUsers.get(0);
        dimissionUser.setApprovalUser("111");
        dimissionUser.setApprovalComments("111");
        dimissionUser.setSteps(55);
        Long isSuc = dimissionUserManager.updateById(dimissionUser);
        Assert.assertEquals(1,isSuc.intValue());

        dimissionUser = dimissionUserManager.getById(dimissionUser.getId());
        Assert.assertEquals("111",dimissionUser.getApprovalComments());
        Assert.assertEquals("111",dimissionUser.getApprovalUser());
        Assert.assertEquals(55,dimissionUser.getSteps().intValue());
    }
}
