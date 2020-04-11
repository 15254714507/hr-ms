package com.sso.dao;

import com.sso.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 孔超
 * @date 2020/4/9 15:17
 */
@Mapper
public interface SysUserDao {
    /**
     * 根据账号获得用户的信息
     *
     * @param userName 账号
     * @return
     */
    public SysUser getByUseName(String userName);
}
