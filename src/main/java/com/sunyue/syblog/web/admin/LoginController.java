package com.sunyue.syblog.web.admin;

import com.sunyue.syblog.po.Usr;
import com.sunyue.syblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;//这个注入主要是为了拿到sql语句中的值
//    @GetMapping()括号不写的话，会默认使用全局设置
    @GetMapping
    public String LoginPage(){
        return "admin/login";
    }
    @PostMapping("/login")
    //RedirectAttributes获取冲顶向内容
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        Usr usr=userService.checkUsr(username,password);
        if(usr!=null){
            usr.setPassword(null);//不能把密码拿到前端页面去
            session.setAttribute("user",usr);
            return "admin/index";
        }else{
            //这里使用重定向
            //如果密码不对，给前端页面提示
            attributes.addFlashAttribute("message","用户名密码错误");
            return "redirect:/admin";
        }
    }
    //注销
    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        //拿掉session中的user
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
