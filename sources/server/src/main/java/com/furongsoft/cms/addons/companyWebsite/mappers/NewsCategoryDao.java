package com.furongsoft.cms.addons.companyWebsite.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.cms.addons.companyWebsite.entities.NewsCategory;
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
 * 新闻分类表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface NewsCategoryDao extends BaseMapper<NewsCategory> {
    class DaoProvider {
        /**
         * 获取所有新闻分类
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectNewsCategoryList(final Map<String, Object> param) {
            String newsCategoryTableName = NewsCategory.class.getAnnotation(Table.class).name();
            return String.format("SELECT * FROM %s ORDER BY priority", newsCategoryTableName);
        }

        /**
         * 删除新闻分类
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String deleteNewsCategory(final Map<String, Object> param) {
            String newsCategoryTableName = NewsCategory.class.getAnnotation(Table.class).name();

            return new SQL() {{
                DELETE_FROM(newsCategoryTableName);
                WHERE("id = #{id}");
                OR();
                WHERE("parent_id = #{id}");
            }}.toString();
        }
    }

    /**
     * 获取所有新闻分类
     *
     * @return 新闻分类列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectNewsCategoryList")
    List<NewsCategory> selectNewsCategoryList();

    /**
     * 删除新闻分类
     *
     * @param id 新闻分类索引
     */
    @DeleteProvider(type = DaoProvider.class, method = "deleteNewsCategory")
    void deleteNewsCategory(@Param("id") Serializable id);
}
