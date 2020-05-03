package com.hrms.support.dao;

import com.hrms.api.domain.condition.RequestForLeaveCondition;
import com.hrms.api.domain.entity.RequestForLeave;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/3 22:31
 */
@Mapper
public interface RequestForLeaveDao {
    /**
     * 根据id获得指定的请假信息
     *
     * @param id
     * @return
     */
    public RequestForLeave getById(Long id);

    /**
     * 添加新的请假的信息
     *
     * @param requestForLeave
     * @return
     */
    public Long insert(RequestForLeave requestForLeave);

    /**
     * 修改请假信息
     *
     * @param requestForLeave
     * @return
     */
    public Long updateById(RequestForLeave requestForLeave);

    /**
     * 删除请假信息
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得申请假的信息的集合
     *
     * @param requestForLeaveCondition
     * @return
     */
    public List<RequestForLeave> list(RequestForLeaveCondition requestForLeaveCondition);
}
