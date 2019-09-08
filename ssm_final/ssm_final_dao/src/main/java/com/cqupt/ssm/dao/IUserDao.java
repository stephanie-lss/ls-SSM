package com.cqupt.ssm.dao;

import com.cqupt.ssm.domain.Role;
import com.cqupt.ssm.domain.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LiSheng
 * @date 2019/9/5 12:52
 */
public interface IUserDao {

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many =
            @Many(select = "com.cqupt.ssm.dao.IRoleDao.findRoleById"))})
    Users findByUsername(String username) throws Exception;

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Select("select * from users")
    List<Users> findAll() throws Exception;

    /**
     * 保存用户信息
     *
     * @param user
     */
    @Insert("insert into users(username,password,email,phoneNum,status) values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void saveUser(Users user) throws Exception;

    /**
     * 根据用户id查询用户详细信息
     *
     * @param id
     * @return
     */

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "email", property = "email"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.cqupt.ssm.dao.IRoleDao.findRoleById"))
    })
    Users findById(Integer id);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{uid})")
    List<Role> findOtherRoles(Integer uid);

    /**
     * 添加角色信息到用户
     *
     * @param uid
     * @param roleId
     */
    @Insert("insert into users_role(userId,roleId) values(#{uid},#{roleId})")
    void addRoleToUser(@Param("uid") int uid, @Param("roleId") Integer roleId);
}
