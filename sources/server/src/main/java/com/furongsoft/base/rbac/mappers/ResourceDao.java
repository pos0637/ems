package com.furongsoft.base.rbac.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.furongsoft.base.rbac.entities.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 资源表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface ResourceDao extends BaseMapper<Resource> {
    @Select("SELECT * FROM t_sys_resource")
    List<Resource> find(Pagination page);
}
