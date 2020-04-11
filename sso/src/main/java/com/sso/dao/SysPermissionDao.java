package com.sso.dao;

import com.sso.domain.entity.SysPermission;
import com.sso.domain.entity.SysRolePermission;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/10 19:50
 */
@Mapper
public interface SysPermissionDao {
    /**
     * 根据角色和权限的关系信息中的权限的id获得权限的信息的集合
     *
     * @param permissionIdList
     * @return
     */
    public List<SysPermission> listByPermissionIds(@Param("list") List<Long> permissionIdList);
}
