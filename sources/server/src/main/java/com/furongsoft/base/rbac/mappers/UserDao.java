package com.furongsoft.base.rbac.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.base.rbac.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 用户表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface UserDao extends BaseMapper<User> {
    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Select("SELECT * FROM t_sys_user WHERE username=#{username}")
    User findByUserName(String username);
}
