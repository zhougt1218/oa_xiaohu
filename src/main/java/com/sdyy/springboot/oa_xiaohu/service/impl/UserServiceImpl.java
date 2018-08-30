package com.sdyy.springboot.oa_xiaohu.service.impl;

import com.sdyy.springboot.oa_xiaohu.beans.Users;
import com.sdyy.springboot.oa_xiaohu.mappers.UserMapper;
import com.sdyy.springboot.oa_xiaohu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xiaohu
 * @createDate 2018-08-25 13:35
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper mapper;
    @Override
    public int addUser(Users user) {
       int count = mapper.addUser(user);
        return count;
    }

    @Override
    public Users findUseByUsername(Map paraMap) {
        Users user = mapper.findUseByUsername(paraMap);
        return user;
    }

    @Override
    public int updateByUser(Map paraMap) {
        int count = mapper.updateByUser(paraMap);
        return count;
    }
}
