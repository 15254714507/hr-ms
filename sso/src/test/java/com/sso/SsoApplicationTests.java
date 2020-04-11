package com.sso;

import com.sso.domain.entity.SysUser;
import com.sso.service.SysUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SsoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Resource
    SysUserService sysUserService;
    @Test
    public void getUserTest(){
        SysUser sysUser = sysUserService.getByUseName("admin");
        Assert.assertNotNull(sysUser);
    }

}
