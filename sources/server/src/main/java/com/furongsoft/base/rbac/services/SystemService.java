package com.furongsoft.base.rbac.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.rbac.entities.Resource;
import com.furongsoft.base.rbac.mappers.ResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 系统服务
 *
 * @author Alex
 */
@Service
@Transactional
public class SystemService {
    private final ResourceDao mResourceDao;

    @Autowired
    public SystemService(ResourceDao resourceDao) {
        this.mResourceDao = resourceDao;
    }

    /**
     * 获取资源
     *
     * @param page 页面
     * @return 资源
     */
    public Page<Resource> getResources(Page<Resource> page) {
        return page.setRecords(this.mResourceDao.find(page));
    }

    /**
     * 新增资源
     *
     * @param resource 资源
     * @throws BaseException 异常
     */
    public void addResources(Resource resource) throws BaseException {
        this.mResourceDao.insert(resource);
    }
}
