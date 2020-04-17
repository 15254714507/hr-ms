package com.hrms.support.manager;

import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.entity.User;
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
 * @date 2020/4/15 0:47
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class UserManagerTest {
    @Resource
    UserManager userManager;


    public String insert() {
        User user = new User();
        user.setUsername("kc");
        user.setAddress("111");
        user.setCensusRegister("11111");
        user.setDateOfBirth(LocalDateTimeFactory.getLocalDate());
        user.setDegree("1111");
        user.setEmail("11111");
        user.setEmploymentDate(LocalDateTimeFactory.getLocalDate());
        user.setFirstWorkDate(LocalDateTimeFactory.getLocalDate());
        user.setGender(0);
        user.setGraduationDate(LocalDateTimeFactory.getLocalDate());
        user.setHeadShot("1111");
        user.setIdentityCard("1111");
        user.setInternshipDate(LocalDateTimeFactory.getLocalDate());
        user.setName("111");
        user.setNational("111");
        user.setNationality("1111");
        user.setPassword("1111");
        user.setPhone("111");
        user.setNativePlace("111");
        user.setProfessional("111");
        user.setResume("1111");
        user.setUniversity("1111");
        user.setWorkYears(1.2);
        user.setCreateUser("1111");
        user.setUpdateUser("1111");
        Long isSuc = userManager.insert(user);
        Assert.assertEquals(1, isSuc.intValue());
        return user.getUsername();
    }

    @Test
    @Transactional
    public void getByUsernameTest() {
        String username = insert();
        User user = userManager.getByUsername(username);
        Assert.assertNotNull(user);
    }

    @Test
    @Transactional
    public void getByIdTest() {
        String username = insert();
        User user = userManager.getByUsername(username);
        Assert.assertNotNull(user);
        user = userManager.getById(user.getId());
        Assert.assertNotNull(user);
    }

    @Test
    @Transactional
    public void updateByIdTest() {
        String username = insert();
        User user = userManager.getByUsername(username);
        Assert.assertNotNull(user);
        user = userManager.getById(user.getId());
        Assert.assertNotNull(user);

        user.setAddress("111");
        user.setCensusRegister("11111");
        user.setDateOfBirth(LocalDateTimeFactory.getLocalDate());
        user.setDegree("1111");
        user.setEmail("11111");
        user.setEmploymentDate(LocalDateTimeFactory.getLocalDate());
        user.setFirstWorkDate(LocalDateTimeFactory.getLocalDate());
        user.setGender(0);
        user.setGraduationDate(LocalDateTimeFactory.getLocalDate());
        user.setHeadShot("1111");
        user.setIdentityCard("1111");
        user.setInternshipDate(LocalDateTimeFactory.getLocalDate());
        user.setName("111");
        user.setNational("111");
        user.setNationality("1111");
        user.setPassword("1111");
        user.setPhone("111");
        user.setNativePlace("111");
        user.setProfessional("111");
        user.setResume("1111");
        user.setUniversity("1111");
        user.setWorkYears(1.2);
        user.setCreateUser("1111");
        user.setUpdateUser("1111");

        Long isSuc = userManager.updateById(user);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        String username = insert();
        User user = userManager.getByUsername(username);
        Assert.assertNotNull(user);

        Long isSuc = userManager.deleteById(user.getId());
        Assert.assertEquals(1, isSuc.intValue());

        user = userManager.getById(user.getId());
        Assert.assertNull(user);

    }

    @Test
    @Transactional
    public void listTest() {
        insert();
        UserCondition userCondition = new UserCondition();
        List<User> userList = userManager.list(userCondition);
        Assert.assertTrue(userList.size() > 0);
    }

}
