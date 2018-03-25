package com.furongsoft.ems.mappers.cms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.base.misc.StringUtils;
import com.furongsoft.ems.entities.cms.Product;
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
 * 产品表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface ProductDao extends BaseMapper<Product> {
    class DaoProvider {
        /**
         * 获取所有产品
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectProductListWithParams(final Map<String, Object> param) {
            String productTableName = Product.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS iconPath");
                FROM(productTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.icon = t2.id");
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
         * 根据索引获取产品
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectProductById(final Map<String, Object> param) {
            String productTableName = Product.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS iconPath");
                FROM(productTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.icon = t2.id");
                if (param.get("id") != null) {
                    WHERE("t1.id = #{id}");
                }
            }}.toString();
        }

        /**
         * 删除指定产品分类的产品
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String deleteProductByCategoryId(final Map<String, Object> param) {
            String productTableName = Product.class.getAnnotation(Table.class).name();

            return new SQL() {{
                DELETE_FROM(productTableName);
                WHERE("category_id = #{id}");
            }}.toString();
        }
    }

    /**
     * 获取所有产品
     *
     * @param page      分页对象
     * @param name      资源名称
     * @param sortField 排序字段
     * @param sortType  排序类型
     * @return 产品列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProductListWithParams")
    List<Product> selectProductList(Pagination page, @Param("name") String name, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 根据索引获取产品
     *
     * @param id 索引
     * @return 产品
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProductById")
    Product selectProduct(@Param("id") Serializable id);

    /**
     * 删除指定产品分类的产品
     *
     * @param id 索引
     */
    @DeleteProvider(type = DaoProvider.class, method = "deleteProductByCategoryId")
    void deleteProductByCategoryId(@Param("id") Serializable id);
}
