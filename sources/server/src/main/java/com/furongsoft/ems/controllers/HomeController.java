package com.furongsoft.ems.controllers;

import com.alibaba.fastjson.JSON;
import com.furongsoft.base.file.mappers.AttachmentDao;
import com.furongsoft.base.rbac.entities.Permission;
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
        /*
        User user = new User();
        user.setUserName("a");
        user.setPassword("b");
        user.setName("");
        user.setSalt("");
        userDao.insert(user);
        */

        Permission pTest1 = new Permission();
        pTest1.setName("公司管理");
        pTest1.setPath("");
        pTest1.setType(0);
        pTest1.setState(0);
        permissionDao.insert(pTest1);

        Permission pTest3 = new Permission();
        pTest3.setParentId(pTest1.getId());
        pTest3.setName("公司信息");
        pTest3.setPath("dist/admin/cms/company/index.html");
        pTest3.setType(0);
        pTest3.setState(0);
        permissionDao.insert(pTest3);

        Permission pTest4 = new Permission();
        pTest4.setParentId(pTest1.getId());
        pTest4.setName("公司描述");
        pTest4.setPath("dist/admin/cms/profile/index.html");
        pTest4.setType(0);
        pTest4.setState(0);
        permissionDao.insert(pTest4);

        Permission pTest6 = new Permission();
        pTest6.setParentId(pTest1.getId());
        pTest6.setName("新闻分类");
        pTest6.setPath("dist/admin/cms/news/category/index.html");
        pTest6.setType(0);
        pTest6.setState(0);
        permissionDao.insert(pTest6);

        Permission pTest7 = new Permission();
        pTest7.setParentId(pTest1.getId());
        pTest7.setName("新闻管理");
        pTest7.setPath("dist/admin/cms/news/index.html");
        pTest7.setType(0);
        pTest7.setState(0);
        permissionDao.insert(pTest7);

        Permission pTest8 = new Permission();
        pTest8.setParentId(pTest1.getId());
        pTest8.setName("招聘信息");
        pTest8.setPath("dist/admin/cms/job/index.html");
        pTest8.setType(0);
        pTest8.setState(0);
        permissionDao.insert(pTest8);

        Permission pTest9 = new Permission();
        pTest9.setParentId(pTest1.getId());
        pTest9.setName("产品分类");
        pTest9.setPath("dist/admin/cms/product/category/index.html");
        pTest9.setType(0);
        pTest9.setState(0);
        permissionDao.insert(pTest9);

        Permission pTest10 = new Permission();
        pTest10.setParentId(pTest1.getId());
        pTest10.setName("产品管理");
        pTest10.setPath("dist/admin/cms/product/index.html");
        pTest10.setType(0);
        pTest10.setState(0);
        permissionDao.insert(pTest10);

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

        return "Successful";
    }
}
