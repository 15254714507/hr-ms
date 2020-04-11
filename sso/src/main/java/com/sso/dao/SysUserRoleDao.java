package com.sso.dao;

import com.sso.domain.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/10 20:31
 */
@Mapper
public interface SysUserRoleDao {
    /**
     * 根据用户的id获得此用户所有的角色关系的集合
     *
     * @param userId
     * @return
     */
    public List<SysUserRole> listByUserId(Long userId);
}
