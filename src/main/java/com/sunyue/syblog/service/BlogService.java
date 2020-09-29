package com.sunyue.syblog.service;

import com.sunyue.syblog.po.Blog;
import com.sunyue.syblog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService  {
    Blog getBlog(Long id);
    //分页，把blog封装进去
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    //给首页使用于分页
    Page<Blog> listBlog(Pageable pageable);

    //用于标签去查询
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);

    //搜索
    Page<Blog> listBolg(String query,Pageable pageable);

    void deleteBlog(Long id);

    //把markdown查询和转换html
    Blog getAndConvert(Long id);
}
