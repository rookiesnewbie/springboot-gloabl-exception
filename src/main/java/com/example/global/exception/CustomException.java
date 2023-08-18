package com.example.global.exception;

import lombok.Data;

/**
 * @Author LiTeng
 * @Date 2023/8/18 15:38
 * Version 1.0
 * @Description 自定义异常
 */
@Data
public class CustomException extends RuntimeException{

    //错误代码
    private String errorCode;

    //描述错误的信息
    private String errorCodeDes;

    public CustomException(String message){
        super(message);
    }

    public CustomException(String errorCode, String errorCodeDes){
        super(errorCode);
        this.errorCode = errorCode;
        this.errorCodeDes = errorCodeDes;
    }
}
