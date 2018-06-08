package com.cyx.mapper;

import com.cyx.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    List<Menu> queryList();

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}