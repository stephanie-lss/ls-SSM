package com.cqupt.ssm.service;

import com.cqupt.ssm.domain.Permission;

import java.util.List;

/**
 * Created by Lisheng in 10:57 2019/9/6
 */
public interface IPermissionService {

    /**
     * 查询所有权限信息
     */
    List<Permission> findAll() throws Exception;

    /**
     * 新建权限信息
     * @param permission
     */
    void savePermission(Permission permission) throws Exception;
}
