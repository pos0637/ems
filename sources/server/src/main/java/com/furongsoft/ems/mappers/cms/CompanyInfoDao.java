package com.furongsoft.ems.mappers.cms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.ems.entities.cms.CompanyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 公司信息表操作对象
 */
@Mapper
@Component
public interface CompanyInfoDao extends BaseMapper<CompanyInfo> {
}
