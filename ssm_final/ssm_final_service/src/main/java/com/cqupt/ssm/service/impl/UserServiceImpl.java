package com.cqupt.ssm.service.impl;

import com.cqupt.ssm.dao.IUserDao;
import com.cqupt.ssm.domain.Role;
import com.cqupt.ssm.domain.Users;
import com.cqupt.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LiSheng
 * @date 2019/9/5 12:51
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {


    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = null;
        try {
            users = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
//        User user=new User(users.getUsername(),"{noop}"+users.getPassword(),getAuthority(users.getRoles()));
        User user = new User(users.getUsername(),users.getPassword(), users.getStatus() == 0 ? false : true, true, true, true, getAuthority(users.getRoles()));
        return user;
}

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<Users> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void saveUser(Users user) throws Exception {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    public Users findById(Integer id) throws Exception{
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer uid) throws Exception {
        return userDao.findOtherRoles(uid);
    }

    @Override
    public void addRoleToUser(Integer uid, Integer[] roleId) throws Exception {
        for (int i = 0; i < roleId.length; i++) {
            userDao.addRoleToUser(uid,roleId[i]);
        }
    }

}
