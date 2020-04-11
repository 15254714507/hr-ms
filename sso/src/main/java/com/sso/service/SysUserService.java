package com.sso.service;


import com.sso.domain.entity.SysUser;

/**
 * @author 孔超
 * @date 2020/4/9 14:58
 */
public interface SysUserService {
    /**
     * 根据账号获得用户的信息
     *
     * @param userName 账号
     * @return
     */
    public SysUser getByUseName(String userName);


}
