package com.furongsoft.cms.addons.schoolWebsite.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.cms.addons.schoolWebsite.entities.School;
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
 * 学校表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface SchoolDao extends BaseMapper<School> {
    class DaoProvider {
        /**
         * 获取所有学校
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectSchoolList(final Map<String, Object> param) {
            String schoolTableName = School.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS logoPath, t3.name AS welcomePicturePath");
                FROM(schoolTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.logo = t2.id");
                LEFT_OUTER_JOIN(attachmentTableName + " t3 ON t1.welcome_picture = t3.id");
            }}.toString();
        }

        /**
         * 根据索引获取学校
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectSchoolById(final Map<String, Object> param) {
            String schoolTableName = School.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*, t2.name AS logoPath, t3.name AS welcomePicturePath");
                FROM(schoolTableName + " t1");
                LEFT_OUTER_JOIN(attachmentTableName + " t2 ON t1.logo = t2.id");
                LEFT_OUTER_JOIN(attachmentTableName + " t3 ON t1.welcome_picture = t4.id");
                if (param.get("id") != null) {
                    WHERE("t1.id = #{id}");
                }
            }}.toString();
        }
    }

    /**
     * 获取所有学校
     *
     * @return 学校列表
     */
    @SelectProvider(type = SchoolDao.DaoProvider.class, method = "selectSchoolList")
    List<School> selectSchoolList();

    /**
     * 根据索引获取学校
     *
     * @param id 索引
     * @return 学校
     */
    @SelectProvider(type = SchoolDao.DaoProvider.class, method = "selectSchoolById")
    School selectSchool(@Param("id") Serializable id);
}
