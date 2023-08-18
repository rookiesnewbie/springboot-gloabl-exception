package com.example.global.aop;

import com.example.global.annotation.NoResponseBodyEntity;
import com.example.global.domain.ResponseBodyEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author LiTeng
 * @Date 2023/8/18 16:26
 * Version 1.0
 * @Description aop
 */
@RestControllerAdvice(basePackages = "com.example.global.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //过滤掉已经加上ResponseBodyEntity返回值的方法
        return !(returnType.getParameterType().equals(ResponseBodyEntity.class)
                || returnType.hasMethodAnnotation(NoResponseBodyEntity.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //String类型需要特殊处理
        if (returnType.getParameterType().equals(String.class)){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new ResponseBodyEntity<>().success(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return new ResponseBodyEntity<>().success(body);
    }
}
