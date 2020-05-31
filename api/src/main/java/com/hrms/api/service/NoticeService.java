package com.hrms.api.service;

import com.hrms.api.domain.condition.NoticeCondition;
import com.hrms.api.domain.entity.Notice;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/31 15:45
 */
public interface NoticeService {
    /**
     * 根据id获得通知对象
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Notice getById(Long id) throws DaoException;
    /**
     * 添加新的通知
     *
     * @param notice
     * @return
     * @throws DaoException
     */
    public Long insert(Notice notice) throws DaoException;
    /**
     * 获得通知的集合
     *
     * @param noticeCondition
     * @return
     * @throws DaoException
     */
    public List<Notice> list(NoticeCondition noticeCondition) throws DaoException;
}
