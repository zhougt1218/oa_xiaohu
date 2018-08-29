package com.sdyy.springboot.oa_xiaohu.mappers;

import com.sdyy.springboot.oa_xiaohu.beans.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author xiaohu
 * @createDate 2018-08-25 13:16
 */
@Mapper
public interface UserMapper {
    //添加user
    public int addUser(Users user);

    //查找用户，验证用户
    public Users findUseByUsername(Map paraMap);

}
