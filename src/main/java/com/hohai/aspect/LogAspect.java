package com.hohai.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author jin
 */
@Aspect
@Component
public class LogAspect {

    //日志记录器
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //定义一个切面
    @Pointcut("execution(* com.hohai.controller.*.*(..))")
    public void log(){}


    //方法执行前
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+ joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        ResquestLog requestLog = new ResquestLog(url, ip, classMethod, args);
        logger.info("Request: {}",requestLog);
    }

    //方法执行后
    @After("log()")
    public void doAfter() {

    }

    //方法返回值时
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result) {
        logger.info("Result : {}",result);
    }

    //使用内部类封装请求url，请求方法等参数
    private class ResquestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public ResquestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }


}
