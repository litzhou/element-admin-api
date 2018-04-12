package com.vacomall;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.vacomall.bean.Rest;
/**
 * 全局异常处理器
 * @author Administrator
 *
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
	
	public static final Logger logger = Logger.getLogger(ExceptionAdvice.class);
	
	  /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Rest handleValidationException(ValidationException e) {
        logger.error("参数验证失败,"+e.getMessage());
        return Rest.failure("参数验证失败,"+e.getMessage());
    }
	 /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Rest handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败,"+e.getMessage());
        return Rest.failure("参数解析失败,"+e.getMessage());
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Rest handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法,"+e.getMessage());
        return Rest.failure("不支持当前请求方法,"+e.getMessage());
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Rest handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型,"+e.getMessage());
        return Rest.failure("不支持当前媒体类型,"+e.getMessage());
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Rest handleNoHandlerFoundException(NoHandlerFoundException  e) {
        logger.error("资源不存在,"+e.getMessage());
        return Rest.failure("资源不存在,"+e.getMessage());
        
    }
    
    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public Rest handleNullPointerException(NullPointerException e) {
        logger.error("空指针异常,"+e.getMessage());
        return Rest.failure("空指针异常,"+e.getMessage());
    }
    
    /**
     * 500
     * @param e
     * @param model
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Rest handleException(Exception e) {
        logger.error("服务运行异常,"+e.getMessage());
        return Rest.failure("服务运行异常,"+e.getMessage());
    }
}
