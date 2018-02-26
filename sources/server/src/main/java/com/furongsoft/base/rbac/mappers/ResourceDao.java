package com.furongsoft.base.rbac.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.furongsoft.base.rbac.entities.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 资源表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface ResourceDao extends BaseMapper<Resource> {
    class ResourceDaoProvider {
        /**
         * 获取所有资源
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectResourceListWhitParam(final Map<String, Object> param) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("t_sys_resource");
                    if (param.get("name") != null) {
                        WHERE("name LIKE CONCAT('%', #{name},'%')");
                    }
                    if (param.get("path") != null) {
                        WHERE("path LIKE CONCAT('%', #{path},'%')");
                    }
                    ORDER_BY("last_modify_time DESC");
                }
            }.toString();
        }
    }

    /**
     * 获取所有资源
     *
     * @param page 分页对象
     * @param name 资源名称
     * @param path 资源路径
     * @return 资源列表
     */
    @SelectProvider(type = ResourceDaoProvider.class, method = "selectResourceListWhitParam")
    List<Resource> selectResourceList(Pagination page, @Param("name") String name, @Param("path") String path);
}
