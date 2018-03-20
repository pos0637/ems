package com.furongsoft.base.rbac.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.base.rbac.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import javax.persistence.Table;
import java.util.Map;

/**
 * 用户表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface UserDao extends BaseMapper<User> {
    class DaoProvider {
        /**
         * 根据用户名称查找用户
         *
         * @param param 参数列表
         * @return SQL语句
         */
        public String findByUserName(final Map<String, Object> param) {
            String userTableName = User.class.getAnnotation(Table.class).name();

            return new SQL() {
                {
                    SELECT("*");
                    FROM(userTableName);
                    WHERE("username=#{username}");
                }
            }.toString();
        }
    }

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    @SelectProvider(type = DaoProvider.class, method = "findByUserName")
    User findByUserName(String username);
}
