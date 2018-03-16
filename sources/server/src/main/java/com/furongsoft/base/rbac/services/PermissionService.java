package com.furongsoft.base.rbac.services;

import com.furongsoft.base.entities.TreeNode;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.rbac.entities.Permission;
import com.furongsoft.base.rbac.mappers.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 权限服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class PermissionService {
    private final PermissionDao permissionDao;

    @Autowired
    public PermissionService(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    /**
     * 获取所有权限
     *
     * @return 权限列表
     */
    public List<Permission> getPermissions() {
        return permissionDao.selectPermissionList();
    }

    /**
     * 获取权限
     *
     * @param id 权限索引
     * @throws BaseException 异常
     */
    public Permission getPermission(Serializable id) throws BaseException {
        return permissionDao.selectById(id);
    }

    /**
     * 新增权限
     *
     * @param permission 权限
     * @throws BaseException 异常
     */
    public void addPermission(Permission permission) throws BaseException {
        permissionDao.insert(permission);
    }

    /**
     * 更新权限
     *
     * @param permission 权限
     * @throws BaseException 异常
     */
    public void editPermission(Permission permission) throws BaseException {
        permissionDao.updateById(permission);
    }

    /**
     * 删除权限
     *
     * @param id 权限索引
     * @throws BaseException 异常
     */
    public void delPermission(Serializable id) throws BaseException {
        permissionDao.deleteById(id);
    }

    /**
     * 获取权限树形列表
     *
     * @param permissions 权限列表
     * @return 权限树形列表
     */
    public TreeNode<Permission> getPermissionsTree(final List<Permission> permissions) {
        if (permissions == null) {
            return new TreeNode<>();
        }

        TreeNode<Permission> root = new TreeNode<>();
        HashMap<Serializable, TreeNode<Permission>> map = new HashMap<>();
        for (Permission permission : permissions) {
            map.put(permission.getId(), new TreeNode<>(permission));
        }

        for (Permission permission : permissions) {
            TreeNode<Permission> parent = map.get(permission.getParentId());
            TreeNode<Permission> node = map.get(permission.getId());
            if (parent != null) {
                parent.children.add(node);
            } else {
                root.children.add(node);
            }
        }

        return root;
    }
}