package com.cqupt.ssm.controller;

import com.cqupt.ssm.domain.Permission;
import com.cqupt.ssm.domain.Role;
import com.cqupt.ssm.service.IPermissionService;
import com.cqupt.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Lisheng in 10:33 2019/9/6
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String saveRole(Permission permission) throws Exception {
        permissionService.savePermission(permission);
        return "redirect:findAll";
    }

}
