package com.sunyue.syblog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

//全局异常处理
//全局数据绑定
//全局数据预处理
@ControllerAdvice
public class ControllerExceptionHandler {
    //获取日志
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
//  异常处理的方式
//这里会拦截所有的异常处理
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //1.记录
        logger.error("Request url:{},Exception :{}",request.getRequestURI(),e );
        //2.返回对象
        //因为这里会拦截所有的异常，包括自己像定义的异常，所以我们需要进行判定，哪些是我们自己定义的异常处理方法
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            //找到自己指定的方法
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("exception",e );
        //3.设定返回到哪个页面：
        mv.setViewName("error/error");
        return mv;
    }
}
