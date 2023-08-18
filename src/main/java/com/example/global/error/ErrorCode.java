package com.example.global.error;

/**
 * @Author LiTeng
 * @Date 2023/8/18 15:36
 * Version 1.0
 * @Description 异常类型
 */
public enum ErrorCode {

    ERR_SYSTEM("500", "系统错误"),
    CUSTOM_ERR("502", "自定义异常"),
    NULL_POINTER_ERROR("503","空指针异常");



    /**
     * 错误code
     */
    private String code;
    /**
     * 错误描述
     */
    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}