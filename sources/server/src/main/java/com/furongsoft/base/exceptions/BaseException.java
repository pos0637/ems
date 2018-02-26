package com.furongsoft.base.exceptions;

/**
 * 基础异常
 *
 * @author Alex
 */
public class BaseException extends RuntimeException {
    /**
     * 文件上传失败异常
     */
    public static class UploadFileFailException extends BaseException {
    }

    /**
     * 错误的提交参数异常
     */
    public static class IllegalArgumentException extends BaseException {
    }

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
