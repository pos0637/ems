package com.furongsoft.base.rbac.mappers;

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
public interface ResourceDao {
    @Select("SELECT * FROM t_sys_resource")
    List<Resource> find();
}
