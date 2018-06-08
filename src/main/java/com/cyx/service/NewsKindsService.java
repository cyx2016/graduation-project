package com.cyx.service;

import com.cyx.mapper.NewsKindsMapper;
import com.cyx.mapper.UserMapper;
import com.cyx.pojo.NewsKinds;
import com.cyx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsKindsService {
    @Autowired
    private NewsKindsMapper newsKindsMapper;

    @Autowired
    private NewsKinds newsKinds;

    public List<NewsKinds> queryList() {
        List<NewsKinds> list = newsKindsMapper.queryList();
        return list;
    }

    public int add(NewsKinds newsKind){
        return newsKindsMapper.insert(newsKind);
    }
    public int update(NewsKinds newsKind){
        return newsKindsMapper.updateByPrimaryKey(newsKind);
    }
    public NewsKinds queryById(String id){
        return newsKindsMapper.selectByPrimaryKey(id);
    }
    public int del(String id){
        return newsKindsMapper.deleteByPrimaryKey(id);
    }


}
