package com.furongsoft.ems.services.cms;

import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.services.BaseService;
import com.furongsoft.ems.entities.cms.CompanyInfo;
import com.furongsoft.ems.mappers.cms.CompanyInfoDao;
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
public class CompanyInfoService extends BaseService<CompanyInfo> {
    private final CompanyInfoDao companyInfoDao;

    @Autowired
    public CompanyInfoService(CompanyInfoDao companyInfoDao) {
        super(companyInfoDao);
        this.companyInfoDao = companyInfoDao;
    }

    @Override
    public CompanyInfo get(Serializable id) throws BaseException {
        List<CompanyInfo> list = companyInfoDao.selectList(null);
        if ((list == null) || (list.size() == 0)) {
            return new CompanyInfo();
        } else {
            return list.get(0);
        }
    }

    @Override
    public void edit(CompanyInfo object) throws BaseException {
        Integer count = companyInfoDao.selectCount(null);
        if ((count == null) || (count == 0)) {
            super.add(object);
        } else {
            super.edit(object);
        }
    }
}
