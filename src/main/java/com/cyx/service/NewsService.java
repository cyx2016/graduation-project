package com.cyx.service;

import com.cyx.mapper.NewsMapper;
import com.cyx.pojo.News;
import com.cyx.pojo.News;
import com.cyx.pojo.News;
import com.cyx.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private News news;

    public List<News> qry(News news){
       return newsMapper.getNewsListByParams(news);
    }

    public Page<News> qryBykindId(int pageNo,String kindId) {
        news.setKindid(kindId);
        Page<News> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        PageHelper.startPage(pageNo, 10);
        List<News> list = newsMapper.getNewsListByKindId(kindId);
        PageInfo<News> pageInfo = new PageInfo<News>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;
    }

    public Page<News> queryList(int pageNo) {
        Page<News> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(7);
        PageHelper.startPage(pageNo, 7);
        List<News> list = newsMapper.queryList();
        PageInfo<News> pageInfo = new PageInfo<News>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;
    }
    public Page<News> querybyNews(News news,int pageNo){
        Page<News> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(7);
        PageHelper.startPage(pageNo, 7);
        List<News> list = newsMapper.getNewsListByParams(news);
        PageInfo<News> pageInfo = new PageInfo<News>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;
    }
    public Page<News> queryListbyparams(String title,String isshow, String kind, String username, String date1, String date2,int pageNo){
        Page<News> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(7);
        PageHelper.startPage(pageNo, 7);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("title",title);params.put("isshow",isshow);params.put("kind",kind);
        params.put("username",username);params.put("date1",date1);params.put("date2", date2);
        List<News> list = newsMapper.queryListbyparams(params);
        PageInfo<News> pageInfo = new PageInfo<News>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;
    }

    public int add(News news){
        return newsMapper.insert(news);
    }
    public int update(News news){
        return newsMapper.updateByPrimaryKeySelective(news);
    }
    public News queryById(String id){
        return newsMapper.selectByPrimaryKey(id);
    }
    public int del(String id){
        return newsMapper.deleteByPrimaryKey(id);
    }

}
