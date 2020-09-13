package com.sunyue.syblog.service;

import com.sunyue.syblog.po.Blog;
import com.sunyue.syblog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService  {
    Blog getBlog(Long id);
    //分页，把blog封装进去
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
