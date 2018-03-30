package com.furongsoft.base.rbac.services;

import com.furongsoft.base.entities.TreeNode;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.StorageService;
import com.furongsoft.base.rbac.entities.Permission;
import com.furongsoft.base.rbac.entities.Resource;
import com.furongsoft.base.rbac.mappers.PermissionDao;
import com.furongsoft.base.services.BaseService;
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
public class PermissionService extends BaseService<Permission> {
    private final PermissionDao permissionDao;
    private final StorageService storageService;

    @Autowired
    public PermissionService(PermissionDao permissionDao, StorageService storageService) {
        super(permissionDao);
        this.permissionDao = permissionDao;
        this.storageService = storageService;
    }

    /**
     * 获取所有权限
     *
     * @return 权限列表
     */
    public List<Permission> getPermissions() {
        return permissionDao.selectPermissionList();
    }

    @Override
    public Permission get(Serializable id) throws BaseException {
        return permissionDao.selectPermission(id);
    }

    @Override
    public void add(Permission permission) throws BaseException {
        Serializable id = storageService.getFileId(permission.getIconPath());
        if (id != null) {
            permission.setIcon((String) id);
        }

        permissionDao.insert(permission);
    }

    @Override
    public void edit(Permission permission) throws BaseException {
        permission.setIcon((String) storageService.getFileId(permission.getIconPath()));
        permissionDao.updateById(permission);
    }

    @Override
    public void del(Serializable id) throws BaseException {
        permissionDao.deletePermission(id);
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
        HashMap<Serializable, TreeNode<Permission>> map = new HashMap<>(permissions.size());
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
