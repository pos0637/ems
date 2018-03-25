package com.furongsoft.ems.mappers.cms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.base.misc.StringUtils;
import com.furongsoft.ems.entities.cms.Profile;
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
 * 公司描述表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface ProfileDao extends BaseMapper<Profile> {
    class DaoProvider {
        /**
         * 获取所有公司描述
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectProfileList(final Map<String, Object> param) {
            String profileTableName = Profile.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS picturePath");
                FROM(profileTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.picture = t2.id");
                if (!StringUtils.isNullOrEmpty(param.get("name"))) {
                    WHERE("t1.name LIKE CONCAT('%', #{name},'%')");
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
         * 根据索引获取公司描述
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectProfileById(final Map<String, Object> param) {
            String profileTableName = Profile.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS picturePath");
                FROM(profileTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.picture = t2.id");
                if (param.get("id") != null) {
                    WHERE("t1.id = #{id}");
                }
            }}.toString();
        }
    }

    /**
     * 获取所有公司描述
     *
     * @param page      分页对象
     * @param name      公司描述名称
     * @param sortField 排序字段
     * @param sortType  排序类型
     * @return 公司描述列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProfileList")
    List<Profile> selectProfileListWithParams(Pagination page, @Param("name") String name, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 获取所有公司描述
     *
     * @param name      公司描述名称
     * @param sortField 排序字段
     * @param sortType  排序类型
     * @return 公司描述列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProfileList")
    List<Profile> selectProfileList(@Param("name") String name, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 根据索引获取公司描述
     *
     * @param id 索引
     * @return 公司描述
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProfileById")
    Profile selectProfile(@Param("id") Serializable id);
}
