package com.example.global.controller;

import com.example.global.domain.ResponseBodyEntity;
import com.example.global.error.ErrorCode;
import com.example.global.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LiTeng
 * @Date 2023/8/18 15:58
 * Version 1.0
 * @Description 异常请求测试接口
 */
@Slf4j
@RestController
public class TestExceptionController {

    /**
     * 数学计算异常，全局异常捕获
     */
    @GetMapping("/exceptionTest")
    public ResponseBodyEntity<String> exceptionTest(){
        System.out.println(1/0);
        return new ResponseBodyEntity<String>().success("test");
    }

    /**
     * 自定义异常
     */
    @GetMapping("/exceptionTest2")
    public Boolean exceptionTest2(){
        throw new CustomException(ErrorCode.CUSTOM_ERR.getCode(),
                ErrorCode.CUSTOM_ERR.getMessage());
    }

    /**
     *空指针异常
     */
    @GetMapping("/exceptionTest3")
    public Boolean exceptionTest3(){
        //模拟空指针异常
        String str = null;
        str.length();
        return true;
    }


}
