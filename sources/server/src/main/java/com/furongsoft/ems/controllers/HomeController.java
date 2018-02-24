package com.furongsoft.ems.controllers;

import com.alibaba.fastjson.JSON;
import com.furongsoft.base.file.mappers.AttachmentDao;
import com.furongsoft.base.rbac.entities.Resource;
import com.furongsoft.base.rbac.entities.User;
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
    private final UserDao mUserDao;
    private final ResourceDao mResourceDao;
    private final AttachmentDao mAttachmentDao;

    @Autowired
    public HomeController(UserDao userDao, ResourceDao resourceDao, AttachmentDao mAttachmentDao) {
        this.mUserDao = userDao;
        this.mResourceDao = resourceDao;
        this.mAttachmentDao = mAttachmentDao;
    }

    @RequestMapping("/index")
    public String index() {
        List<User> list = mUserDao.selectList(null);

        return "Hello, world! " + JSON.toJSONString(list);
    }

    @RequestMapping("/init")
    public String Initialize() {
        User user = new User();
        user.setUserName("a");
        user.setPassword("b");
        user.setName("");
        user.setSalt("");
        mUserDao.insert(user);

        for (int i = 0; i < 30; ++i) {
            Resource resource = new Resource("系统管理" + i, 0, "/system", 0, null);
            mResourceDao.insert(resource);
        }

        return "Successful";
    }
}
