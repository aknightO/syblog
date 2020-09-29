package com.sunyue.syblog.dao;

import com.sunyue.syblog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

//后面这个Long的类型代表的是主键的类型
public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend=true ")
    List<Blog> findTop(Pageable pageable);

    //搜索(按照标题和内容content查找)
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findbyQuery(String query,Pageable pageable);
}
