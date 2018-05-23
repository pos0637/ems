package com.furongsoft.cms.controllers.api.v1;

import com.furongsoft.base.rbac.mappers.UserDao;
import com.furongsoft.base.restful.entities.RestResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public RestResponse users() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new RestResponse(HttpStatus.OK, "You are already logged in", null);
        } else {
            return new RestResponse(HttpStatus.OK, "You are guest", null);
        }
    }
}
