package com.furongsoft.ems.mappers.cms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.base.misc.StringUtils;
import com.furongsoft.ems.entities.cms.News;
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
 * 新闻表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface NewsDao extends BaseMapper<News> {
    class DaoProvider {
        /**
         * 获取所有新闻
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectNewsListWithParams(final Map<String, Object> param) {
            String newsTableName = News.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS iconPath");
                FROM(newsTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.icon = t2.id");
                if (!StringUtils.isNullOrEmpty(param.get("categoryId"))) {
                    WHERE("t1.category_id = #{categoryId}");
                }
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
         * 根据索引获取新闻
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectNewsById(final Map<String, Object> param) {
            String newsTableName = News.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS iconPath");
                FROM(newsTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.icon = t2.id");
                if (param.get("id") != null) {
                    WHERE("t1.id = #{id}");
                }
            }}.toString();
        }

        /**
         * 删除指定新闻分类的新闻
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String deleteNewsByCategoryId(final Map<String, Object> param) {
            String newsTableName = News.class.getAnnotation(Table.class).name();

            return new SQL() {{
                DELETE_FROM(newsTableName);
                WHERE("category_id = #{id}");
            }}.toString();
        }
    }

    /**
     * 获取所有新闻
     *
     * @param page       分页对象
     * @param categoryId 分类索引
     * @param name       资源名称
     * @param sortField  排序字段
     * @param sortType   排序类型
     * @return 新闻列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectNewsListWithParams")
    List<News> selectNewsListWithParams(Pagination page, @Param("categoryId") String categoryId, @Param("name") String name, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 获取所有新闻
     *
     * @param categoryId 分类索引
     * @param name       资源名称
     * @param sortField  排序字段
     * @param sortType   排序类型
     * @return 新闻列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectNewsListWithParams")
    List<News> selectNewsList(@Param("categoryId") String categoryId, @Param("name") String name, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 根据索引获取新闻
     *
     * @param id 索引
     * @return 新闻
     */
    @SelectProvider(type = DaoProvider.class, method = "selectNewsById")
    News selectNews(@Param("id") Serializable id);

    /**
     * 删除指定新闻分类的新闻
     *
     * @param id 索引
     */
    @DeleteProvider(type = DaoProvider.class, method = "deleteNewsByCategoryId")
    void deleteNewsByCategoryId(@Param("id") Serializable id);
}
