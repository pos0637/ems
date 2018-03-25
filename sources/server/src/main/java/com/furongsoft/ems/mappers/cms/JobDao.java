package com.furongsoft.ems.mappers.cms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.furongsoft.base.misc.StringUtils;
import com.furongsoft.ems.entities.cms.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * 工作表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface JobDao extends BaseMapper<Job> {
    class DaoProvider {
        /**
         * 获取所有工作
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectJobListWithParams(final Map<String, Object> param) {
            String jobTableName = Job.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*");
                FROM(jobTableName + " t1");
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
         * 获取所有工作
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String selectJobList(final Map<String, Object> param) {
            String jobTableName = Job.class.getAnnotation(Table.class).name();

            return new SQL() {{
                SELECT("t1.*");
                FROM(jobTableName + " t1");
                ORDER_BY("t1.priority");
            }}.toString();
        }
    }

    /**
     * 获取所有工作
     *
     * @param page      分页对象
     * @param name      资源名称
     * @param sortField 排序字段
     * @param sortType  排序类型
     * @return 工作列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectJobListWithParams")
    List<Job> selectJobListWithParams(Pagination page, @Param("name") String name, @Param("sortField") String sortField, @Param("sortType") String sortType);

    /**
     * 获取所有工作
     *
     * @return 工作列表
     */
    @SelectProvider(type = DaoProvider.class, method = "selectJobList")
    List<Job> selectJobList();
}
