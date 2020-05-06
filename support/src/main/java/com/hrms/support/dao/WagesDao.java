package com.hrms.support.dao;

import com.hrms.api.domain.condition.WagesCondition;
import com.hrms.api.domain.entity.Wages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/6 2:35
 */
@Mapper
public interface WagesDao {
    /**
     * 根据id获取工资信息
     *
     * @param id 用户id
     * @return 返回工资对象
     */
    public Wages getById(Long id);

    /**
     * 添加新的工资信息
     *
     * @param wages
     * @return 返回是否成功 1是成功，否则其他的都会报错
     */
    public Long insert(Wages wages);

    /**
     * 修改工资信息
     *
     * @param wages
     * @return
     */
    public Long updateById(Wages wages);

    /**
     * 删除此工资信息 逻辑删除
     *
     * @param id
     * @return
     */
    public Long deleteById(Long id);


    /**
     * 获得工资对象的集合
     *
     * @param wagesCondition
     * @return
     */
    public List<Wages> list(WagesCondition wagesCondition);
}
