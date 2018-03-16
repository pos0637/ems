package com.furongsoft.base.rbac.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.StorageService;
import com.furongsoft.base.rbac.entities.Resource;
import com.furongsoft.base.rbac.mappers.ResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 资源服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ResourceService {
    private final ResourceDao resourceDao;
    private final StorageService storageService;

    @Autowired
    public ResourceService(ResourceDao resourceDao, StorageService storageService) {
        this.resourceDao = resourceDao;
        this.storageService = storageService;
    }

    /**
     * 获取资源
     *
     * @param page      页面
     * @param name      资源名称
     * @param path      资源路径
     * @param sortField 排序字段
     * @param sortType  排序类型
     * @return 资源
     */
    public Page<Resource> getResources(Page<Resource> page, String name, String path, String sortField, String sortType) {
        return page.setRecords(resourceDao.selectResourceList(page, name, path, sortField, sortType));
    }

    /**
     * 根据索引列表批量删除资源
     *
     * @param ids 索引列表
     */
    public void delResources(List<Serializable> ids) {
        resourceDao.deleteBatchIds(ids);
    }

    /**
     * 获取资源
     *
     * @param id 资源索引
     * @throws BaseException 异常
     */
    public Resource getResource(Serializable id) throws BaseException {
        return resourceDao.selectResource(id);
    }

    /**
     * 新增资源
     *
     * @param resource 资源
     * @throws BaseException 异常
     */
    public void addResource(Resource resource) throws BaseException {
        Serializable id = storageService.getFileId(resource.getIconPath());
        if (id != null) {
            resource.setIcon((String) id);
        }

        resourceDao.insert(resource);
    }

    /**
     * 更新资源
     *
     * @param resource 资源
     * @throws BaseException 异常
     */
    public void editResource(Resource resource) throws BaseException {
        resource.setIcon((String) storageService.getFileId(resource.getIconPath()));
        resourceDao.updateById(resource);
    }

    /**
     * 删除资源
     *
     * @param id 资源索引
     * @throws BaseException 异常
     */
    public void delResource(Serializable id) throws BaseException {
        resourceDao.deleteById(id);
    }
}
