package com.hrms.support.scheduletask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 孔超
 * @date 2020/5/5 2:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class SignScheduleTaskTest {
    @Resource
    SignScheduleTask signScheduleTask;

    @Test
    public void signScheduleTaskTest() {
//        signScheduleTask.signScheduleTaskLogin();
    }
}
