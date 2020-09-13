package com.sunyue.syblog.dao;

import com.sunyue.syblog.po.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

//继承了jpaRepository，这样就可以直接操作数据库了
public interface UserRepository extends JpaRepository<Usr,Long> {
    //更具实体类usr，使用jpa中的方法，传入两个参数，username和password
    Usr findByUsernameAndPassword(String username,String password);
}
