package com.furongsoft.cms.controllers;

import com.furongsoft.base.rbac.entities.Permission;
import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.mappers.PermissionDao;
import com.furongsoft.base.rbac.mappers.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.furongsoft.base.rbac.security.PasswordHelper.encryptPassword;

/**
 * 首页控制器
 *
 * @author Alex
 */
@Controller
public class HomeController {
    private final UserDao userDao;
    private final PermissionDao permissionDao;

    @Autowired
    public HomeController(UserDao userDao, PermissionDao permissionDao) {
        this.userDao = userDao;
        this.permissionDao = permissionDao;
    }

    @RequestMapping("/")
    public String index() {
        return "forward:/cms/schoolWebsite/index";
    }

    @RequestMapping("/home/init")
    @ResponseBody
    public String initialize() {
        /*
        User user = new User();
        user.setUserName("admin");
        user.setPassword("123456");
        user.setName("");
        user.setSalt("");
        userDao.insert(encryptPassword(user));
        */

        Permission pTest1 = new Permission();
        pTest1.setName("学校管理");
        pTest1.setPath("");
        pTest1.setType(0);
        pTest1.setState(0);
        permissionDao.insert(pTest1);

        Permission pTest3 = new Permission();
        pTest3.setParentId(pTest1.getId());
        pTest3.setName("学校信息");
        pTest3.setPath("addons/schoolWebsite/school/index.html");
        pTest3.setType(0);
        pTest3.setState(0);
        permissionDao.insert(pTest3);

        Permission pTest4 = new Permission();
        pTest4.setParentId(pTest1.getId());
        pTest4.setName("师资队伍");
        pTest4.setPath("addons/schoolWebsite/teachers/index.html");
        pTest4.setType(0);
        pTest4.setState(0);
        permissionDao.insert(pTest4);

        Permission pTest6 = new Permission();
        pTest6.setParentId(pTest1.getId());
        pTest6.setName("实验教学");
        pTest6.setPath("addons/schoolWebsite/experiment/index.html");
        pTest6.setType(0);
        pTest6.setState(0);
        permissionDao.insert(pTest6);

        Permission pTest7 = new Permission();
        pTest7.setParentId(pTest1.getId());
        pTest7.setName("实验管理");
        pTest7.setPath("addons/schoolWebsite/regulations/index.html");
        pTest7.setType(0);
        pTest7.setState(0);
        permissionDao.insert(pTest7);

        Permission pTest8 = new Permission();
        pTest8.setParentId(pTest1.getId());
        pTest8.setName("中心公告");
        pTest8.setPath("addons/schoolWebsite/announcement/index.html");
        pTest8.setType(0);
        pTest8.setState(0);
        permissionDao.insert(pTest8);

        Permission pTest2 = new Permission();
        pTest2.setName("系统管理");
        pTest2.setPath("");
        pTest2.setType(0);
        pTest2.setState(0);
        permissionDao.insert(pTest2);

        Permission pTest5 = new Permission();
        pTest5.setParentId(pTest2.getId());
        pTest5.setName("权限管理");
        pTest5.setPath("system/permission/index.html");
        pTest5.setType(0);
        pTest5.setState(0);
        permissionDao.insert(pTest5);

        return "Successful";
    }
}
