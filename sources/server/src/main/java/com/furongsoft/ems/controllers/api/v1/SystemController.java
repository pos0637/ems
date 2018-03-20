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
import java.util.stream.Collectors;

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

    /**
     * 获取全部资源
     *
     * @param pageRequest 页面
     * @param name        名称
     * @param path        路径
     * @param sortField   排序字段
     * @param sortType    排序方式
     * @return 全部资源
     */
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

    /**
     * 获取资源
     *
     * @param id 资源索引
     * @return 响应内容
     */
    @GetMapping("/resources/{id}")
    public RestResponse getResource(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, resourceService.get(id));
    }

    /**
     * 添加资源
     *
     * @param resource 资源
     * @return 响应内容
     */
    @PostMapping("/resources")
    public RestResponse addResource(@NonNull Resource resource) {
        resourceService.add(resource);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 更新资源
     *
     * @param id       资源索引
     * @param resource 资源
     * @return 响应内容
     */
    @PutMapping("/resources/{id}")
    public RestResponse editResource(@NonNull @PathVariable String id, @NonNull Resource resource) {
        resource.setId(id);
        resourceService.edit(resource);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 删除资源
     *
     * @param id 资源索引
     * @return 响应内容
     */
    @DeleteMapping("/resources/{id}")
    public RestResponse delResource(@NonNull @PathVariable String id) {
        resourceService.del(id);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 批量删除资源
     *
     * @param delete 资源索引列表
     * @return 响应内容
     */
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

    /**
     * 获取权限列表
     *
     * @return 权限列表
     */
    @GetMapping("/permissions")
    public RestResponse getPermissions() {
        TreeNode<Permission> root = permissionService.getPermissionsTree(permissionService.getPermissions());
        return new RestResponse(HttpStatus.OK, null, JSON.toJSONString(root.children));
    }

    /**
     * 获取权限
     *
     * @param id 权限索引
     * @return 响应内容
     */
    @GetMapping("/permissions/{id}")
    public RestResponse getPermission(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, permissionService.get(id));
    }

    /**
     * 添加权限
     *
     * @param permission 权限
     * @return 响应内容
     */
    @PostMapping("/permissions")
    public RestResponse addPermission(@NonNull Permission permission) {
        permissionService.add(permission);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 更新权限
     *
     * @param id         权限索引
     * @param permission 权限
     * @return 响应内容
     */
    @PutMapping("/permissions/{id}")
    public RestResponse editPermission(@NonNull @PathVariable String id, @NonNull Permission permission) {
        permission.setId(id);
        permissionService.edit(permission);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 删除权限
     *
     * @param id 权限索引
     * @return 响应内容
     */
    @DeleteMapping("/permissions/{id}")
    public RestResponse delPermission(@NonNull @PathVariable String id) {
        permissionService.del(id);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 获取菜单列表
     *
     * @return 菜单列表
     */
    @GetMapping("/menus")
    public RestResponse getMenus() {
        List<Permission> list = permissionService.getPermissions();
        list = list.stream().filter(permission -> (permission.getType() == 0)).collect(Collectors.toList());
        TreeNode<Permission> root = permissionService.getPermissionsTree(list);
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


    @RequiresAuthentication
    public RestResponse logout() {
        return new RestResponse(HttpStatus.OK, null, null);
    }
}
