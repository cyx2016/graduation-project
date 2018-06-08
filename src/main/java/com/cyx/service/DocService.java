package com.cyx.service;

import com.cyx.mapper.DocumentMapper;
import com.cyx.pojo.Document;
import com.cyx.pojo.DocumentExample;
import com.cyx.pojo.Document;
import com.cyx.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocService {
    @Autowired
    private DocumentMapper docMapper;

    @Autowired
    private Document doc;

    private DocumentExample example;

    public List<Document> queryList(){
        return docMapper.selectByExample(example);
    }
    public int add(Document record){
        return docMapper.insert(record);
    }

    /* public int getMaxSort(){
         return docMapper.getMaxSort();
     }*/
    public Document queryById(String id){
        return docMapper.selectByPrimaryKey(id);
    }

    public int delDocumentKind(String id){
        return docMapper.deleteByPrimaryKey(id);
    }
    public int modDocumentKind(Document record){
        return docMapper.updateByPrimaryKey(record);
    }
    public int update(Document record){return docMapper.updateByPrimaryKeySelective(record);}

    //分页查询测试
    public Page<Document> qry(int pageNo){
        Page<Document> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        PageHelper.startPage(pageNo, 10);
        List<Document> list = docMapper.qry();
        PageInfo<Document> pageInfo = new PageInfo<Document>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;

    }

    //模糊查询用户信息
    public Page<Document> queryListbyparams(Document doc,int pageNo) {
        Page<Document> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        PageHelper.startPage(pageNo, 10);
        List<Document> list = docMapper.getDocListByParams(doc);
        PageInfo<Document> pageInfo = new PageInfo<Document>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;
    }
}
