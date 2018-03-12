package com.furongsoft.base.rbac.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.furongsoft.base.misc.StringUtils;
import com.furongsoft.base.rbac.entities.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.io.Serializable;
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
                    SELECT("t1.*, t2.name AS iconPath");
                    FROM("t_sys_resource T1");
                    LEFT_OUTER_JOIN("t_sys_attachment t2 ON t1.icon = t2.id");
                    if (param.get("name") != null) {
                        WHERE("T1.name LIKE CONCAT('%', #{name},'%')");
                    }
                    if (param.get("path") != null) {
                        WHERE("T1.path LIKE CONCAT('%', #{path},'%')");
                    }
                    if (!StringUtils.isNullOrEmpty(param.get("sortField"))
                            && !StringUtils.isNullOrEmpty(param.get("sortType"))) {
                        ORDER_BY("T1." + param.get("sortField") + " " + param.get("sortType"));
                    } else {
                        ORDER_BY("T1.last_modify_time DESC");
                    }
                }
            }.toString();
        }

        /**
         * 根据索引获取资源
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectResourceById(final Map<String, Object> param) {
            return new SQL() {
                {
                    SELECT("t1.*, t2.name AS iconPath");
                    FROM("t_sys_resource T1");
                    LEFT_OUTER_JOIN("t_sys_attachment t2 ON t1.icon = t2.id");
                    if (param.get("id") != null) {
                        WHERE("T1.id = #{id}");
                    }
                }
            }.toString();
        }
    }

    /**
     * 获取所有资源
     *
     * @param page      分页对象
     * @param name      资源名称
     * @param path      资源路径
     * @param sortField 排序字段
     * @param sortType  排序类型
     * @return 资源列表
     */
    @SelectProvider(type = ResourceDaoProvider.class, method = "selectResourceListWhitParam")
    List<Resource> selectResourceList(Pagination page, @Param("name") String name, @Param("path") String path, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 根据索引获取资源
     *
     * @param id 索引
     * @return 资源
     */
    @SelectProvider(type = ResourceDaoProvider.class, method = "selectResourceById")
    Resource selectResource(@Param("id") Serializable id);
}
