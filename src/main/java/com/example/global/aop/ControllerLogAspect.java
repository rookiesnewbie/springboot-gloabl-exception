package com.example.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * @Author LiTeng
 * @Date 2023/8/18 16:33
 * Version 1.0
 * @Description 日志切面
 */
@Aspect
@Slf4j
@Component
public class ControllerLogAspect {
    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    @Pointcut("execution(* com.example.global.controller..*.*(..))")
    public void controllers() {
    }

    @Before("controllers()")
    public void deBefore(final JoinPoint joinPoint) throws UnknownHostException {
        // 接收到请求，记录请求内容
        log.info("===========================================================");
        log.info("================  Controller Log Start  ===================");
        log.info("===========================================================");
        this.startTime = LocalDateTime.now();
        final ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            final HttpServletRequest request = attributes.getRequest();
            InetAddress localhost = InetAddress.getLocalHost();
            String localIpAddress = localhost.getHostAddress();
            log.info("==> Request: [{}]{}", request.getMethod(), request.getRequestURL());
            log.info("==> From IP: {}",localIpAddress);
        }
        log.info(
                "==>  Method: {}",
                joinPoint.getSignature().getDeclaringTypeName() + "#" + joinPoint.getSignature().getName());
        log.info("==>    Args: {}", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 后置结果返回
     *
     * @param result 结果
     */
    @AfterReturning(pointcut = "controllers()", returning = "result")
    public void doAfterReturning(final Object result) {
        // 处理请求的时间差
        final long difference = ChronoUnit.MILLIS.between(this.startTime, LocalDateTime.now());
        log.info("==>   Spend: {}s", difference / 1000.0);
        log.info("==>  Return: {}", result);
        log.info("================  Controller Log End  =====================");
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(pointcut = "controllers()", throwing = "e")
    public void doAfterThrowing(final Throwable e) {
        log.info("==> Exception: {}", e.toString());
        e.printStackTrace();
        log.info("================  Controller Log End  =====================");
    }

}