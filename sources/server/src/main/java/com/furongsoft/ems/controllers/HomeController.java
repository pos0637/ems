package com.furongsoft.ems.controllers;

import com.alibaba.fastjson.JSON;
import com.furongsoft.base.file.mappers.AttachmentDao;
import com.furongsoft.base.rbac.entities.Permission;
import com.furongsoft.base.rbac.entities.Resource;
import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.mappers.PermissionDao;
import com.furongsoft.base.rbac.mappers.ResourceDao;
import com.furongsoft.base.rbac.mappers.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 首页控制器
 *
 * @author Alex
 */
@Controller
@ResponseBody
@RequestMapping("/home")
public class HomeController {
    private final UserDao userDao;
    private final ResourceDao resourceDao;
    private final AttachmentDao attachmentDao;
    private final PermissionDao permissionDao;

    @Autowired
    public HomeController(UserDao userDao, ResourceDao resourceDao, AttachmentDao attachmentDao, PermissionDao permissionDao) {
        this.userDao = userDao;
        this.resourceDao = resourceDao;
        this.attachmentDao = attachmentDao;
        this.permissionDao = permissionDao;
    }

    @RequestMapping("/index")
    public String index() {
        List<User> list = userDao.selectList(null);

        return "Hello, world! " + JSON.toJSONString(list);
    }

    @RequestMapping("/init")
    public String initialize() {
        User user = new User();
        user.setUserName("a");
        user.setPassword("b");
        user.setName("");
        user.setSalt("");
        userDao.insert(user);

        for (int i = 0; i < 30; ++i) {
            Resource resource = new Resource("系统管理" + i, 0, "/system", 0, null);
            resourceDao.insert(resource);
        }

        Permission pTest1 = new Permission();
        pTest1.setName("首页");
        pTest1.setPath("");
        pTest1.setType(0);
        pTest1.setState(0);
        permissionDao.insert(pTest1);

        Permission pTest3 = new Permission();
        pTest3.setParentId(pTest1.getId());
        pTest3.setName("公司信息");
        pTest3.setPath("dist/admin/cms/companyInfo/index.html");
        pTest3.setType(0);
        pTest3.setState(0);
        permissionDao.insert(pTest3);

        Permission pTest4 = new Permission();
        pTest4.setParentId(pTest1.getId());
        pTest4.setName("test4");
        pTest4.setPath("xxx");
        pTest4.setType(0);
        pTest4.setState(0);
        permissionDao.insert(pTest4);

        Permission pTest2 = new Permission();
        pTest2.setName("系统管理");
        pTest2.setPath("");
        pTest2.setType(0);
        pTest2.setState(0);
        permissionDao.insert(pTest2);

        Permission pTest5 = new Permission();
        pTest5.setParentId(pTest2.getId());
        pTest5.setName("权限管理");
        pTest5.setPath("dist/admin/system/permission/index.html");
        pTest5.setType(0);
        pTest5.setState(0);
        permissionDao.insert(pTest5);

        Permission pTest6 = new Permission();
        pTest6.setParentId(pTest2.getId());
        pTest6.setName("test6");
        pTest6.setPath("xxx");
        pTest6.setType(0);
        pTest6.setState(0);
        permissionDao.insert(pTest6);

        return "Successful";
    }
}
