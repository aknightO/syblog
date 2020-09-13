package com.sunyue.syblog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspest {

    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    //从前端到后端都是流的过程，所以我们可以使用切面来对日志进行处理
    //拦截所有的类获取切点
    @Pointcut("execution(* com.sunyue.syblog.web.*.*(..))")
    public void log(){
    }
    //这个log方法在切面之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url =request.getRequestURI();
        String ip =request.getRemoteAddr();
        //获取类名
        String classMethod =joinPoint.getSignature().getDeclaringTypeName() +" ." +joinPoint.getSignature().getName();
        Object[] args =joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
        //返回记录的请求
        logger.info("request:{}" + requestLog);
    }

    @After("log()")
    public void doAfter(){
        logger.info("doAfter.........");
    }

    //方法执行完成后返回的时候去拦截它
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("result:" +result);
    }
    //内部类分装所需要的东西
    public class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
