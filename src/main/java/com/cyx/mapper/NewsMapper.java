package com.cyx.mapper;

import com.cyx.pojo.News;
import com.cyx.pojo.News;
import com.cyx.pojo.News;

import java.util.List;
import java.util.Map;

public interface NewsMapper {
    int insert(News record);

    int insertSelective(News record);


    int deleteByPrimaryKey(String id);

    News selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> getNewsListByKindId(String kindId);

    List<News> queryList();

    List<News> getNewsListByParams(News record);

    List<News> queryListbyparams(Map<String, Object> map);
}