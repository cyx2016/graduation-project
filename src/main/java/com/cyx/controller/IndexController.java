package com.cyx.controller;


import com.cyx.pojo.News;
import com.cyx.pojo.NewsKinds;
import com.cyx.pojo.Project;
import com.cyx.pojo.User;
import com.cyx.service.NewsKindsService;
import com.cyx.service.ProjectService;
import com.cyx.service.NewsService;
import com.cyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {


    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsKindsService newsKindsService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private User user;

    @Autowired
    private News news;

    @Autowired
    private NewsKinds newsKinds;

    @Autowired
    private Project project;


    @RequestMapping("/index2")
    public String index(ModelMap map) {
        System.out.println("进入index1控制层");
        map.addAttribute("name","陈宇祥");
        return "index1";
    }
    @RequestMapping(value = "/login1")
    public String login() {
        System.out.println("进入登录");
        return "adminHomepage";
    }

    @RequestMapping("/login")
    public String login1(String username, String password, ModelMap map, HttpServletRequest request) {
        //System.out.println(username+password);
        User user=new User();
        System.out.println("登录验证");
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
        /*if("cyx".equals(username) && "123456".equals(password)) {
            System.out.println("用户正确");
        }*/
        HttpSession session = request.getSession();
        user.setName(username);user.setPassword(password);
        User user1=userService.queryListbyparams(user,1).getResults().get(0);
        Integer flag=userService.validate(username,password);
        //System.out.println(session.getMaxInactiveInterval());
        if(flag==1){
            map.addAttribute("username",username);
            session.setAttribute("username",username);
            session.setAttribute("userid",user1.getId());
            session.setAttribute("userrole","admin");
            session.setAttribute("userroleid",user1.getRole());
            map.addAttribute("userList",userService.queryList());
            return "adminHomepage";
        }else if(flag!=1 && null!=flag){
            map.addAttribute("username",username);
            session.setAttribute("username",username);
            session.setAttribute("userid",user1.getId());
            session.setAttribute("userroleid",user1.getRole());
            session.setAttribute("userrole","user");
            map.addAttribute("projectList",projectService.queryList());
            return "userManage";
        }else
            return "error";
        //System.out.println("热部署"+username);
        /*map.addAttribute("name","陈宇祥登录成功");
        return "adminHomepage";*/
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("登出");
        //清除session
        session.invalidate();
        return "index";
    }

    @RequestMapping(value = "/showtets")
    public String showtest() {
        System.out.println("前端展示");
        return "pinterest";
    }
    @RequestMapping(value = "/show1")
    public String show1() {
        System.out.println("前端展示");
        return "index";
    }


    @RequestMapping("/showhomepage")
    @ResponseBody
    public Map<String, Object> show(){
        //news.setIsshow("1");
        //newsService.querybyNews(news).toArray();
        //JSONArray array = new  JSONArray(newstemp);
        News news = new News();
        news.setIsshow("1");
        List<News> newslist=newsService.qry(news);
        List<NewsKinds>  newsKindslist=newsKindsService.queryList();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("news",newslist);
        params.put("kinds",newsKindslist);
        //ModelAndView modelAndView = new ModelAndView("/index2");
        //modelAndView.addObject("news", newsService.querybyNews(news));
        return params;
    }

    @RequestMapping(value = "/newstemplate")
    public ModelAndView show3(String id){
        ModelAndView modelAndView = new ModelAndView("newstemplate");
        modelAndView.addObject("id",id);
        return modelAndView;
    }


}
