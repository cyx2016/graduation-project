package com.cyx.mapper;


import com.cyx.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//使用Mapper注解声明该接口为mybatis的dao接口

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectByNameAndPassword(User record);

    List<User> queryList();

    List<User> getUserListByParams(User record);
}