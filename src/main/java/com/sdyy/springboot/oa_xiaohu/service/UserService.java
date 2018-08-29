package com.sdyy.springboot.oa_xiaohu.service;

import com.sdyy.springboot.oa_xiaohu.beans.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xiaohu
 * @createDate 2018-08-25 13:34
 */
@Service
public interface UserService {

    //添加User
    public int addUser(Users user);

    //查找用户，验证用户
    public Users findUseByUsername(Map paraMap);
}
