package com.ym.ms.exception;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录 @see WebMvcConfigurer
 *
 * @author sys
 * @date 9.5
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -4289360519664366313L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}