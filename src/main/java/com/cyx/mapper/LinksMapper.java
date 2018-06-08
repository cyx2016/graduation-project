package com.cyx.mapper;

import com.cyx.pojo.Links;
import com.cyx.pojo.User;

import java.util.List;

public interface LinksMapper {
    int deleteByPrimaryKey(String id);

    int insert(Links record);

    int insertSelective(Links record);

    Links selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Links record);

    int updateByPrimaryKey(Links record);

    int getMaxSort();

    List<Links> queryList();
}