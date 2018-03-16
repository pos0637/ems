package com.furongsoft.base.rbac.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.base.rbac.entities.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface PermissionDao extends BaseMapper<Permission> {
    @Select("SELECT * FROM t_sys_permission ORDER BY priority")
    List<Permission> selectPermissionList();
}
