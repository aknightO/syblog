package com.sunyue.syblog.dao;

import com.sunyue.syblog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

//后面这个Long的类型代表的是主键的类型
public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend=true ")
    List<Blog> findTop(Pageable pageable);

    //搜索(按照标题和内容content查找,1的意思是第一个参数)
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findbyQuery(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    //select date_format(b.update_time,'%Y')as year from t_blog Group by year Order by year DESC
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    //select * from t_blog b where date_format(b.update_time,'%Y') = '2017'
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
