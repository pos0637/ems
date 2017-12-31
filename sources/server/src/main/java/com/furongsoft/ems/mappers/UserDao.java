package com.furongsoft.ems.mappers;

import com.furongsoft.ems.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface UserDao {
    @Select("SELECT * FROM t_sys_user")
    List<User> find();
}
