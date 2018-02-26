package com.furongsoft.ems.controllers.api.v1;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.entities.PageRequest;
import com.furongsoft.base.entities.PageResponse;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.rbac.entities.Resource;
import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.mappers.UserDao;
import com.furongsoft.base.rbac.security.JwtUtils;
import com.furongsoft.base.rbac.services.SystemService;
import com.furongsoft.base.restful.entities.RestResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
    public PageResponse getResources(
            PageRequest pageRequest,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String path) {
        Page<Resource> page = mSystemService.getResources(pageRequest.getPage(), name, path);
        return new PageResponse<>(HttpStatus.OK, page);
    }

    @GetMapping("/resources/{id}")
    public RestResponse getResource(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, mSystemService.getResource(id));
    }

    @PostMapping("/resources")
    public RestResponse addResource(@NonNull Resource resource) {
        mSystemService.addResource(resource);
        return new RestResponse(HttpStatus.OK);
    }

    @PutMapping("/resources/{id}")
    public RestResponse editResource(@NonNull @PathVariable String id, @NonNull Resource resource) {
        resource.setId(id);
        mSystemService.editResource(resource);
        return new RestResponse(HttpStatus.OK);
    }

    @DeleteMapping("/resources/{id}")
    public RestResponse delResource(@NonNull @PathVariable String id) {
        mSystemService.delResource(id);
        return new RestResponse(HttpStatus.OK);
    }

    @PostMapping("/resources/batch")
    public RestResponse delResources(@NonNull @RequestParam String delete) {
        try {
            delete = URLDecoder.decode(delete, "UTF-8");
            String[] ids = delete.split(",");
            mSystemService.delResources(ids);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException.IllegalArgumentException();
        }

        return new RestResponse(HttpStatus.OK);
    }

    @PostMapping("/login")
    public RestResponse login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));

        User user = mUserDao.findByUserName(username);
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
