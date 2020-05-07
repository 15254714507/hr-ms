package com.hrms.support.manager;

import com.hrms.api.domain.condition.WagesCondition;
import com.hrms.api.domain.entity.Wages;
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
 * @date 2020/5/6 19:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class WagesManagerTest {
    @Resource
    WagesManager wagesManager;

    public void insert() {
        Wages wages = new Wages();
        wages.setUsername("kc");
        wages.setName("55");
        wages.setBaseSalary(1.1);
        wages.setPerformanceSalary(1.1);
        wages.setPensionInsurance(1.1);
        wages.setMedicalInsurance(1.1);
        wages.setUnemploymentInsurance(1.1);
        wages.setInjuryInsurance(1.1);
        wages.setFertilityInsurance(1.1);
        wages.setHousingProvidentFund(1.1);
        wages.setPersonalIncomeTax(1.1);
        wages.setDaysOfAbsenteeism(1.1);
        wages.setDaysOfLeave(1.1);
        wages.setDaysOfRecess(1.1);
        wages.setDaysOfSickLeave(1.1);
        wages.setPaidWages(1.1);
        wages.setWagesDate(LocalDateTimeFactory.getLocalDate());
        wages.setCreateUser("kc");
        wages.setUpdateUser("kc");
        wages.setStatus(0);
        Long isSuc = wagesManager.insert(wages);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listTest() {
        insert();
        WagesCondition wagesCondition = new WagesCondition();
        wagesCondition.setUsername("kc");
        wagesCondition.setYear(LocalDateTimeFactory.getLocalDate().getYear());
        wagesCondition.setMonth(LocalDateTimeFactory.getLocalDate().getMonthValue());
        wagesCondition.setStatus(0);
        List<Wages> wagesList = wagesManager.list(wagesCondition);
        Assert.assertEquals(1, wagesList.size());
    }

    @Test
    @Transactional
    public void getByIdTest() {
        insert();
        WagesCondition wagesCondition = new WagesCondition();
        wagesCondition.setUsername("kc");
        wagesCondition.setYear(LocalDateTimeFactory.getLocalDate().getYear());
        wagesCondition.setMonth(LocalDateTimeFactory.getLocalDate().getMonthValue());
        List<Wages> wagesList = wagesManager.list(wagesCondition);
        Wages wages = wagesManager.getById(wagesList.get(0).getId());
        Assert.assertNotNull(wages);

    }

    @Test
    @Transactional
    public void deleteById() {
        insert();
        WagesCondition wagesCondition = new WagesCondition();
        wagesCondition.setUsername("kc");
        wagesCondition.setYear(LocalDateTimeFactory.getLocalDate().getYear());
        wagesCondition.setMonth(LocalDateTimeFactory.getLocalDate().getMonthValue());
        List<Wages> wagesList = wagesManager.list(wagesCondition);
        Long isSuc = wagesManager.deleteById(wagesList.get(0).getId());
        Assert.assertEquals(1, isSuc.intValue());
        Wages wages = wagesManager.getById(wagesList.get(0).getId());
        Assert.assertNull(wages);

    }

    @Test
    @Transactional
    public void updateById() {
        insert();
        WagesCondition wagesCondition = new WagesCondition();
        wagesCondition.setUsername("kc");
        wagesCondition.setYear(LocalDateTimeFactory.getLocalDate().getYear());
        wagesCondition.setMonth(LocalDateTimeFactory.getLocalDate().getMonthValue());
        List<Wages> wagesList = wagesManager.list(wagesCondition);

        Wages wages = wagesList.get(0);
        wages.setBaseSalary(1.2);
        wages.setPerformanceSalary(1.2);
        wages.setPensionInsurance(1.2);
        wages.setMedicalInsurance(1.2);
        wages.setUnemploymentInsurance(1.2);
        wages.setInjuryInsurance(1.2);
        wages.setFertilityInsurance(1.2);
        wages.setHousingProvidentFund(1.2);
        wages.setPersonalIncomeTax(1.2);
        wages.setDaysOfAbsenteeism(1.2);
        wages.setDaysOfLeave(1.2);
        wages.setDaysOfRecess(1.2);
        wages.setDaysOfSickLeave(1.2);
        wages.setPaidWages(1.2);
        wages.setStatus(1);
        Long isSuc = wagesManager.updateById(wages);
        Assert.assertEquals(1, isSuc.intValue());

        wages = wagesManager.getById(wagesList.get(0).getId());
        Assert.assertEquals(1.2, wages.getPerformanceSalary(), 1);
        Assert.assertEquals(1.2, wages.getBaseSalary(), 1);
        Assert.assertEquals(1.2, wages.getPensionInsurance(), 1);
        Assert.assertEquals(1.2, wages.getMedicalInsurance(), 1);
        Assert.assertEquals(1.2, wages.getUnemploymentInsurance(), 1);
        Assert.assertEquals(1.2, wages.getInjuryInsurance(), 1);
        Assert.assertEquals(1.2, wages.getFertilityInsurance(), 1);
        Assert.assertEquals(1.2, wages.getHousingProvidentFund(), 1);
        Assert.assertEquals(1.2, wages.getPersonalIncomeTax(), 1);
        Assert.assertEquals(1.2, wages.getDaysOfAbsenteeism(), 1);
        Assert.assertEquals(1.2, wages.getDaysOfLeave(), 1);
        Assert.assertEquals(1.2, wages.getDaysOfRecess(), 1);
        Assert.assertEquals(1.2, wages.getDaysOfSickLeave(), 1);
        Assert.assertEquals(1.2, wages.getPaidWages(), 1);
        Assert.assertEquals(1, wages.getStatus(), 1);


    }
}
