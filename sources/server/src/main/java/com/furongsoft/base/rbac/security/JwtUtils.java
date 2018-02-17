package com.furongsoft.base.rbac.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWT工具类
 *
 * @author Alex
 */
public class JwtUtils {
    /**
     * 令牌过期时间
     */
    private static final long EXPIRE_TIME = 5 * 24 * 60 * 60 * 1000;

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUsername(String token) {
        try {
            return JWT.decode(token).getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 由用户名密码创建令牌
     *
     * @param username 用户名
     * @param password 密码
     * @return 令牌
     */
    public static String getToken(String username, String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(password);
            return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 验证令牌与用户名密码是否匹配
     *
     * @param token    令牌
     * @param username 用户名
     * @param password 密码
     * @return 是否匹配
     */
    public static boolean verify(String token, String username, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
