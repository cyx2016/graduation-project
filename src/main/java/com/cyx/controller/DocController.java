package com.cyx.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyx.pojo.Document;
import com.cyx.pojo.DocumentKind;
import com.cyx.pojo.User;
import com.cyx.service.DocService;
import com.cyx.util.DateTool;
import com.cyx.util.IdTool;
import com.cyx.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/doc")
public class DocController {
    private static final Logger logger = LoggerFactory.getLogger(DocController.class);

    @Autowired
    DocService docService;

    @Autowired
    Document doc;

    /**
     * 实现文件上传
     * 服务器对应文件名命名方式：原文件名+日期(yyyyMMdd)+四位随机数
     */
    @RequestMapping("upload")
    public ModelAndView fileUpload(MultipartFile file1, String name, String member, String kind, HttpSession session) {
        String content="";
        ModelAndView view = new ModelAndView("/error");
        logger.info(file1.getOriginalFilename()+name+member+kind);
        if (file1.isEmpty()) {
            return view;
        }
        String fileName = file1.getOriginalFilename();
        String date = DateTool.today();
        String path ="D:/毕业设计/FileData/literature/"+date;
        if(fileName.contains(".") )
        {
            String filename = fileName.substring(0,fileName.lastIndexOf("."));
            String filekind = fileName.substring(fileName.lastIndexOf("."));
            filename+=date+ IdTool.randoom(4);
            fileName=filename+filekind;
            //新闻路径设置
            content=date+"/"+fileName+";";
        }
        logger.info("文本上传"+content);
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file1.transferTo(dest); //保存文件
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return view;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return view;
        }
        doc.setId(IdTool.randoom(8));
        doc.setName(name);
        doc.setLevel(member);
        logger.info(session.getAttribute("userid").toString());
        doc.setAuthor(session.getAttribute("userid").toString());
        doc.setUrl(content);
        doc.setKind(kind);
        if(docService.add(doc)==0){
            return view;
        }
        view.setViewName("/docShow");
        return view;
    }

    /**
     * 实现文档修改
     * 服务器对应文件名命名方式：原文件名+日期(yyyyMMdd)+四位随机数
     */
    @RequestMapping("mod")
    public ModelAndView mod(MultipartFile file1,String id, String name, String member, String kind, HttpSession session) {
        String content="";
        ModelAndView view = new ModelAndView("/error");
        logger.info(file1.getOriginalFilename()+name+member+kind);
        if (!file1.isEmpty()) {
            String fileName = file1.getOriginalFilename();
            String date = DateTool.today();
            String path ="D:/毕业设计/FileData/literature/"+date;
            if(fileName.contains(".") )
            {
                String filename = fileName.substring(0,fileName.lastIndexOf("."));
                String filekind = fileName.substring(fileName.lastIndexOf("."));
                filename+=date+ IdTool.randoom(4);
                fileName=filename+filekind;
                //新闻路径设置
                content=date+"/"+fileName+";";
            }
            logger.info("文本上传"+content);
            File dest = new File(path + "/" + fileName);
            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            try {
                file1.transferTo(dest); //保存文件
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return view;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return view;
            }
            doc.setUrl(content);
        }

        doc.setId(id);
        doc.setName(name);
        doc.setLevel(member);
        doc.setAuthor(session.getAttribute("userid").toString());
        doc.setKind(kind);
        if(docService.update(doc)==0){
            return view;
        }
        view.setViewName("/docShow");
        return view;
    }

    @RequestMapping("/pageshow")
    @ResponseBody
    public Page<Document> test(@RequestBody Object pageNO){
        logger.info(pageNO.toString());
        JSONObject jsonObject = JSONObject.parseObject(pageNO.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        return docService.qry(pageNoInt);
    }

    @RequestMapping("/showByParams")
    @ResponseBody
    public Page<Document> showUserByParams(@RequestBody Object page){
        Document doc = new Document();
        logger.info("根据条件模糊查询出用户信息");
        JSONObject jsonObject = JSONObject.parseObject(page.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        doc.setAuthorName(jsonObject.get("authorName").toString());
        doc.setKind(jsonObject.get("kind").toString());
        logger.info(jsonObject.get("kind").toString());
        doc.setName(jsonObject.get("name").toString());
        return docService.queryListbyparams(doc,pageNoInt);
    }

    @RequestMapping("/showByParams1")
    @ResponseBody
    public Page<Document> showUserByParams1(@RequestBody Object page,HttpSession session){
        Document doc = new Document();
        logger.info("根据条件模糊查询出用户信息");
        JSONObject jsonObject = JSONObject.parseObject(page.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        doc.setAuthorName(jsonObject.get("authorName").toString());
        doc.setKind(jsonObject.get("kind").toString());
        logger.info(jsonObject.get("kind").toString());
        doc.setName(jsonObject.get("name").toString());
        return docService.queryListbyparams(doc,pageNoInt);
    }

    @RequestMapping("/del")
    @ResponseBody
    public int delDocument(@RequestBody Document doc){
        logger.info("删除友情链接");
        //无此用户处理
        if(docService.queryById(doc.getId())==null){
            return 1;
        }
        return docService.delDocumentKind(doc.getId());
    }

    @RequestMapping(value = "/intoDocMod")
    public ModelAndView intoMod(String id ,HttpSession session) {
        System.out.println("进入文档修改页面");
        ModelAndView modelAndView = new ModelAndView("/docMod");
        if(session.getAttribute("userrole")=="user"){
            modelAndView.setViewName("/userDocMod");
        }
        Document doc= docService.queryById(id);
        modelAndView.addObject("doc",doc);
        return modelAndView;
    }
}
