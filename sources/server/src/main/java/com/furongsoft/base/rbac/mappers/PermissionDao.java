package com.furongsoft.base.rbac.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.base.rbac.entities.Permission;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 权限表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface PermissionDao extends BaseMapper<Permission> {
    class DaoProvider {
        /**
         * 获取所有权限
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectPermissionList(final Map<String, Object> param) {
            String permissionTableName = Permission.class.getAnnotation(Table.class).name();
            return String.format("SELECT * FROM %s ORDER BY priority", permissionTableName);
        }

        /**
         * 删除权限
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String deletePermission(final Map<String, Object> param) {
            String permissionTableName = Permission.class.getAnnotation(Table.class).name();

            return new SQL() {{
                DELETE_FROM(permissionTableName);
                WHERE("id = #{id}");
                OR();
                WHERE("parent_id = #{id}");
            }}.toString();
        }
    }

    /**
     * 获取所有权限
     *
     * @return 权限列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectPermissionList")
    List<Permission> selectPermissionList();

    /**
     * 删除权限
     *
     * @param id 索引
     */
    @DeleteProvider(type = DaoProvider.class, method = "deletePermission")
    void deletePermission(@Param("id") Serializable id);
}
