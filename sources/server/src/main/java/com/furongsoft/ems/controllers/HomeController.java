package com.furongsoft.ems.controllers;

import com.alibaba.fastjson.JSON;
import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.mappers.UserDao;
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

    @Autowired
    public HomeController(UserRepository userRepository, UserDao userDao) {
        mUserRepository = userRepository;
        mUserDao = userDao;
    }

    @RequestMapping("/index")
    public String index() {
        List<User> list = mUserDao.find();

        return "Hello, world! " + JSON.toJSONString(list);
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
