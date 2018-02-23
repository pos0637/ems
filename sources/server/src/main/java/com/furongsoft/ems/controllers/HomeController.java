package com.furongsoft.ems.controllers;

import com.alibaba.fastjson.JSON;
import com.furongsoft.base.rbac.entities.Resource;
import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.mappers.ResourceDao;
import com.furongsoft.base.rbac.mappers.UserDao;
import com.furongsoft.base.rbac.repositories.ResourceRepository;
import com.furongsoft.base.rbac.repositories.UserRepository;
import com.furongsoft.base.rbac.security.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
    private final UserRepository mUserRepository;
    private final UserDao mUserDao;
    private final ResourceRepository mResourceRepository;

    @Autowired
    public HomeController(UserRepository userRepository, UserDao userDao, ResourceRepository resourceRepository) {
        mUserRepository = userRepository;
        mUserDao = userDao;
        mResourceRepository = resourceRepository;
    }

    @RequestMapping("/index")
    public String index() {
        List<User> list = mUserDao.find();

        return "Hello, world! " + JSON.toJSONString(list);
    }

    @RequestMapping("/init")
    public String Initialize() {
        Resource resource = new Resource("系统管理", 0, "/system", 0, null);
        resource.setCreateUser(0L);
        resource.setCreateTime(new Date());
        resource.setLastModifyUser(0L);
        mResourceRepository.save(resource);

        return "Successful";
    }

    @RequestMapping("/create")
    public String create() {
        User user = new User();
        user.setUserName("a");
        user.setPassword("b");
        user.setName("");
        user.setCreateUser(0L);
        user.setCreateTime(new Date());
        user.setLastModifyUser(0L);
        user = mUserRepository.save(PasswordHelper.encryptPassword(user));

        return "id: " + user.getId();
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query(@RequestParam(value = "name", required = true) String name) {
        User user = mUserRepository.findByUserName(name);
        if (user == null) {
            return "not found!";
        }

        return "id: " + user.getId();
    }
}
