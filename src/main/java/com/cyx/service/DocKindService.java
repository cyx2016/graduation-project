package com.cyx.service;

import com.cyx.mapper.DocumentKindMapper;
import com.cyx.pojo.DocumentKind;
import com.cyx.pojo.DocumentKindExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocKindService {

    @Autowired
    private DocumentKindMapper docKindMapper;

    @Autowired
    private DocumentKind docKind;

    private  DocumentKindExample example;

    public List<DocumentKind> queryList(){
        return docKindMapper.selectByExample(example);
    }
    public int add(DocumentKind record){
        return docKindMapper.insert(record);
    }

   /* public int getMaxSort(){
        return docKindMapper.getMaxSort();
    }*/
    public DocumentKind queryById(String id){
        return docKindMapper.selectByPrimaryKey(id);
    }

    public int delDocumentKind(String id){
        return docKindMapper.deleteByPrimaryKey(id);
    }
    public int modDocumentKind(DocumentKind record){
        return docKindMapper.updateByPrimaryKey(record);
    }

}
