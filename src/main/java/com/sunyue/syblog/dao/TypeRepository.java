package com.sunyue.syblog.dao;

import com.sunyue.syblog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository  extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
