package com.example.global.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author LiTeng
 * @Date 2023/8/18 15:32
 * Version 1.0
 * @Description 统一响应体返回结果
 */
@Data
public class ResponseBodyEntity<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6974189491915284638L;

    public static final String SUCCESS = "0";

    public static final String FAIL = "-1";

    private String code;
    private String msg;
    private T data;

    public ResponseBodyEntity<T> success(T data) {
        this.code = ResponseBodyEntity.SUCCESS;
        this.data = data;
        return this;
    }

    public ResponseBodyEntity<T> fail(String code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public static boolean isNotNull(ResponseBodyEntity res) {
        if (res != null && SUCCESS.equals(res.getCode()) && res.getData() != null) {
            return true;
        }
        return false;
    }

    public T data(T defaultVal){
        if( SUCCESS.equals(code)&&data!=null ){
            return data;
        }
        return defaultVal;
    }

    public T data(){
        return data(null);
    }
}
