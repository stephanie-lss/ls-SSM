package com.cqupt.ssm.controller;

import com.cqupt.ssm.domain.Role;
import com.cqupt.ssm.domain.Users;
import com.cqupt.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author LiSheng
 * @date 2019/9/5 12:49
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Users> usersList = userService.findAll();
        mv.addObject("userList", usersList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    public String saveUser(Users user) throws Exception {
        userService.saveUser(user);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Users user = userService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 查询用户以及用户可以添加的角色
     *
     * @param id
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) Integer uid) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户,目的是为了将用户的id传到user-role-add.jsp页面中，因为在给用户添加角色信息的时候需要用户的id
        Users user = userService.findById(uid);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(uid);
        mv.addObject("user", user);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) Integer uid,@RequestParam(name = "ids",required = true) Integer[] roleId) throws Exception {
        userService.addRoleToUser(uid,roleId);
        return "redirect:findAll";
    }
}
