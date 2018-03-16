package com.furongsoft.ems.controllers.api.v1;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.entities.PageRequest;
import com.furongsoft.base.entities.PageResponse;
import com.furongsoft.base.entities.TreeNode;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.rbac.entities.Permission;
import com.furongsoft.base.rbac.entities.Resource;
import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.mappers.UserDao;
import com.furongsoft.base.rbac.security.JwtUtils;
import com.furongsoft.base.rbac.services.PermissionService;
import com.furongsoft.base.rbac.services.ResourceService;
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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 系统控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/system")
public class SystemController {
    private final UserDao userDao;
    private final ResourceService resourceService;
    private final PermissionService permissionService;

    @Autowired
    public SystemController(UserDao userDao, ResourceService resourceService, PermissionService permissionService) {
        this.userDao = userDao;
        this.resourceService = resourceService;
        this.permissionService = permissionService;
    }

    @GetMapping("/resources")
    public PageResponse getResources(
            PageRequest pageRequest,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String path,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortType) {
        Page<Resource> page = resourceService.getResources(pageRequest.getPage(), name, path, sortField, sortType);
        return new PageResponse<>(HttpStatus.OK, page);
    }

    @GetMapping("/resources/{id}")
    public RestResponse getResource(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, resourceService.getResource(id));
    }

    @PostMapping("/resources")
    public RestResponse addResource(@NonNull @RequestParam Resource resource) {
        resourceService.addResource(resource);
        return new RestResponse(HttpStatus.OK);
    }

    @PutMapping("/resources/{id}")
    public RestResponse editResource(@NonNull @PathVariable String id, @NonNull @RequestParam Resource resource) {
        resource.setId(id);
        resourceService.editResource(resource);
        return new RestResponse(HttpStatus.OK);
    }

    @DeleteMapping("/resources/{id}")
    public RestResponse delResource(@NonNull @PathVariable String id) {
        resourceService.delResource(id);
        return new RestResponse(HttpStatus.OK);
    }

    @PostMapping("/resources/batch")
    public RestResponse delResources(@NonNull @RequestParam String delete) {
        try {
            delete = URLDecoder.decode(delete, "UTF-8");
            List<Serializable> ids = JSON.parseArray(delete, Serializable.class);
            resourceService.delResources(ids);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException.IllegalArgumentException();
        }

        return new RestResponse(HttpStatus.OK);
    }

    @GetMapping("/permissions")
    public RestResponse getPermissions() {
        TreeNode<Permission> root = permissionService.getPermissionsTree(permissionService.getPermissions());
        return new RestResponse(HttpStatus.OK, null, JSON.toJSONString(root.children));
    }

    @PostMapping("/login")
    public RestResponse login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));

        User user = userDao.findByUserName(username);
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
