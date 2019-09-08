package com.cqupt.ssm.service.impl;

import com.cqupt.ssm.dao.IRoleDao;
import com.cqupt.ssm.domain.Permission;
import com.cqupt.ssm.domain.Role;
import com.cqupt.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lisheng in 10:33 2019/9/6
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    /**
     * 查询所有角色信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    /**
     * 新建role
     *
     * @param role
     */
    @Override
    public void saveRole(Role role) throws Exception{
        roleDao.saveRole(role);
    }

    @Override
    public Role findById(Integer roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(Integer roleId) throws Exception{
        return roleDao.findOtherPermissions(roleId);
    }

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionId
     */
    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionId) {
        for (int i = 0; i < permissionId.length; i++) {
            roleDao.addPermissionToRole(roleId,permissionId[i]);
        }
    }
}
