package com.hrms.support.manager;

import com.hrms.api.domain.condition.NoticeCondition;
import com.hrms.api.domain.entity.Notice;
import com.hrms.api.exception.DaoException;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/30 23:38
 */
public interface NoticeManager {
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
     * 修改通知的内容
     *
     * @param notice
     * @return
     * @throws DaoException
     */
    public Long updateById(Notice notice) throws DaoException;

    /**
     * 删除通知
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteById(Long id) throws DaoException;

    /**
     * 获得通知的集合
     *
     * @param noticeCondition
     * @return
     * @throws DaoException
     */
    public List<Notice> list(NoticeCondition noticeCondition) throws DaoException;
}
