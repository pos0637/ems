package com.furongsoft.ems.repositories;

import com.furongsoft.ems.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户仓库
 *
 * @author Alex
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    public User findByUserName(String userName);
}
