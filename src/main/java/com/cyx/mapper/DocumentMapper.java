package com.cyx.mapper;

import com.cyx.pojo.Document;
import com.cyx.pojo.DocumentExample;
import java.util.List;

import com.cyx.pojo.News;
import org.apache.ibatis.annotations.Param;

public interface DocumentMapper {
    long countByExample(DocumentExample example);

    int deleteByExample(DocumentExample example);

    int deleteByPrimaryKey(String id);

    int insert(Document record);

    int insertSelective(Document record);

    List<Document> selectByExample(DocumentExample example);

    List<Document> qry();

    List<Document> getDocListByParams(Document record);

    Document selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Document record, @Param("example") DocumentExample example);

    int updateByExample(@Param("record") Document record, @Param("example") DocumentExample example);

    int updateByPrimaryKeySelective(Document record);

    int updateByPrimaryKey(Document record);
}