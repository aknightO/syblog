package com.sunyue.syblog.web;
import com.sunyue.syblog.service.BlogService;
import com.sunyue.syblog.service.TagService;
import com.sunyue.syblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,  Model model)
    {
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
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
