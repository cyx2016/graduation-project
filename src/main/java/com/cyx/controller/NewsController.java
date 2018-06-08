package com.cyx.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyx.pojo.*;
import com.cyx.pojo.News;
import com.cyx.pojo.News;
import com.cyx.service.NewsService;
import com.cyx.util.DateTool;
import com.cyx.util.FileTool;
import com.cyx.util.IdTool;
import com.cyx.util.Page;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @Autowired
    private News news;

    private String imgUrl="";

    private String content="";

    /*
    * 获取file.html页面
    */
    @RequestMapping("file")
    public String file() {
        return "/file";
    }

    /**
     * 获取新闻表格
     * @return
     */
    @RequestMapping("/show")
    public Page<News> showUser(@RequestBody Object pageNO){
        JSONObject jsonObject = JSONObject.parseObject(pageNO.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        logger.info("从数据库读取新闻集合");
        Page<News> newsList= newsService.queryList(pageNoInt);
        for (News temp:newsList.getResults()){
            if(temp.getDesp().length()>=20) {
                temp.setDesp(temp.getDesp().substring(0, 20) + "...");
            }
        }
        return newsList;
    }

    @RequestMapping("/showUser")
    public Page<News> showUser1(@RequestBody Object pageNO, HttpSession session){
        JSONObject jsonObject = JSONObject.parseObject(pageNO.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        String role=session.getAttribute("userid").toString();
        News new1= new News();
        new1.setUserid(role);
        int pageNoInt=Integer.parseInt(pageNoString);
        //logger.info("分页查询,页码为"+pageNoString);
        logger.info("分页查询,页码为"+role);
        logger.info("从数据库读取新闻集合");
        Page<News> newsList= newsService.querybyNews(new1,pageNoInt);
        for (News temp:newsList.getResults()){
            if(temp.getDesp().length()>=20) {
                temp.setDesp(temp.getDesp().substring(0, 20) + "...");
            }
        }
        return newsList;
    }

    @RequestMapping("/add")
    @ResponseBody
    public int add(@RequestBody News news1){
        logger.info(news1.getDesp());
        logger.info("新闻增加");
        logger.info("新闻种类"+news1.getKindid());
        logger.info("图片路径："+imgUrl);
        logger.info("文件路径："+content);
        news1.setId(IdTool.randoom(8));
        news1.setDate(DateTool.today());
        news1.setPic(imgUrl);
        news1.setContent(content);
        //用于文件上传
        //清空图片
        imgUrl="";
        content="";
        return newsService.add(news1);
    }

    /**
     * 实现文件上传
     * 服务器对应文件名命名方式：原文件名+日期(yyyyMMdd)+四位随机数
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file1") MultipartFile file) {
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        String date = DateTool.today();
        String path = "D:/毕业设计/FileData/doc/"+date;//F:/test
        if(fileName.indexOf(".")>=0)
        {
            String filename = fileName.substring(0,fileName.lastIndexOf("."));
            String filekind = fileName.substring(fileName.lastIndexOf("."));
            filename+=date+ IdTool.randoom(4);
            fileName=filename+filekind;
            //新闻路径设置
            content=date+"/"+fileName+";";
        }
        logger.info("文本上传"+content);
        //int size = (int) file.getSize();
        //System.out.println(fileName + "-->" + size);

        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }


    /**
     * 实现多文件上传(目前只用于图片)
     * 服务器对应文件名命名方式：原文件名+日期(yyyyMMdd)+四位随机数
     */
    @RequestMapping(value = "multifileUpload", method = RequestMethod.POST)
    public @ResponseBody String multifileUpload( @RequestParam("file") List<MultipartFile> files) {

        logger.info("文件上传");
        //logger.info(DateTool.today());
        String date = DateTool.today();
        //文件上传到服务器路径
        String path = "D:/毕业设计/FileData/img/"+date;//F:/test
        //文件列表为空时
        if (files.isEmpty()) {
            return "false";
        }
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            if(fileName.indexOf(".")>=0)
            {
                String filename = fileName.substring(0,fileName.lastIndexOf("."));
                String filekind = fileName.substring(fileName.lastIndexOf("."));
                filename+=date+ IdTool.randoom(4);
                fileName = filename+filekind;
                imgUrl = imgUrl+date+"/"+fileName+";";
            }
            logger.info("文件名"+imgUrl);
            if (file.isEmpty()) {
                return "false";
            } else {
                File dest = new File(path + "/" + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }


    //文件和表单同时上传
    @RequestMapping( "multifileUpload1")
    public ModelAndView multifileUpload1(List<MultipartFile> file, MultipartFile file1, String title, String kind, String desp, String isShow, HttpSession session) {
        logger.info("增加一条新闻");
        ModelAndView view = new ModelAndView("/error");
        News new1 = new News();
        //参数设置
        new1.setKindid(kind);new1.setIsshow(isShow);new1.setDesp(desp);new1.setTitle(title);
        new1.setUserid(session.getAttribute("userid").toString());new1.setDate(DateTool.today());new1.setId(IdTool.randoom(8));
        String date = DateTool.today();
        String imgUrl="";String content="";
        //文件上传到服务器路径
        String path = "D:/毕业设计/FileData/img/"+date;//F:/test
        //文件列表为空时
        if (file.isEmpty()||file1.isEmpty()) {
            if(file.isEmpty()){
                logger.info("文件1为空");
            }
            logger.info("文件为空");
            return view;
        }

        //<editor-fold desc="图片上传并设置路径">
        for (MultipartFile filetemp : file) {
            String fileName = filetemp.getOriginalFilename();
            if(fileName.indexOf(".")>=0)
            {
                String filename = fileName.substring(0,fileName.lastIndexOf("."));
                String filekind = fileName.substring(fileName.lastIndexOf("."));
                filename+=date+ IdTool.randoom(4);
                fileName = filename+filekind;
                imgUrl = imgUrl+date+"/"+fileName+";";
            }
            logger.info("文件名"+imgUrl);
            if (file.isEmpty()) {
                return view;
            } else {
                File dest = new File(path + "/" + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    filetemp.transferTo(dest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return view;
                }
            }
        }
        //</editor-fold>

        //<editor-fold desc=设置路径
        String fileName = file1.getOriginalFilename();
        path = "D:/毕业设计/FileData/doc/"+date;//F:/test
        if(fileName.indexOf(".")>=0)
        {
            String filename = fileName.substring(0,fileName.lastIndexOf("."));
            String filekind = fileName.substring(fileName.lastIndexOf("."));
            filename+=date+ IdTool.randoom(4);
            fileName=filename+filekind;
            //新闻路径设置
            content=date+"/"+fileName+";";
        }
        logger.info("文本上传"+content);
        //int size = (int) file.getSize();
        //System.out.println(fileName + "-->" + size);

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
        //</editor-fold>

        new1.setPic(imgUrl);
        new1.setContent(content);
        if(newsService.add(new1)==0)
            return view;
        view.setViewName("/newsShow");
        return view;
    }

    @RequestMapping( "mod1")
    public ModelAndView mod1(List<MultipartFile> file, MultipartFile file1, String id,String title, String kind, String desp, String isShow, HttpSession session) {
        logger.info("修改一条新闻");
        ModelAndView view = new ModelAndView("/error");
        News new1 = new News();
        //参数设置
        new1.setKindid(kind);new1.setIsshow(isShow);new1.setDesp(desp);new1.setTitle(title);
        new1.setUserid(session.getAttribute("userid").toString());new1.setDate(DateTool.today());new1.setId(id);
        String date = DateTool.today();
        String imgUrl="";String content="";
        //文件上传到服务器路径
        String path = "D:/毕业设计/FileData/img/"+date;//F:/test
        //文件列表为空时
        if (!file.isEmpty()&&file.size()!=0) {
            //<editor-fold desc="图片上传并设置路径">
            for (MultipartFile filetemp : file) {
                if (!filetemp.isEmpty()) {
                    String fileName = filetemp.getOriginalFilename();
                    if (fileName.indexOf(".") >= 0) {
                        String filename = fileName.substring(0, fileName.lastIndexOf("."));
                        String filekind = fileName.substring(fileName.lastIndexOf("."));
                        filename += date + IdTool.randoom(4);
                        fileName = filename + filekind;
                        imgUrl = imgUrl + date + "/" + fileName + ";";
                    }
                    logger.info("文件名" + imgUrl);
                    if (file.isEmpty()) {
                        return view;
                    } else {
                        File dest = new File(path + "/" + fileName);
                        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                            dest.getParentFile().mkdir();
                        }
                        try {
                            filetemp.transferTo(dest);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            return view;
                        }
                    }
                }
            }
            //</editor-fold>
            new1.setPic(imgUrl);
        }
        if(!file1.isEmpty()){
            //<editor-fold desc=设置路径
            String fileName = file1.getOriginalFilename();
            path = "D:/毕业设计/FileData/doc/"+date;//F:/test
            if(fileName.indexOf(".")>=0)
            {
                String filename = fileName.substring(0,fileName.lastIndexOf("."));
                String filekind = fileName.substring(fileName.lastIndexOf("."));
                filename+=date+ IdTool.randoom(4);
                fileName=filename+filekind;
                //新闻路径设置
                content=date+"/"+fileName+";";
            }
            logger.info("文本上传"+content);
            //int size = (int) file.getSize();
            //System.out.println(fileName + "-->" + size);

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
            //</editor-fold>
            new1.setContent(content);
        }

        if(newsService.update(new1)==0)
            return view;
        view.setViewName("/newsShow");
        return view;
    }


    @RequestMapping("/showById")
    @ResponseBody
    public Object showNewsById(@RequestBody JSONObject object){
        logger.info("根据id查询新闻");
        News new1 = newsService.queryById(object.getString("id").toString());
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("news",new1);
        String text1 ="";String text2 ="";String text3 ="";String text4 ="";
        //logger.info(""+new1.getContent().length());
        if(null!=new1.getContent()&&""!=new1.getContent()&&0!=new1.getContent().length()) {
            File file = new File("D:/毕业设计/FileData/doc/" + new1.getContent().substring(0, new1.getContent().length() - 1));
            logger.info(file.getPath());
            if (file.exists()) {
                //String text = FileTool.txt2String(file);
                //int length = text.length();
                text1 = FileTool.txt2String(file, 0, 10);
                text2 = FileTool.txt2String(file, 11, 15);
                text3 = FileTool.txt2String(file, 16, 20);
                text4 = FileTool.txt2String(file, 21, 0);
            }
        }
        if(null!=new1.getPic()) {
            String[] imgarray = new1.getPic().split(";");
            for(String img:imgarray){
                logger.info(img);
            }
            map.put("imgarray", imgarray );
        }
        map.put("text1", text1);
        map.put("text2", text2);
        map.put("text3", text3);
        map.put("text4", text4);
        return map;
    }
    @RequestMapping("/showByParams")
    @ResponseBody
    public Page<News> showNewsByParams(@RequestBody JSONObject object/*String title,String isshow, String kind, String username, String date1, String date2*/){
        logger.info("根据条件模糊查询出新闻");
        //object.getString("")
        //logger.info(newsService.queryListbyparams(title,isshow, kind, username, date1, date2);
        Page<News> newsList= newsService.queryListbyparams(object.getString("title").toString(),
                object.getString("isshow").toString(),
                object.getString("kindname").toString(),
                object.getString("username").toString(),
                object.getString("date1").toString(),
                object.getString("date2").toString(),
                Integer.parseInt(object.getString("pageNo").toString()));
        for (News temp:newsList.getResults()){
            if(temp.getDesp().length()>=20) {
                temp.setDesp(temp.getDesp().substring(0, 20) + "...");
            }
        }

        return newsList;
    }

    @RequestMapping("/showByParamsAndUser")
    @ResponseBody
    public Page<News> showNewsByParamsAndUser(@RequestBody JSONObject object, HttpSession session){
        logger.info("根据条件模糊查询出新闻");
        String role=session.getAttribute("userid").toString();
        logger.info("cyx主页展示参数"+object.getString("isshow").toString());
        //object.getString("")
        //logger.info(newsService.queryListbyparams(title,isshow, kind, username, date1, date2);
        Page<News> newsList= newsService.queryListbyparams(object.getString("title").toString(),
                object.getString("isshow").toString(),
                object.getString("kindname").toString(),
                role,
                object.getString("date1").toString(),
                object.getString("date2").toString(),
                Integer.parseInt(object.getString("pageNo").toString()));
        for (News temp:newsList.getResults()){
            if(temp.getDesp().length()>=20) {
                temp.setDesp(temp.getDesp().substring(0, 20) + "...");
            }
        }

        return newsList;
    }

    @RequestMapping("/del")
    @ResponseBody
    public int delLinks(@RequestBody News news1){
        logger.info("删除新闻内容");
        //无此用户处理
        if(newsService.queryById(news1.getId())==null){
            return 1;
        }
        return newsService.del(news1.getId());
    }

    @RequestMapping(value = "/intoNewsMod")
    public ModelAndView intoMod(String id,HttpSession session ) {
        logger.info("进入新闻修改页面");
        ModelAndView modelAndView = new ModelAndView("newsMod");
        if(session.getAttribute("userrole")=="user"){
            modelAndView.setViewName("/userNewsMod");
        }
        News news1= newsService.queryById(id);
        modelAndView.addObject("news",news1);
        return modelAndView;
    }

    @RequestMapping(value = "/intoNewsKindDetail")
    public ModelAndView intoNewsKindDetail(String id){
        //logger.info("分页查询,页码为"+pageNoString);
        Page<News> page= newsService.qryBykindId(1,id);
        News new1 =new News();new1.setKindid(id);
        if(page.getResults().size()==0){
            page.getResults().add(new1);
        }
        /*if(null==page.getResults()){
            List<News> temp= new ArrayList<>();
            news.setKindid(id);
            temp.add(news);
            page.setResults(temp);
        }*/
        ModelAndView modelAndView = new ModelAndView("newsKindDetail");
        modelAndView.addObject("page",page);
        return modelAndView;
    }

    @RequestMapping(value = "/intoKindDetail")
    public ModelAndView intoKindDetail(String id){
        //logger.info("分页查询,页码为"+pageNoString);
        News new1 =new News();new1.setKindid(id);
        Page<News> page= newsService.qryBykindId(1,id);
        /*if(null==page.getResults()){
            List<News> temp= new ArrayList<>();
            news.setKindid(id);
            temp.add(news);
            page.setResults(temp);
        }*/
        if(page.getResults().size()==0){
            page.getResults().add(new1);
        }
        ModelAndView modelAndView = new ModelAndView("showNewsDetail");
        modelAndView.addObject("page",page);
        return modelAndView;
    }

    @RequestMapping(value = "/intoKindDetailByPage")
    @ResponseBody
    public Page<News> intoKindDetailByPage(@RequestBody Object Page){
        JSONObject jsonObject = JSONObject.parseObject(Page.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        String id = jsonObject.get("id").toString();
        int pageNoInt=Integer.parseInt(pageNoString);
        Page<News> page= newsService.qryBykindId(pageNoInt,id);
        return page;
    }

    @RequestMapping("/mod")
    @ResponseBody
    public int mod(@RequestBody News news1){
        logger.info("新闻修改");
        logger.info("新闻种类"+news1.getIsshow());
        logger.info("图片路径："+imgUrl);
        logger.info("文件路径："+content);
        news1.setDate(DateTool.today());
        news1.setPic(imgUrl);
        news1.setContent(content);
        //用于文件上传
        //清空图片路径
        imgUrl="";
        content="";
        int count = newsService.update(news1);
        logger.info("更新个数："+count);
        return count;
    }

    //主页新闻展示
    @RequestMapping("/showhomepage")
    @ResponseBody
    public List<News> show(){
        //news.setIsshow("1");
        //newsService.querybyNews(news).toArray();
        //JSONArray array = new  JSONArray(newstemp);
        //Map<String, Object> params = newsService.querybyNews(news); //new HashMap<String, Object>();
        //ModelAndView modelAndView = new ModelAndView("/index2");
        //modelAndView.addObject("news", newsService.querybyNews(news));
        int i=1;
        News news = new News();
        news.setIsshow("1");
        return newsService.qry(news);
    }


    //根据新闻种类查询新闻
    @RequestMapping(value = "/intoNews")
    @ResponseBody
    public  Page<News>  intoUserDetail(String id,HttpSession session ){
        int pageNoInt=0;
        //logger.info("分页查询,页码为"+pageNoString);
        return newsService.qryBykindId(1,id);
    }

}

