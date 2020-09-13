package com.sunyue.syblog.service;

import com.sunyue.syblog.po.Usr;

public interface UserService {
    //用hu登录检查
    Usr checkUsr(String username,String password);
}
