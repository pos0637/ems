package com.furongsoft.base.restful.entities;

import org.springframework.http.HttpStatus;

/**
 * Restful响应内容
 *
 * @author Alex
 */
public class RestResponse {
    /**
     * HTTP状态码
     */
    private int code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 新令牌
     */
    private String newToken;

    public RestResponse(HttpStatus status, String message, Object data) {
        this(status.value(), message, data);
    }

    public RestResponse(HttpStatus status, String message, Object data, String newToken) {
        this(status.value(), message, data, newToken);
    }

    public RestResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RestResponse(int code, String message, Object data, String newToken) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.newToken = newToken;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getNewToken() {
        return newToken;
    }

    public void setNewToken(String newToken) {
        this.newToken = newToken;
    }
}
