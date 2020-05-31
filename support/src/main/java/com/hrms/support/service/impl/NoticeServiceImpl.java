package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.NoticeCondition;
import com.hrms.api.domain.entity.Notice;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.NoticeService;
import com.hrms.support.manager.NoticeManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/31 15:45
 */
@Slf4j
@Service(interfaceClass = NoticeService.class)
public class NoticeServiceImpl implements NoticeService {
    @Resource
    NoticeManager noticeManager;

    @Override
    public Notice getById(Long id) throws DaoException {
        return noticeManager.getById(id);
    }

    @Override
    public Long insert(Notice notice) throws DaoException {
        return noticeManager.insert(notice);
    }

    @Override
    public Long deleteById(Long id) throws DaoException {
        return noticeManager.deleteById(id);
    }

    @Override
    public List<Notice> list(NoticeCondition noticeCondition) throws DaoException {
        return noticeManager.list(noticeCondition);
    }
}
