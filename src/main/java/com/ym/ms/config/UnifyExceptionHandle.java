package com.ym.ms.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import com.ym.ms.dto.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSONObject;
import com.ym.ms.dto.common.Result;
import com.ym.ms.exception.ServiceException;
import com.ym.ms.exception.ValidatParameterException;

/**
 * 统一异常处理
 *
 * @author sys
 * @date 9.5
 */
@ControllerAdvice
@ResponseBody
public class UnifyExceptionHandle {

    private static Logger logger = LoggerFactory.getLogger(UnifyExceptionHandle.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest req) {
        logger.error("缺少请求参数", e);
        logger.error("异常：请求参数-" + getReqParams(req).toJSONString());
        return Result.err(ResultCode.C9999999, e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServletRequestBindingException.class)
    public Result<String> handleServletRequestBindingException(ServletRequestBindingException e, HttpServletRequest req) {
        logger.error("缺少请求参数", e);
        logger.error("异常：请求参数" + getReqParams(req).toJSONString());
        return Result.err(ResultCode.C9999999, e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return Result.err(ResultCode.C9999999, e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleArgsException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return Result.err(ResultCode.C9999999, message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException e) {
        logger.error("参数绑定失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return Result.err(ResultCode.C9999999, message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleServiceException(ConstraintViolationException e) {
        logger.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return Result.err(ResultCode.C9999999, message);
    }


    /**
     * 400 - 参数验证-Validat验证框架
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidatParameterException.class)
    public Result<String> handleValidatParameterException(ValidatParameterException e) {
        logger.error("参数验证失败", e);
        return Result.err(ResultCode.C0000004, e.getMessage());
    }

    /**
     * 400 - Bad Request
     * 验证框架
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Result<String> handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        return Result.err(ResultCode.C9999999, e.getMessage());
    }

    /**
     * 415 - Unsupported Media Type
     */
/*    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return Result.err("content_type_not_supported");
    }
*/

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public Result<String> handleServiceException(ServiceException e) {
        logger.error("业务逻辑异常", e);
        return Result.err(ResultCode.C9999999, e.getMessage());
    }

    /**
     * 操作数据库出现异常:名称重复，外键关联
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.error("操作数据库出现异常:", e);
        return Result.err(ResultCode.C9999999, "操作数据库出现异常：字段重复、有外键关联等");
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e, HttpServletRequest req) {
        logger.error("异常", e);
       /* String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
        		req.getRequestURI(),
        		e.getClass().getName(),
                handlerMethod.getBean().getClass().getName(),
                handlerMethod.getMethod().getName(),
                e.getMessage());
        System.out.println(message);*/
        return Result.err(ResultCode.C9999999, e.getMessage());
    }

    private JSONObject getReqParams(HttpServletRequest request) {
        checkNotNull(request, "request");
        JSONObject reb = new JSONObject();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            reb.put(paraName, request.getParameter(paraName));
        }
        return reb;
    }

}