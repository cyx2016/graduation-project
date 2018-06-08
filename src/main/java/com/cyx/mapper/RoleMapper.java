package com.cyx.mapper;

import com.cyx.pojo.Role;
import com.cyx.pojo.User;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> queryList();

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}