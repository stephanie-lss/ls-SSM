package com.cqupt.ssm.service;

import com.cqupt.ssm.domain.Role;
import com.cqupt.ssm.domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author LiSheng
 * @date 2019/9/5 12:50
 */
public interface IUserService extends UserDetailsService {

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<Users> findAll() throws Exception;

    /**
     * 保存用户信息
     *
     * @param user
     */
    void saveUser(Users user) throws Exception;

    /**
     * 根据用户id查询用户详情信息
     * @param id
     * @return
     * @throws Exception
     */
    Users findById(Integer id) throws Exception;

    /**
     * 根据用户id查询用户可以添加的角色
     * @param uid
     * @return
     * @throws Exception
     */
    List<Role> findOtherRoles(Integer uid) throws Exception;

    /**
     * 给用户添加角色
     * @param uid
     * @param roleId
     */
    void addRoleToUser(Integer uid, Integer[] roleId) throws Exception;
}
