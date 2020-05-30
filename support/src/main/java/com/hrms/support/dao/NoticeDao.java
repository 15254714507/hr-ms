package com.hrms.support.dao;

import com.hrms.api.domain.condition.NoticeCondition;
import com.hrms.api.domain.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/30 22:41
 */
@Mapper
public interface NoticeDao {
    /**
     * 根据id获得通知对象
     *
     * @param id
     * @return
     */
    public Notice getById(Long id);

    /**
     * 添加新的通知
     *
     * @param notice
     * @return
     */
    public Long insert(Notice notice);

    /**
     * 修改通知的内容
     *
     * @param notice
     * @return
     */
    public Long updateById(Notice notice);

    /**
     * 删除通知
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得通知的集合
     *
     * @param noticeCondition
     * @return
     */
    public List<Notice> list(NoticeCondition noticeCondition);
}
