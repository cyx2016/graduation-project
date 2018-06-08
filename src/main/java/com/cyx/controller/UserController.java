package com.cyx.controller;


import com.alibaba.fastjson.JSONObject;
import com.cyx.pojo.Project;
import com.cyx.service.ProjectService;
import com.cyx.service.UserService;
import com.cyx.pojo.User;
import com.cyx.util.DateTool;
import com.cyx.util.IdTool;
import com.cyx.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);



    /*@Autowired
    private UserMapper userMapper;*/

    @Autowired
    private UserService userService;

    @Autowired
    private User user;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/show")
    public List<User> showUser(){
        logger.info("从数据库读取user集合");
        return userService.queryList();
    }



    @RequestMapping(value = "/intoUserMod")
    public ModelAndView intoMod(String id /*@RequestBody User user*/) {
        //user.setId(id);
        System.out.println("进入用户修改页面");
        //System.out.println(userService.queryListbyparams(user).get(0).getName());
        ModelAndView modelAndView = new ModelAndView("/userMod");
        User user1= userService.queryUser(id);
        user1.setBirth(DateTool.reformate(user1.getBirth()));
        //ModelAndView modelAndView = new ModelAndView("/index2");
        modelAndView.addObject("user",user1);
        return modelAndView;
        //return userService.queryListbyparams(user).get(0);
    }

    @RequestMapping(value = "/intoUserDetail")
    public ModelAndView intoUserDetail(String id , HttpSession session/*@RequestBody User user*/) {
        //user.setId(id);
        System.out.println("进入用户详细查询页面");
        logger.info(""+session.getAttribute("username"));
        //System.out.println(userService.queryListbyparams(user).get(0).getName());
        ModelAndView modelAndView = new ModelAndView("/userDetail");
        User user1= userService.queryUser(id);
        user1.setBirth(DateTool.reformate(user1.getBirth()));
        //ModelAndView modelAndView = new ModelAndView("/index2");
        modelAndView.addObject("user",user1);
        return modelAndView;
        //return userService.queryListbyparams(user).get(0);
    }

    @RequestMapping("/add")
    public void addUser(String name, String sex, String birth, String develope, String Introduction, String password, String  role,HttpServletResponse response)throws IOException {
        logger.info("用户增加");
        String id= IdTool.randoom(8);
        user.setId(id);
        user.setName(name);
        user.setRole(Integer.parseInt(role));
        user.setSex(sex);
        user.setIntroduction(Introduction);
        if(null==password||password.equals("")){
            password="123456";
        }
        user.setPassword(password);
        user.setDevelope(develope);
        logger.info(birth);
        if(null!=birth&&""!=birth) {
            user.setBirth(DateTool.formate(birth));
        }
        int count =userService.addUser(user);
        if(count==0){
            response.sendRedirect("/error");
           //return "/error";
        }else{
            //session获取
            //modelAndView.addObject("username","cyx");
            //modelAndView.addObject("userList", userService.queryList());
            //login();

            response.sendRedirect("/adminHomePage.html");
            //return"adminHomepage";
        }
    }

    @RequestMapping("/show1")
    public List<User> showUser1(@RequestBody User user){
        logger.info("从数据库读取user集合");
        return userService.queryListbyparams(user);
    }

    @RequestMapping("/showteam")
    public List<User> showUser1(){
        logger.info("查询出用户并去除管理员");
        List<User> userList=userService.queryList();
        Iterator<User> iter = userList.iterator();
        while(iter.hasNext()){
            if(iter.next().getRole()==1){
                iter.remove();
            }
        }
        return userList;
    }

    @RequestMapping("/del")
    @ResponseBody
    public int del(@RequestBody User user){
        //无此用户处理
        if(userService.queryListbyparams(user,1)==null){
            return 1;
        }

        projectService.qryByLeader(user.getId());
        if(0!=projectService.qryByLeader(user.getId()).size()){
            return 400;
        }

        logger.info("删除用户"+user.getId());
        return userService.delUser(user.getId());
    }
    @RequestMapping(value = "/login2")
    public String login() {
        System.out.println("进入登录");
        return "adminHomepage";
    }
    @RequestMapping("/insert")
    @ResponseBody
    public int insert(@RequestBody User user){
        String id= IdTool.randoom(8);
        user.setId(id);
        return userService.addUser(user);
    }
    @RequestMapping("/update")
    //@ResponseBody@RequestBody
    public void update(String id, String name, String sex, String birth, String develope, String Introduction, String password, String  role, HttpServletResponse response, HttpServletRequest request)throws Exception {
        user.setId(id);
        user.setName(name);
        user.setRole(Integer.parseInt(role));
        user.setSex(sex);
        user.setIntroduction(Introduction);
        user.setPassword(password);
        user.setDevelope(develope);
        if(null!=birth&&""!=birth) {
            user.setBirth(DateTool.formate(birth));
        }
        logger.info("用户更新"+user.getId());
        int count=userService.updateUser(user);
        logger.info("用户更新个数"+count);
        if(1==count){
            request.getRequestDispatcher("/adminHomePage").forward(request, response);
            //response.sendRedirect("/adminHomePage.html");
        }else
            response.sendRedirect("/error.html");
    }
 /*   @RequestMapping("/show1")
    public User showUser1(String id){
        logger.info("从数据库查询user信息");
        return userMapper.selectByPrimaryKey(id);
    }*/

    @RequestMapping("/showByParams")
    @ResponseBody
    public Page<User> showUserByParams(@RequestBody Object page){
        logger.info("根据条件模糊查询出用户信息");
        JSONObject jsonObject = JSONObject.parseObject(page.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        user.setName(jsonObject.get("name").toString());
        user.setId(jsonObject.get("id").toString());
        user.setSex(jsonObject.get("sex").toString());
        System.out.println(jsonObject.get("role").equals("")? "0":jsonObject.get("role").toString());
        user.setRole(Integer.parseInt(jsonObject.get("role").equals("")? "0":jsonObject.get("role").toString()));
        //logger.info(userService.queryListbyparams(user).toString());
        //System.out.println(userService.queryListbyparams(user).get(0).getRole());
        return userService.queryListbyparams(user,pageNoInt);
    }

    @RequestMapping("/pageshow")
    @ResponseBody
    public Page<User> test(@RequestBody Object pageNO){
        logger.info(pageNO.toString());
        JSONObject jsonObject = JSONObject.parseObject(pageNO.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        //System.out.println(test);
        //JSONObject object = JSONObject.(pageNO);
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        return userService.qry(pageNoInt);
    }
}
