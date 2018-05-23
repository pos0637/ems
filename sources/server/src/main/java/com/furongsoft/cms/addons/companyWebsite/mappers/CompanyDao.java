package com.furongsoft.cms.addons.companyWebsite.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.cms.addons.companyWebsite.entities.Company;
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
 * 公司表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface CompanyDao extends BaseMapper<Company> {
    class DaoProvider {
        /**
         * 获取所有公司
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectCompanyList(final Map<String, Object> param) {
            String companyTableName = Company.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS logoPath, t3.name AS barcodePath, t4.name AS welcomePicturePath");
                FROM(companyTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.logo = t2.id");
                LEFT_OUTER_JOIN(attachmentTableName + " t3 ON t1.barcode = t3.id");
                LEFT_OUTER_JOIN(attachmentTableName + " t4 ON t1.welcome_picture = t4.id");
            }}.toString();
        }

        /**
         * 根据索引获取公司
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectCompanyById(final Map<String, Object> param) {
            String companyTableName = Company.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS logoPath, t3.name AS barcodePath, t4.name AS welcomePicturePath");
                FROM(companyTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.logo = t2.id");
                LEFT_OUTER_JOIN(attachmentTableName + " t3 ON t1.barcode = t3.id");
                LEFT_OUTER_JOIN(attachmentTableName + " t4 ON t1.welcome_picture = t4.id");
                if (param.get("id") != null) {
                    WHERE("t1.id = #{id}");
                }
            }}.toString();
        }
    }

    /**
     * 获取所有公司
     *
     * @return 公司列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectCompanyList")
    List<Company> selectCompanyList();

    /**
     * 根据索引获取公司
     *
     * @param id 索引
     * @return 公司
     */
    @SelectProvider(type = DaoProvider.class, method = "selectCompanyById")
    Company selectCompany(@Param("id") Serializable id);
}
