package com.sunyue.syblog.web.admin;

import com.sunyue.syblog.po.Blog;
import com.sunyue.syblog.po.Usr;
import com.sunyue.syblog.service.BlogService;
import com.sunyue.syblog.service.TagService;
import com.sunyue.syblog.service.TypeService;
import com.sunyue.syblog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {
    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model) {
        //获取分类框中的东西
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return LIST;
    }
    //只刷新表格片段，而不去刷新整个页面
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        //实现局部渲染
        return "admin/blogs :: blogList";
    }

    //博客编辑
    @GetMapping("/blogs/{id}/input")
    public String input(@PathVariable Long id, Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.listType());

        Blog blog=blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @GetMapping("/blogs/input")
    public String editinput(Model model){
        //为了共用页面是报错，初始化Blog页面
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.listType());
        //标签初始化
        model.addAttribute("tags",tagService.listTag());
        return INPUT;
    }


    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes){
        blog.setUser((Usr) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b=blogService.saveBlog(blog);
        if(b==null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete( @PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id );
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

}
