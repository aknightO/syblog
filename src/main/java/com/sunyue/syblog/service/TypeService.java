package com.sunyue.syblog.service;

import com.sunyue.syblog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    //新增分类
    Type saveType(Type type);

    //根据id查询分类
    Type getType(Long id);

    //根据分类分页查询
    Page<Type> listType(Pageable pageable);

    //修改分类
    Type updateType(Long id,Type type);

    //通过name查type
    Type getTypeByName(String name);

    //删除分类
    void deleteType(Long id);

    //返回所有数据
    List<Type> listType();

    //首页的分类推荐
    List<Type> listTypeTop(Integer size);
}
