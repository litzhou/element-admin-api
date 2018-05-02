package com.vacomall;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
     * 权限异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UnauthorizedException.class)
    public Rest handleUnauthorizedException(UnauthorizedException e) {
        logger.error("无访问权限,"+e.getMessage());
        return Rest.failure("无访问权限,"+e.getMessage());
    }
    
    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Rest handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    	BindingResult bind =  e.getBindingResult();
    	logger.error("参数校验失败,"+e.getMessage());
    	if(bind.hasErrors()) {
    		List<String> errors = bind.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
    		return Rest.failure(500, "参数校验失败", errors, e.getMessage());
    	}
    	return Rest.failure("参数校验失败,"+e.getMessage());
    }
    
    /**
     * 默认异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Rest handleException(Exception e) {
    	logger.error("服务运行异常,"+e.getMessage());
    	return Rest.failure("服务运行异常,"+e.getMessage());
    }
}
