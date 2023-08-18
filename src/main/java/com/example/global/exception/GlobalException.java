package com.example.global.exception;

import com.example.global.domain.ResponseBodyEntity;
import com.example.global.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author LiTeng
 * @Date 2023/8/18 15:23
 * Version 1.0
 * @Description 全局异常处理类
 */

@Slf4j
@RestControllerAdvice
public class GlobalException {

    //服务器异常处理
    @ExceptionHandler(value = Exception.class)
    public ResponseBodyEntity<Object> defaultExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("系统错误！原因是：",e.getMessage());
        return new ResponseBodyEntity<Object>().fail(ErrorCode.ERR_SYSTEM.getCode(),
                ErrorCode.ERR_SYSTEM.getMessage());
    }
    //自定义异常处理
    @ExceptionHandler(value = CustomException.class)
    public ResponseBodyEntity<Object> businessExceptionHandler(HttpServletRequest req,
                                                       CustomException e) {
        log.error("业务异常！原因是：",e.getErrorCodeDes());
        return new ResponseBodyEntity<Object>().fail(e.getErrorCode(),
                e.getErrorCodeDes());
    }

    //空指针异常处理
    @ExceptionHandler(value =NullPointerException.class)
    public ResponseBodyEntity<Object> exceptionHandler(HttpServletRequest req,
                                                       NullPointerException e){
        log.error("发生空指针异常！原因是:",e.getMessage());
        return new ResponseBodyEntity<Object>().fail(ErrorCode.NULL_POINTER_ERROR.getCode(),
                ErrorCode.NULL_POINTER_ERROR.getMessage());
    }

    //....当然这里还可以定义很多异常，如果数字转换异常等等
}
