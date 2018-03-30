package com.furongsoft.ems.mappers.cms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.ems.entities.cms.ProductCategory;
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
 * 产品分类表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface ProductCategoryDao extends BaseMapper<ProductCategory> {
    class DaoProvider {
        /**
         * 获取所有产品分类
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectProductCategoryList(final Map<String, Object> param) {
            String productCategoryTableName = ProductCategory.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS iconPath");
                FROM(productCategoryTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.icon = t2.id");
                ORDER_BY("t1.priority");
            }}.toString();
        }

        /**
         * 根据索引获取产品分类
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectProductCategoryById(final Map<String, Object> param) {
            String productCategoryTableName = ProductCategory.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS iconPath");
                FROM(productCategoryTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.icon = t2.id");
                if (param.get("id") != null) {
                    WHERE("t1.id = #{id}");
                }
            }}.toString();
        }

        /**
         * 删除产品分类
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String deleteProductCategory(final Map<String, Object> param) {
            String productCategoryTableName = ProductCategory.class.getAnnotation(Table.class).name();

            return new SQL() {{
                DELETE_FROM(productCategoryTableName);
                WHERE("id = #{id}");
                OR();
                WHERE("parent_id = #{id}");
            }}.toString();
        }
    }

    /**
     * 获取所有产品分类
     *
     * @return 产品分类列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProductCategoryList")
    List<ProductCategory> selectProductCategoryList();

    /**
     * 根据索引获取产品分类
     *
     * @param id 索引
     * @return 产品分类
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProductCategoryById")
    ProductCategory selectProductCategory(@Param("id") Serializable id);

    /**
     * 删除产品分类
     *
     * @param id 产品分类索引
     */
    @DeleteProvider(type = DaoProvider.class, method = "deleteProductCategory")
    void deleteProductCategory(@Param("id") Serializable id);
}
