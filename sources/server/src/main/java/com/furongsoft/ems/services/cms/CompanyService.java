package com.furongsoft.ems.services.cms;

import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.StorageService;
import com.furongsoft.base.services.BaseService;
import com.furongsoft.ems.entities.cms.Company;
import com.furongsoft.ems.mappers.cms.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 公司信息服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CompanyService extends BaseService<Company> {
    private final CompanyDao companyDao;
    private final StorageService storageService;

    @Autowired
    public CompanyService(CompanyDao companyDao, StorageService storageService) {
        super(companyDao);
        this.companyDao = companyDao;
        this.storageService = storageService;
    }

    @Override
    public Company get(Serializable id) throws BaseException {
        List<Company> list = companyDao.selectCompanyList();
        if ((list == null) || (list.size() == 0)) {
            return new Company();
        } else {
            return list.get(0);
        }
    }

    @Override
    public void edit(Company object) throws BaseException {
        Serializable id = storageService.getFileId(object.getLogoPath());
        if (id != null) {
            object.setLogo((String) id);
        }

        id = storageService.getFileId(object.getBarcodePath());
        if (id != null) {
            object.setBarcode((String) id);
        }

        Integer count = companyDao.selectCount(null);
        if ((count == null) || (count == 0)) {
            super.add(object);
        } else {
            super.edit(object);
        }
    }
}
