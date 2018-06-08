package com.cyx.mapper;

import com.cyx.pojo.NewsKinds;

import java.util.List;

public interface NewsKindsMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsKinds record);

    int insertSelective(NewsKinds record);

    NewsKinds selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsKinds record);

    int updateByPrimaryKey(NewsKinds record);

    List<NewsKinds> queryList();
}