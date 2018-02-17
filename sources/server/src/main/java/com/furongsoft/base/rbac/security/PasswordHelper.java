package com.furongsoft.base.rbac.security;

import com.furongsoft.base.rbac.entities.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码工具类
 *
 * @author Alex
 */
public class PasswordHelper {
    /**
     * 加密算法
     */
    public static final String ALGORITHM_NAME = "md5";

    /**
     * 散列次数
     */
    public static final int HASH_ITERATIONS = 2;

    /**
     * 加密用户对象中的密码并生成盐值
     *
     * @param user 用户
     */
    public static User encryptPassword(User user) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        user.setSalt(salt);

        String password = new SimpleHash(ALGORITHM_NAME, user.getPassword(), ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
        user.setPassword(password);

        return user;
    }
}
