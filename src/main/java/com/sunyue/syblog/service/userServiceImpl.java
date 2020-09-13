package com.sunyue.syblog.service;

import com.sunyue.syblog.dao.UserRepository;
import com.sunyue.syblog.po.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements UserService {

    //想要实现jpa，先注入
    @Autowired
    private UserRepository userRepository;
    @Override
    public Usr checkUsr(String username, String password) {
        //返回查询到的sql语句
        Usr user= userRepository.findByUsernameAndPassword(username,password);
        return user;
    }
}
