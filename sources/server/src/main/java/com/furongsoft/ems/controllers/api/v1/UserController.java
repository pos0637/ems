package com.furongsoft.ems.controllers.api.v1;

import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.repositories.UserRepository;
import com.furongsoft.base.rbac.security.JwtUtils;
import com.furongsoft.base.restful.entities.RestResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserRepository mUserRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        mUserRepository = userRepository;
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

    @PostMapping("/login")
    public RestResponse login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));

        User user = mUserRepository.findByUserName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }

        return new RestResponse(HttpStatus.OK, null, null, JwtUtils.getToken(username, user.getPassword()));
    }
}
