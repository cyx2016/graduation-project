package com.cyx.controller;

import com.cyx.pojo.Links;
import com.cyx.pojo.Links;
import com.cyx.service.LinksService;
import com.cyx.service.UserService;
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
@RequestMapping("/links")
public class LinksController {
    private static final Logger logger = LoggerFactory.getLogger(LinksController.class);



    /*@Autowired
    private UserMapper userMapper;*/

    @Autowired
    private LinksService linksService;

    @Autowired
    private Links links;

    @RequestMapping("/show")
    public List<Links> showUser(){
        logger.info("从数据库读取links集合");
        return linksService.queryList();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addUser(@RequestBody Links link){
        logger.info("添加用户");
        link.setId(IdTool.randoom(2));
        if(null==link.getSort()){
            link.setSort(linksService.getMaxSort()+1);
        }
        if(linksService.add(link)!=0){
            return link.getId();
        }
        else
            return "error";
    }
    
    @RequestMapping("/del")
    @ResponseBody
    public int delLinks(@RequestBody Links link){
        logger.info("删除友情链接");
        //无此用户处理
        if(linksService.queryById(link.getId())==null){
            return 1;
        }
        return linksService.delLinks(link.getId());
    }

    @RequestMapping("/mod")
    @ResponseBody
    public int modLinks(@RequestBody Links link){
        logger.info("修改友情链接");
        return linksService.modLinks(link);
    }
}
