package com.furongsoft.base.rbac.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.base.misc.StringUtils;
import com.furongsoft.base.rbac.entities.Resource;
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
 * 资源表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface ResourceDao extends BaseMapper<Resource> {
    class DaoProvider {
        /**
         * 获取所有资源
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectResourceListWithParams(final Map<String, Object> param) {
            String resourceTableName = Resource.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS iconPath");
                FROM(resourceTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.icon = t2.id");
                if (!StringUtils.isNullOrEmpty(param.get("name"))) {
                    WHERE("t1.name LIKE CONCAT('%', #{name},'%')");
                }
                if (!StringUtils.isNullOrEmpty(param.get("path"))) {
                    WHERE("t1.path LIKE CONCAT('%', #{path},'%')");
                }
                if (!StringUtils.isNullOrEmpty(param.get("sortField"))
                        && !StringUtils.isNullOrEmpty(param.get("sortType"))) {
                    ORDER_BY("t1." + param.get("sortField") + " " + param.get("sortType"));
                } else {
                    ORDER_BY("t1.last_modify_time DESC");
                }
            }}.toString();
        }

        /**
         * 根据索引获取资源
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectResourceById(final Map<String, Object> param) {
            String resourceTableName = Resource.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS iconPath");
                FROM(resourceTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.icon = t2.id");
                if (param.get("id") != null) {
                    WHERE("t1.id = #{id}");
                }
            }}.toString();
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
    @SelectProvider(type = DaoProvider.class, method = "selectResourceListWithParams")
    List<Resource> selectResourceList(Pagination page, @Param("name") String name, @Param("path") String path, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 根据索引获取资源
     *
     * @param id 索引
     * @return 资源
     */
    @SelectProvider(type = DaoProvider.class, method = "selectResourceById")
    Resource selectResource(@Param("id") Serializable id);
}
