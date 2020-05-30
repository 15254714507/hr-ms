package com.hrms.support.manager;

import com.hrms.api.domain.condition.NoticeCondition;
import com.hrms.api.domain.entity.Notice;
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
 * @date 2020/5/30 23:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.hrms.main.MainApplication.class)
public class NoticeManagerImpl {
    @Resource
    NoticeManager noticeManager;

    public void insert() {
        Notice notice = new Notice();
        notice.setTitle("111");
        notice.setContent("111");
        notice.setDeadline(LocalDateTimeFactory.getLocalDateTime());
        notice.setCreateUser("kc");
        notice.setUpdateUser("kc");
        Long isSuc = noticeManager.insert(notice);
        Assert.assertEquals(1, isSuc.intValue());
    }

    @Test
    @Transactional
    public void listByTest() {
        insert();
        NoticeCondition noticeCondition = new NoticeCondition();
        noticeCondition.setDeadline(LocalDateTimeFactory.getLocalDateTime());
        List<Notice> noticeList = noticeManager.list(noticeCondition);
        Assert.assertTrue(noticeList.size() > 0);
    }

    @Test
    @Transactional
    public void getByIdTest() {
        insert();
        NoticeCondition noticeCondition = new NoticeCondition();
        noticeCondition.setDeadline(LocalDateTimeFactory.getLocalDateTime());
        List<Notice> noticeList = noticeManager.list(noticeCondition);

        Notice notice = noticeManager.getById(noticeList.get(0).getId());
        Assert.assertNotNull(notice);
    }

    @Test
    @Transactional
    public void updateByIdTest() {
        insert();
        NoticeCondition noticeCondition = new NoticeCondition();
        noticeCondition.setDeadline(LocalDateTimeFactory.getLocalDateTime());
        List<Notice> noticeList = noticeManager.list(noticeCondition);
        Notice notice = noticeManager.getById(noticeList.get(0).getId());
        notice.setTitle("222");
        notice.setContent("222");
        notice.setDeadline(LocalDateTimeFactory.getLocalDateTime());
        Long isSuc = noticeManager.updateById(notice);
        Assert.assertEquals(1,isSuc.intValue());

        Notice notice1 = noticeManager.getById(notice.getId());
        Assert.assertNotNull(notice1);
        Assert.assertEquals("222",notice1.getTitle());
        Assert.assertEquals("222",notice1.getContent());
    }

    @Test
    @Transactional
    public void deleteByIdTest() {
        insert();
        NoticeCondition noticeCondition = new NoticeCondition();
        noticeCondition.setDeadline(LocalDateTimeFactory.getLocalDateTime());
        List<Notice> noticeList = noticeManager.list(noticeCondition);
        Notice notice = noticeList.get(0);
        Long isSuc = noticeManager.deleteById(notice.getId());
        Assert.assertEquals(1, isSuc.intValue());
        Notice notice1 = noticeManager.getById(notice.getId());
        Assert.assertNull(notice1);
    }
}
