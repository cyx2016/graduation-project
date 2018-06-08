package com.cyx.service;

import com.cyx.mapper.LinksMapper;
import com.cyx.mapper.NewsKindsMapper;
import com.cyx.pojo.Links;
import com.cyx.pojo.NewsKinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinksService {
    @Autowired
    private LinksMapper linksMapper;

    @Autowired
    private Links links;

    public List<Links> queryList(){
        return linksMapper.queryList();
    }
    public int add(Links record){
        return linksMapper.insert(record);
    }

    public int getMaxSort(){
        return linksMapper.getMaxSort();
    }
    public Links queryById(String id){
        return linksMapper.selectByPrimaryKey(id);
    }

    public int delLinks(String id){
        return linksMapper.deleteByPrimaryKey(id);
    }
    public int modLinks(Links record){
        return linksMapper.updateByPrimaryKey(record);
    }

}
