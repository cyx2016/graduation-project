package com.cyx.mapper;

import com.cyx.pojo.DocumentKind;
import com.cyx.pojo.DocumentKindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentKindMapper {
    long countByExample(DocumentKindExample example);

    int deleteByExample(DocumentKindExample example);

    int deleteByPrimaryKey(String id);

    int insert(DocumentKind record);

    int insertSelective(DocumentKind record);

    List<DocumentKind> selectByExample(DocumentKindExample example);

    DocumentKind selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DocumentKind record, @Param("example") DocumentKindExample example);

    int updateByExample(@Param("record") DocumentKind record, @Param("example") DocumentKindExample example);

    int updateByPrimaryKeySelective(DocumentKind record);

    int updateByPrimaryKey(DocumentKind record);
}