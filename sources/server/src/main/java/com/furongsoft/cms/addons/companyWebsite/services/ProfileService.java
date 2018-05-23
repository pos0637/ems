package com.furongsoft.cms.addons.companyWebsite.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.StorageService;
import com.furongsoft.base.services.BaseService;
import com.furongsoft.cms.addons.companyWebsite.entities.Profile;
import com.furongsoft.cms.addons.companyWebsite.mappers.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 公司描述服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ProfileService extends BaseService<Profile> {
    private final ProfileDao profileDao;
    private final StorageService storageService;

    @Autowired
    public ProfileService(ProfileDao profileDao, StorageService storageService) {
        super(profileDao);
        this.profileDao = profileDao;
        this.storageService = storageService;
    }

    /**
     * 获取公司描述
     *
     * @param page      页面
     * @param name      公司描述名称
     * @param sortField 排序字段
     * @param sortType  排序类型
     * @return 公司描述
     */
    public Page<Profile> getProfiles(Page<Profile> page, String name, String sortField, String sortType) {
        return page.setRecords(profileDao.selectProfileListWithParams(page, name, sortField, sortType));
    }

    /**
     * 获取公司描述
     *
     * @return 公司描述
     */
    public List<Profile> getProfiles() {
        return profileDao.selectProfileList(null, null, null);
    }

    /**
     * 根据索引列表批量删除公司描述
     *
     * @param ids 索引列表
     */
    public void delProfiles(List<Serializable> ids) {
        profileDao.deleteBatchIds(ids);
    }

    @Override
    public Profile get(Serializable id) throws BaseException {
        return profileDao.selectProfile(id);
    }

    @Override
    public void add(Profile profile) throws BaseException {
        profile.setPicture((String) storageService.getFileId(profile.getPicturePath()));
        profileDao.insert(profile);
    }

    @Override
    public void edit(Profile profile) throws BaseException {
        profile.setPicture((String) storageService.getFileId(profile.getPicturePath()));
        profileDao.updateById(profile);
    }
}
