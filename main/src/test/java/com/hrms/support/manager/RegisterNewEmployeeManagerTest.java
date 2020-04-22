package com.hrms.support.manager;

import com.hrms.api.domain.condition.RegisterNewEmployeeCondition;
import com.hrms.api.domain.entity.RegisterNewEmployee;
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
 * @date 2020/4/20 19:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class RegisterNewEmployeeManagerTest {
    @Resource
    RegisterNewEmployeeManager registerNewEmployeeManager;

    @Test
    public void insert() {
        RegisterNewEmployee registerNewEmployee = new RegisterNewEmployee();
        registerNewEmployee.setUsername("kc");
        registerNewEmployee.setAddress("111");
        registerNewEmployee.setCensusRegister("11111");
        registerNewEmployee.setDateOfBirth(LocalDateTimeFactory.getLocalDate());
        registerNewEmployee.setDegree("1111");
        registerNewEmployee.setEmail("11111");
        registerNewEmployee.setEmploymentDate(LocalDateTimeFactory.getLocalDate());
        registerNewEmployee.setFirstWorkDate(LocalDateTimeFactory.getLocalDate());
        registerNewEmployee.setGender(0);
        registerNewEmployee.setGraduationDate(LocalDateTimeFactory.getLocalDate());
        registerNewEmployee.setIdentityCard("1111");
        registerNewEmployee.setInternshipDate(LocalDateTimeFactory.getLocalDate());
        registerNewEmployee.setName("111");
        registerNewEmployee.setNational("111");
        registerNewEmployee.setNationality("1111");
        registerNewEmployee.setPhone("111");
        registerNewEmployee.setNativePlace("111");
        registerNewEmployee.setProfessional("111");
        registerNewEmployee.setUniversity("1111");
        registerNewEmployee.setWorkYears(1.2);
        registerNewEmployee.setCreateUser("1111");
        registerNewEmployee.setUpdateUser("1111");
        registerNewEmployee.setDepartmentId(11L);
        registerNewEmployee.setDepartmentName("111");
        registerNewEmployee.setJobId(11L);
        registerNewEmployee.setJobName("111");
        registerNewEmployee.setBaseSalary(600);
        registerNewEmployee.setPerformanceSalary(500);
        registerNewEmployee.setTypesOfEmployees("111");
        Long isSuc = registerNewEmployeeManager.insert(registerNewEmployee);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        insert();
        RegisterNewEmployeeCondition registerNewEmployeeCondition = new RegisterNewEmployeeCondition();
       registerNewEmployeeCondition.setCreateUser("1111");
        List<RegisterNewEmployee> registerNewEmployeeList = registerNewEmployeeManager.list(registerNewEmployeeCondition);
        Assert.assertTrue(1 <= registerNewEmployeeList.size());
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        insert();
        RegisterNewEmployeeCondition registerNewEmployeeCondition = new RegisterNewEmployeeCondition();
        registerNewEmployeeCondition.setLead(true);
        registerNewEmployeeCondition.setCreateUser("1111");
        List<RegisterNewEmployee> registerNewEmployeeList = registerNewEmployeeManager.list(registerNewEmployeeCondition);
        Assert.assertTrue(1 <= registerNewEmployeeList.size());
        Long isSuc = registerNewEmployeeManager.deleteById(registerNewEmployeeList.get(0).getId());
        Assert.assertEquals(1,isSuc.intValue());

        RegisterNewEmployee registerNewEmployee = registerNewEmployeeManager.getById(registerNewEmployeeList.get(0).getId());
        Assert.assertNull(registerNewEmployee);
    }


}
