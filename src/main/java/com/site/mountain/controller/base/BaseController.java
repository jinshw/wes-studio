package com.site.mountain.controller.base;

import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseController {

    @Autowired
    SysUserService sysUserService;

    public List<SysUser> getUserList(){
        List<SysUser> list = null;
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            list = sysUserService.findListByRole(sysUser);
        } else {
            list = sysUserService.findList(new SysUser());
        }
        return list;
    }
}
