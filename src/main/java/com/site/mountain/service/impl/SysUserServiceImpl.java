package com.site.mountain.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.SysMenuDao;
import com.site.mountain.dao.mysql.SysRoleMenuDao;
import com.site.mountain.dao.mysql.SysUserDao;
import com.site.mountain.dao.mysql.SysUserRoleDao;
import com.site.mountain.entity.SysMenu;
import com.site.mountain.entity.SysUser;
import com.site.mountain.entity.SysUserRole;
import com.site.mountain.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    private final static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    @Transactional(value = "pgSqlTransactionManager", rollbackFor = Exception.class, timeout = 36000)
    public int insert(SysUser pojo) {
        int flag = 0;
        flag = sysUserDao.insert(pojo);
        if (pojo.getRoles().length > 0) {
            flag = sysUserDao.insertUserAndRole(pojo);
        }
        return flag;
    }

    @Override
    public int insertSelective(SysUser pojo) {
        return sysUserDao.insertSelective(pojo);
    }

    @Override
    public List findList(SysUser SysUser) {
        return sysUserDao.findList(SysUser);
    }

    @Override
    public List findUserList(SysUser SysUser) {
        return sysUserDao.findUserList(SysUser);
    }

    @Override
    public List findListByRole(SysUser SysUser) {
        return sysUserDao.findListByRole(SysUser);
    }

    public SysUser findByUser(SysUser sysUser) {
        return sysUserDao.findByUser(sysUser);
    }

    @Override
    public PageInfo<SysUser> findListPage(SysUser pojo) {
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        List<SysUser> list = sysUserDao.findList(pojo);
        PageInfo<SysUser> page = new PageInfo<SysUser>(list);
        return page;
    }

    @Override
    public List<SysUser> selectAllUserAndRoles(SysUser sysUser) {
        return sysUserDao.selectAllUserAndRoles(sysUser);
    }

    @Override
    public SysMenu getMenuTree(SysUser sysUser) {
//        SysMenu sysMenu = null;
        List<String> selectedMenu = new ArrayList<String>();
        List<SysUser> list = sysUserDao.selectAllUserAndRoles(sysUser);
        SysUserRole sysUserRole = new SysUserRole();
        for (SysMenu temp : list.get(0).getMenuList()) {
            selectedMenu.add(temp.getMenuId().toString());
        }


//        sysUserRole.setUserId(list.get(0).getUserId());
//        List<SysUserRole> sysUserRoleList = sysUserRoleDao.findList(sysUserRole);
//        BigInteger bigInteger = new BigInteger("-1");
//        for (SysUserRole sysUserRoleTemp : sysUserRoleList) {
////            sysMenu = getMenuTreeByRoleId(bigInteger, sysUserRoleTemp.getRoleId());
//            SysRoleMenu sysRoleMenu = new SysRoleMenu();
//            sysRoleMenu.setRoleId(sysUserRoleTemp.getRoleId());
//        }
        BigInteger bigInteger = new BigInteger("-1");
        SysMenu sysMenu = getMenuTreeByRoleId(bigInteger, selectedMenu);
        return sysMenu;
    }

    public JSONArray getPermissionRouter(SysUser sysUser) {
        List<SysUser> sysUserList = selectAllUserAndRoles(sysUser);
        Integer[] roles = null;
        if (sysUserList != null && sysUserList.size() > 0) {
            SysUser sysUserTemp = sysUserList.get(0);
            roles = sysUserTemp.getRoles();
        }

        List<String> selectedMenu = new ArrayList<String>();
        List<SysUser> list = sysUserDao.selectAllUserAndRoles(sysUser);
        for (SysMenu temp : list.get(0).getMenuList()) {
            selectedMenu.add(temp.getMenuId().toString());
        }
        BigInteger bigInteger = new BigInteger("-1");
        JSONObject sysMenuJsonObject = getRouterTreeByRoleId(bigInteger, selectedMenu, roles);
//        SysMenu sysMenu = getMenuTreeByRoleId(bigInteger, selectedMenu);
        JSONArray jsonArray = sysMenuJsonObject.getJSONArray("children");
        return jsonArray;
    }

    public SysMenu getMenuTreeByRoleId(BigInteger menuId, List selectedMenu) {
        SysMenu tree = new SysMenu();
        tree = sysMenuDao.selectByid(menuId);
        SysMenu spn = new SysMenu();
        spn.setParentId(menuId);
        List<SysMenu> children = sysMenuDao.selectByPid(spn);
        for (SysMenu menu : children) {
            SysMenu s = getMenuTreeByRoleId(menu.getMenuId(), selectedMenu);

//            SysRoleMenu sysRoleMenu = new SysRoleMenu();
//            sysRoleMenu.setMenuId(menu.getMenuId());
//            sysRoleMenu.setRoleId(roleId);
//            int count = sysRoleMenuDao.findCount(sysRoleMenu);
            if (selectedMenu.contains(menu.getMenuId().toString())) {
                tree.getChildren().add(s);
            } else {
                if (s.getChildren().size() > 0) {
                    tree.getChildren().add(s);
                }
            }
        }
//        if (children.size() == 0) {
//            SysRoleMenu sysRoleMenu = new SysRoleMenu();
//            sysRoleMenu.setMenuId(menuId);
//            sysRoleMenu.setRoleId(roleId);
//            int count = sysRoleMenuDao.findCount(sysRoleMenu);
//            if (count == 0) {
//                tree.getChildren().remove(tree.getChildren().size() - 1);
//            }
//        }
        return tree;
    }

    public JSONObject getRouterTreeByRoleId(BigInteger menuId, List selectedMenu, Integer[] roles) {
        SysMenu tree = sysMenuDao.selectByid(menuId);
        JSONObject treeJson = new JSONObject();
        JSONObject metaJson = new JSONObject();
        metaJson.put("title", tree.getParentId() + "." + tree.getMenuId());
        metaJson.put("title", tree.getName());
        metaJson.put("icon", tree.getIcon());
        metaJson.put("rank", tree.getOrderNum());
        metaJson.put("roles", roles);


        String url = tree.getUrl() == null ? "" : tree.getUrl();
        String[] urls = url.split(";");
        String path = "";
        String name = "";
        if (urls.length > 1) {
            name = urls[0];
            path = urls[1];
        } else {
            path = urls[0];
        }

        treeJson.put("path", path);
        treeJson.put("meta", metaJson);
        treeJson.put("name", name);

        SysMenu spn = new SysMenu();
        spn.setParentId(menuId);
        List<SysMenu> children = sysMenuDao.selectByPid(spn);
        for (SysMenu menu : children) {
            if (menu.getType() != 2) {// 如果是按钮跳过
                JSONObject s = getRouterTreeByRoleId(menu.getMenuId(), selectedMenu, roles);
                JSONArray jsonArrayTemp = treeJson.getJSONArray("children");
                if (jsonArrayTemp == null) {
                    jsonArrayTemp = new JSONArray();
                }
                if (selectedMenu.contains(menu.getMenuId().toString())) {
//                tree.getChildren().add(s);
                    jsonArrayTemp.add(s);
                    treeJson.put("children", jsonArrayTemp);

                } else {
                    JSONArray sChildrenJSONArray = s.getJSONArray("children");
                    if (sChildrenJSONArray == null) {
                        sChildrenJSONArray = new JSONArray();
                    }
                    if (sChildrenJSONArray.size() > 0) {
//                    tree.getChildren().add(s);
                        jsonArrayTemp.add(s);
                        treeJson.put("children", jsonArrayTemp);
                    }
                }
            }
        }
        return treeJson;
//        return tree;
    }

    @Override
    public int delete(SysUser SysUser) {
        return sysUserDao.delete(SysUser);
    }

    @Override
    public int update(SysUser sysUser) {
        int flag = sysUserDao.update(sysUser);
        flag = sysUserDao.deleteUserAndRole(sysUser);
        flag = sysUserDao.insertUserAndRole(sysUser);
        return flag;
    }

    @Override
    public int updatePassword(SysUser sysUser) {
        return sysUserDao.updatePassword(sysUser);
    }

}
