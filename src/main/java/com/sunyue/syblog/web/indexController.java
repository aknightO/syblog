package com.sunyue.syblog.web;
import com.sunyue.syblog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
//        String blog =null;
//        if(blog == null){
//            throw new NotFoundException("博客不存在");
//        }
        return "index";
    }
    @GetMapping("/flash")
    public String flash(){
        return "flash";
    }
    @GetMapping("/blog")
    public String blog(){
//        String blog =null;
//        if(blog == null){
//            throw new NotFoundException("博客不存在");
//        }
        return "blog";
    }
}
