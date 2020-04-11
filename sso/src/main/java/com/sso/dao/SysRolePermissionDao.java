package com.sso.dao;

import com.sso.domain.entity.SysRole;
import com.sso.domain.entity.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/10 19:29
 */
@Mapper
public interface SysRolePermissionDao {
    /**
     * 通过role的id集合获取角色和权限的关心对应信息的集合
     *
     * @param roleIdList
     * @return
     */
    public List<SysRolePermission> listByRoleIds(@Param("list") List<Long> roleIdList);
}
