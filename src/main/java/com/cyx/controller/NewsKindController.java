package com.cyx.controller;

import com.cyx.pojo.News;
import com.cyx.pojo.NewsKinds;
import com.cyx.service.NewsKindsService;
import com.cyx.service.NewsService;
import com.cyx.util.IdTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/newsKind")
public class NewsKindController {

    private static final Logger logger = LoggerFactory.getLogger(NewsKindController.class);

    @Autowired
    private NewsKindsService newsKindsService;

    @Autowired
    private NewsKinds newsKinds;

    @Autowired
    private NewsService newsService;

    @RequestMapping("/show")
    public List<NewsKinds> showUser() {
        logger.info("从数据库读取新闻种类集合");
        return newsKindsService.queryList();
    }

    @RequestMapping("/add")
    public void addNewsKind(String name, String del, HttpServletResponse response) throws IOException {
        logger.info("新闻种类添加");
        String id = IdTool.randoom(8);
        newsKinds.setId(id);
        newsKinds.setName(name);
        newsKinds.setDel(del);
        int count = newsKindsService.add(newsKinds);
        if (count == 0) {
            response.sendRedirect("/error");
        } else {
            response.sendRedirect("/newsKind.html");
        }
    }

    @RequestMapping("/intoNewsKind")
    public ModelAndView intoMod(String id) {
        logger.info("进入新闻种类修改页面");
        ModelAndView modelAndView = new ModelAndView("/newsKindMod");
        NewsKinds newsKind = newsKindsService.queryById(id);
        modelAndView.addObject("newsKind", newsKind);
        return modelAndView;
    }

    @RequestMapping("/mod")
    @ResponseBody
    public void update(String id, String name, String del, String role, HttpServletResponse response) throws IOException {
        newsKinds.setId(id);
        newsKinds.setName(name);
        if (null == del) {
            del = "2";
        }
        newsKinds.setDel(del);
        logger.info("权限" + del);
        logger.info("新闻种类更新" + newsKinds.getId());
        int count = newsKindsService.update(newsKinds);
        if (1 == count) {
            response.sendRedirect("/newsKind.html");
        } else
            response.sendRedirect("/error.html");
    }

    @RequestMapping("/del")
    @ResponseBody
    public int del(@RequestBody NewsKinds newsKind) {
        News new1 = new News();
        new1.setKindid(newsKind.getId());
        //无此新闻种类处理
        if (newsKindsService.queryById(newsKind.getId()) == null) {
            return 1;
        }
        if(newsService.qry(new1).size()!=0){
            return 400;
        }
        logger.info("删除新闻种类" + newsKind.getId());
        return newsKindsService.del(newsKind.getId());
    }
}

