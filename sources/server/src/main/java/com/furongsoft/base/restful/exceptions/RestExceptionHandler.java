package com.furongsoft.base.restful.exceptions;

import com.furongsoft.base.misc.Tracker;
import com.furongsoft.base.restful.entities.RestResponse;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Restful异常拦截器
 *
 * @author Alex
 */
@RestControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public RestResponse handle401(ShiroException e) {
        Tracker.error(e);
        return new RestResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public RestResponse handle401() {
        Tracker.error("Unauthorized");
        return new RestResponse(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse globalException(HttpServletRequest request, Throwable ex) {
        Tracker.error(ex);
        return new RestResponse(getStatus(request).value(), ex.getMessage(), null);
    }

    /**
     * 获取HTTP状态码
     *
     * @param request HTTP请求
     * @return HTTP状态码
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return (statusCode == null) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(statusCode);
    }
}
