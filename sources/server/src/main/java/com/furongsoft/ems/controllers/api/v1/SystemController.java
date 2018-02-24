package com.furongsoft.ems.controllers.api.v1;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.entities.PageRequest;
import com.furongsoft.base.entities.PageResponse;
import com.furongsoft.base.rbac.entities.Resource;
import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.mappers.UserDao;
import com.furongsoft.base.rbac.security.JwtUtils;
import com.furongsoft.base.restful.entities.RestResponse;
import com.furongsoft.base.rbac.services.SystemService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 系统控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/system")
public class SystemController {
    private final UserDao mUserDao;
    private final SystemService mSystemService;

    @Autowired
    public SystemController(UserDao userDao, SystemService systemService) {
        this.mUserDao = userDao;
        this.mSystemService = systemService;
    }

    @GetMapping("/resources")
    public PageResponse getResources(PageRequest pageRequest) {
        Page<Resource> page = mSystemService.getResources(pageRequest.getPage());
        return new PageResponse<>(HttpStatus.OK, page);
    }

    @PostMapping("/resources")
    public RestResponse addResource(Resource resource) {
        return new RestResponse(HttpStatus.OK);
    }

    @PostMapping("/login")
    public RestResponse login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));

        User user = this.mUserDao.findByUserName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }

        return new RestResponse(HttpStatus.OK, null, null, JwtUtils.getToken(username, user.getPassword()));
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public RestResponse logout() {
        return new RestResponse(HttpStatus.OK, null, null);
    }
}
