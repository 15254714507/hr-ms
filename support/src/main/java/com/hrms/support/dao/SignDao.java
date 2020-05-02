package com.hrms.support.dao;

import com.hrms.api.domain.condition.SignCondition;
import com.hrms.api.domain.condition.UserCondition;
import com.hrms.api.domain.entity.Sign;
import com.hrms.api.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/2 21:54
 */
@Mapper
public interface SignDao {
    /**
     * 根据id获取签到信息
     *
     * @param id 用户id
     * @return 返回签到对象
     */
    public Sign getById(Long id);

    /**
     * 添加签到信息
     *
     * @param sign 要签到的对象
     * @return 返回是否成功 1是成功，否则其他的都会报错
     */
    public Long insert(Sign sign);

    /**
     * 修改签到信息
     *
     * @param sign
     * @return
     */
    public Long updateById(Sign sign);

    /**
     * 删除此签到信息
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);


    /**
     * 获得签到对象的集合
     *
     * @param signCondition
     * @return
     */
    public List<Sign> list(SignCondition signCondition);
}
