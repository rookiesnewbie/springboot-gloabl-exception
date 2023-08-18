package com.example.global.controller;

import com.example.global.annotation.NoResponseBodyEntity;
import com.example.global.domain.ResponseBodyEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LiTeng
 * @Date 2023/8/18 15:44
 * Version 1.0
 * @Description 正常请求测试接口
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseBodyEntity<String> test(){
        log.info("test方法开始");
        return new ResponseBodyEntity<String>().success("test");
    }

    @GetMapping("/test2")
    public Boolean test2(){
        log.info("test2方法开始");
        return true;
    }

    @GetMapping("/test3")
    @NoResponseBodyEntity
    public String test3(){
        log.info("test3方法开始");
        return "test3";
    }

}