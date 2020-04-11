package com.sso.dao;

import com.sso.domain.entity.SysRole;
import com.sso.domain.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/10 20:55
 */
@Mapper
public interface SysRoleDao {

    /**
     * 角色的id的集合获得角色信息的集合
     *
     * @param roleIdList
     * @return
     */
    public List<SysRole> listByIds(@Param("list") List<Long> roleIdList);
}
