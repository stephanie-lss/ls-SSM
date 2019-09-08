package com.cqupt.ssm.dao;

import com.cqupt.ssm.domain.Permission;
import com.cqupt.ssm.domain.Role;
import com.cqupt.ssm.domain.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LiSheng
 * @date 2019/9/5 14:40
 */
public interface IRoleDao {

    /**
     * 根据用户的ID查询角色信息
     *
     * @param uid
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{uid})")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = List.class, many = @Many(select = "com.cqupt.ssm.dao.IPermissionDao.findById"))
    })
    List<Role> findRoleById(Integer uid) throws Exception;

    /**
     * 查询所有角色信息
     *
     * @return
     */
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    /**
     * 新建role
     *
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role);

    /**
     * 根据角色id查询角色信息
     *
     * @param roleId
     * @return
     */
    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = List.class, many = @Many(select = "com.cqupt.ssm.dao.IPermissionDao.findById"))
    })
    Role findById(Integer roleId);

    /**
     * 根据角色id查询角色未拥有的资源权限信息
     *
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(Integer roleId);

    /**
     * 给角色id对应的角色添加权限信息
     *
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}
