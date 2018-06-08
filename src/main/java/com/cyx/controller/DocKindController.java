package com.cyx.controller;

import com.cyx.pojo.Document;
import com.cyx.pojo.DocumentKind;
import com.cyx.service.DocKindService;
import com.cyx.service.DocService;
import com.cyx.util.IdTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/docKind")
public class DocKindController {
    private static final Logger logger = LoggerFactory.getLogger(DocKindController.class);



    /*@Autowired
    private UserMapper userMapper;*/

    @Autowired
    private DocKindService docKindService;

    @Autowired
    private DocumentKind docKind;

    @Autowired
    private DocService docService;

    @RequestMapping("/show")
    public List<DocumentKind> showUser(){
        logger.info("从数据库读取docKind集合");
        return docKindService.queryList();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addDocumentKind(@RequestBody DocumentKind docKind){
        logger.info("添加文档种类");
        docKind.setId(IdTool.randoom(8));
        if(docKindService.add(docKind)!=0){
            return docKind.getId();
        }
        else
            return "error";
    }

    @RequestMapping("/del")
    @ResponseBody
    public int delDocumentKind(@RequestBody DocumentKind docKind){
        logger.info("删除文档种类");
        Document Doc = new Document();
        Doc.setKind(docKind.getId());
        //无此文档种类处理
        if(docKindService.queryById(docKind.getId())==null){
            return 1;
        }
        if(docService.queryListbyparams(Doc,1).getResults().size()!=0){
            return 400;
        }
        return docKindService.delDocumentKind(docKind.getId());
    }

    @RequestMapping("/mod")
    @ResponseBody
    public int modDocumentKind(@RequestBody DocumentKind docKind){
        logger.info("修改友情链接");
        return docKindService.modDocumentKind(docKind);
    }
}
