package com.sunyue.syblog.dao;

import com.sunyue.syblog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface TypeRepository  extends JpaRepository<Type,Long> {

    Type findByName(String name);

    //按照分页的大小作为我们所要查询的条数
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
