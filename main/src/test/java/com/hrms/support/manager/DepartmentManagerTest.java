package com.hrms.support.manager;

import com.hrms.api.domain.condition.DepartmentCondition;
import com.hrms.api.domain.entity.Department;
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
 * @date 2020/4/15 22:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class DepartmentManagerTest {
    @Resource
    DepartmentManager departmentManager;

    private void insert() {
        Department department = new Department();
        department.setDepartmentName("kc");
        department.setCreateUser("kc");
        department.setUpdateUser("kc");
        Long isSuc = departmentManager.insert(department);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void insertTest() {
        insert();
        Department department = new Department();
        department.setDepartmentName("kc");
        Long isError = departmentManager.insert(department);
        Assert.assertEquals(0, isError.intValue());
    }

    @Test
    @Transactional
    public void getByIdTest() {
        insert();
        DepartmentCondition departmentCondition = new DepartmentCondition();
        departmentCondition.setDepartmentName("kc");
        List<Department> departmentList = departmentManager.list(departmentCondition);
        Assert.assertEquals(1, departmentList.size());
        Department department = departmentManager.getById(departmentList.get(0).getId());
        Assert.assertNotNull(department);
    }

    @Test
    @Transactional
    public void updateByIdTest() {
        insert();
        DepartmentCondition departmentCondition = new DepartmentCondition();
        departmentCondition.setDepartmentName("kc");
        List<Department> departmentList = departmentManager.list(departmentCondition);
        Assert.assertEquals(1, departmentList.size());
        departmentList.get(0).setDepartmentName("ww");

        Long isSuc = departmentManager.updateById(departmentList.get(0));
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void deleteById() {
        insert();
        DepartmentCondition departmentCondition = new DepartmentCondition();
        departmentCondition.setDepartmentName("kc");
        List<Department> departmentList = departmentManager.list(departmentCondition);
        Assert.assertEquals(1, departmentList.size());
        Long id = departmentList.get(0).getId();
        Long isSuc = departmentManager.deleteById(id);
        Assert.assertEquals(1, isSuc.intValue());
        Department department = departmentManager.getById(id);
        Assert.assertNull(department);

    }


}
