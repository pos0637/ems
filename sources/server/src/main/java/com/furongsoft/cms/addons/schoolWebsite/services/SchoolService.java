package com.furongsoft.cms.addons.schoolWebsite.services;

import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.StorageService;
import com.furongsoft.base.services.BaseService;
import com.furongsoft.cms.addons.schoolWebsite.entities.School;
import com.furongsoft.cms.addons.schoolWebsite.mappers.SchoolDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 学校信息服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class SchoolService extends BaseService<School> {
    private final SchoolDao schoolDao;
    private final StorageService storageService;

    public SchoolService(SchoolDao schoolDao, StorageService storageService) {
        super(schoolDao);
        this.schoolDao = schoolDao;
        this.storageService = storageService;
    }

    @Override
    public School get(Serializable id) throws BaseException {
        List<School> list = schoolDao.selectSchoolList();
        if ((list == null) || (list.size() == 0)) {
            return new School();
        } else {
            return list.get(0);
        }
    }

    @Override
    public void edit(School object) throws BaseException {
        Serializable id = storageService.getFileId(object.getLogoPath());
        if (id != null) {
            object.setLogo((String) id);
        }

        id = storageService.getFileId(object.getWelcomePicturePath());
        if (id != null) {
            object.setWelcomePicture((String) id);
        }

        Integer count = schoolDao.selectCount(null);
        if ((count == null) || (count == 0)) {
            super.add(object);
        } else {
            super.edit(object);
        }
    }
}
