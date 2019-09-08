package com.cqupt.ssm.service.impl;

import com.cqupt.ssm.dao.IPermissionDao;
import com.cqupt.ssm.domain.Permission;
import com.cqupt.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lisheng in 10:58 2019/9/6
 */
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 查询所有权限信息
     */
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    /**
     * 新建权限信息
     *
     * @param permission
     */
    @Override
    public void savePermission(Permission permission) throws Exception {
        permissionDao.savePermission(permission);
    }
}
