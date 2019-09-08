package com.cqupt.ssm.service;

import com.cqupt.ssm.domain.Permission;
import com.cqupt.ssm.domain.Role;

import java.util.List;

/**
 * Created by Lisheng in 10:33 2019/9/6
 */
public interface IRoleService {

    /**
     * 查询所有角色信息
     *
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws Exception;

    /**
     * 新建role
     *
     * @param role
     */
    void saveRole(Role role) throws Exception;

    /**
     * 根据角色id查询角色信息
     * @param roleId
     * @return
     */
    Role findById(Integer roleId) throws Exception;

    /**
     * 根据角色id查询未拥有的权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermissions(Integer roleId) throws Exception;

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     */
    void addPermissionToRole(Integer roleId, Integer[] permissionId);
}
