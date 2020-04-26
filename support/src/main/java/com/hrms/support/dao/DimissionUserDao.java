package com.hrms.support.dao;

import com.hrms.api.domain.condition.DimissionUserCondition;
import com.hrms.api.domain.entity.DimissionUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/26 19:41
 */
@Mapper
public interface DimissionUserDao {
    /**
     * 根据id获取离职信息
     *
     * @param id 用户id
     * @return 返回离职对象
     */
    public DimissionUser getById(Long id);

    /**
     * 添加离职用户
     *
     * @param dimissionUser 申请离职的对象
     * @return 返回是否成功 1是成功，否则其他的都会报错
     */
    public Long insert(DimissionUser dimissionUser);

    /**
     * 修改离职用户中的某些信息
     *
     * @param dimissionUser
     * @return
     */
    public Long updateById(DimissionUser dimissionUser);

    /**
     * 删除此离职信息
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);

    /**
     * 获得离职用户的集合
     *
     * @param dimissionUserCondition
     * @return
     */
    public List<DimissionUser> list(DimissionUserCondition dimissionUserCondition);
}
