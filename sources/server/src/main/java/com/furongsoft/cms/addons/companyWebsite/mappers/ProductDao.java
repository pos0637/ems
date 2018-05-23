package com.furongsoft.cms.addons.companyWebsite.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.base.misc.StringUtils;
import com.furongsoft.cms.addons.companyWebsite.entities.Product;
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
         * 根据索引获取产品序号
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectProductRank(final Map<String, Object> param) {
            String productTableName = Product.class.getAnnotation(Table.class).name();

            return String.format("SELECT \n" +
                            "    t2.rank\n" +
                            "FROM\n" +
                            "    (SELECT \n" +
                            "        @rownum := @rownum + 1 AS rank, t1.id\n" +
                            "    FROM\n" +
                            "        %s t1, (SELECT @rownum := 0) r\n" +
                            "    WHERE\n" +
                            "        t1.category_id = '%s') t2\n" +
                            "WHERE\n" +
                            "    t2.id = '%s'",
                    productTableName,
                    param.get("categoryId").toString(),
                    param.get("id").toString());
        }

        /**
         * 根据序号获取产品
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectProductByRank(final Map<String, Object> param) {
            String productTableName = Product.class.getAnnotation(Table.class).name();

            return String.format("SELECT \n" +
                            "    t2.*\n" +
                            "FROM\n" +
                            "    (SELECT \n" +
                            "        @rownum := @rownum + 1 AS rank, t1.*\n" +
                            "    FROM\n" +
                            "        %s t1, (SELECT @rownum := 0) r\n" +
                            "    WHERE\n" +
                            "        t1.category_id = '%s') t2\n" +
                            "WHERE\n" +
                            "    t2.rank = %d",
                    productTableName,
                    param.get("categoryId").toString(),
                    param.get("rank"));
        }

        /**
         * 获取推荐产品列表
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectRecommendProductList(final Map<String, Object> param) {
            String productTableName = Product.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS iconPath");
                FROM(productTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.icon = t2.id");
                WHERE("t1.recommend = 1");
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
     * @param page       分页对象
     * @param categoryId 分类索引
     * @param name       产品名称
     * @param sortField  排序字段
     * @param sortType   排序类型
     * @return 产品列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProductListWithParams")
    List<Product> selectProductListWithParams(Pagination page, @Param("categoryId") Serializable categoryId, @Param("name") String name, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 获取所有产品
     *
     * @param categoryId 分类索引
     * @param name       产品名称
     * @param sortField  排序字段
     * @param sortType   排序类型
     * @return 产品列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProductListWithParams")
    List<Product> selectProductList(@Param("categoryId") Serializable categoryId, @Param("name") String name, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 获取推荐产品列表
     *
     * @param page 分页对象
     * @return 产品列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectRecommendProductList")
    List<Product> selectRecommendProductList(Pagination page);

    /**
     * 根据索引获取产品
     *
     * @param id 索引
     * @return 产品
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProductById")
    Product selectProduct(@Param("id") Serializable id);

    /**
     * 根据索引获取产品序号
     *
     * @param categoryId 分类索引
     * @param id         产品索引
     * @return 产品序号
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProductRank")
    Long selectProductRank(@Param("categoryId") Serializable categoryId, @Param("id") Serializable id);

    /**
     * 根据序号获取产品
     *
     * @param categoryId 分类索引
     * @param rank       序号
     * @return 产品
     */
    @SelectProvider(type = DaoProvider.class, method = "selectProductByRank")
    Product selectProductByRank(@Param("categoryId") Serializable categoryId, @Param("rank") Long rank);

    /**
     * 删除指定产品分类的产品
     *
     * @param id 索引
     */
    @DeleteProvider(type = DaoProvider.class, method = "deleteProductByCategoryId")
    void deleteProductByCategoryId(@Param("id") Serializable id);
}
