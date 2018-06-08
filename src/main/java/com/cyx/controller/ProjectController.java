package com.cyx.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyx.pojo.Project;
import com.cyx.service.ProjectService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private Project project;

    @RequestMapping("/pageshow")
    @ResponseBody
    public Page<Project> test(@RequestBody Object pageNO){
        logger.info(pageNO.toString());
        JSONObject jsonObject = JSONObject.parseObject(pageNO.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        //System.out.println(test);
        //JSONObject object = JSONObject.(pageNO);
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        return projectService.qry(pageNoInt);
    }

    @RequestMapping("/pageUserShow")
    @ResponseBody
    public Page<Project> qryByUser(@RequestBody Object pageNO,HttpSession session){
        String userId=session.getAttribute("userid").toString();
        logger.info(pageNO.toString());
        JSONObject jsonObject = JSONObject.parseObject(pageNO.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        return projectService.qryByUserId(userId,pageNoInt);
    }

    @RequestMapping("/showByParams")
    @ResponseBody
    public Page<Project> showUserByParams(@RequestBody Object page){
        logger.info("根据条件模糊查询出项目信息");
        JSONObject jsonObject = JSONObject.parseObject(page.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        //logger.info(jsonObject.get("status").toString()+jsonObject.get("leader").toString()+jsonObject.get("date1").toString()+jsonObject.get("date2").toString());
        project.setName(jsonObject.get("name").toString());
        project.setLeader(jsonObject.get("leader").toString());
        project.setDate1(jsonObject.get("date1").toString());
        project.setDate2(jsonObject.get("date2").toString());
        project.setStatus(jsonObject.get("status").toString().equals("0")?null:jsonObject.get("status").toString());
        logger.info(project.getStatus());
        return projectService.queryListbyparams(project,pageNoInt);
    }

    @RequestMapping("/showByParamsAndUserId")
    @ResponseBody
    public Page<Project> showByParams(@RequestBody Object page,HttpSession session){
        logger.info("根据条件模糊查询出项目信息");
        String userId=session.getAttribute("userid").toString();
        JSONObject jsonObject = JSONObject.parseObject(page.toString());
        String pageNoString=jsonObject.get("pageNo").toString();
        int pageNoInt=Integer.parseInt(pageNoString);
        logger.info("分页查询,页码为"+pageNoString);
        Project project = new Project();
        //logger.info(jsonObject.get("status").toString()+jsonObject.get("leader").toString()+jsonObject.get("date1").toString()+jsonObject.get("date2").toString());
        project.setName(jsonObject.get("name").toString());
        project.setLeader(jsonObject.get("leader").toString());
        project.setDate1(jsonObject.get("date1").toString());
        project.setDate2(jsonObject.get("date2").toString());
        project.setStatus(jsonObject.get("status").toString().equals("0")?null:jsonObject.get("status").toString());
        project.setMember(userId);
        logger.info(project.getName());
        logger.info(project.getStatus());
        return projectService.queryListByUserId(project,pageNoInt);
    }

    @RequestMapping("/add")
    public void addProject(HttpSession session, String name, String fund, String datetime, String duration, String member, HttpServletResponse response)throws IOException {
        logger.info("项目申请"+name+"资金"+fund+"日期"+datetime+"时期"+duration+"成员"+member+"");
        project.setId(IdTool.randoom(8));
        project.setLeader(session.getAttribute("userid").toString());
        project.setName(name);
        project.setDatetime(datetime);
        project.setFund(fund);
        project.setStatus("1");
        project.setDuration(duration);
        project.setMember(member);
        int count=projectService.add(project);
        if(count!=0){
        response.sendRedirect("/userManage");}
        else
            response.sendRedirect("/error");
    }

    @RequestMapping("/mod")
    public void modProject(HttpSession session,String id, String name, String fund, String datetime, String duration, String member, HttpServletResponse response)throws IOException {
        logger.info("项目申请"+name+"资金"+fund+"日期"+datetime+"时期"+duration+"成员"+member+"");
        project.setId(id);
        //project.setLeader(session.getAttribute("userid").toString());
        project.setName(name);
        project.setDatetime(datetime);
        project.setFund(fund);
        project.setStatus("1");
        project.setDuration(duration);
        project.setMember(member);
        int count=projectService.modProject(project);
        if(count!=0){
            response.sendRedirect("/userManage");}
        else
            response.sendRedirect("/error");
    }

    @RequestMapping(value = "/intoProjectDetail")
    public ModelAndView intoProjectDetail(String id ,HttpSession session) {
        System.out.println("进入项目详细查询页面");
        ModelAndView modelAndView = new ModelAndView("/projectDetail");
        if(session.getAttribute("userrole")=="user"){
            modelAndView.setViewName("/userProjectDetail");
        }
        Project pro= projectService.queryById(id);
        modelAndView.addObject("pro",pro);
        return modelAndView;
    }

    @RequestMapping(value = "/intoProjectMod")
    public ModelAndView intoProjectModl(String id ,HttpSession session ) {
        System.out.println("进入项目详细修改页面");
        ModelAndView modelAndView = new ModelAndView("/projectMod");
        if(session.getAttribute("userrole")=="user"){
            modelAndView.setViewName("/userProjectMod");
        }
        Project pro= projectService.queryById1(id);
        if(pro.getDatetime()!=null&&pro.getDatetime()!="") {
            pro.setDatetime(DateTool.reformate(pro.getDatetime()));
        }
        modelAndView.addObject("pro",pro);
        return modelAndView;
    }

    @RequestMapping(value = "/intoAdminProjectDetail")
    public ModelAndView intoProjectDetail1(String id ) {
        System.out.println("进入项目详细查询页面");
        ModelAndView modelAndView = new ModelAndView("/adminProjectDetail");
        Project pro= projectService.queryById(id);
        modelAndView.addObject("pro",pro);
        return modelAndView;
    }

    @RequestMapping(value = "/intoAdminProjectMod")
    public ModelAndView intoProjectModl1(String id ) {
        System.out.println("进入项目详细修改页面");
        ModelAndView modelAndView = new ModelAndView("/adminProjectMod");
        Project pro= projectService.queryById1(id);
        if(pro.getDatetime()!=null&&pro.getDatetime()!="") {
            pro.setDatetime(DateTool.reformate(pro.getDatetime()));
        }
        modelAndView.addObject("pro",pro);
        return modelAndView;
    }

    @RequestMapping("/del")
    @ResponseBody
    public int del(@RequestBody Project pro){
        //无此用户项目
        if(projectService.queryListbyparams(pro,1)==null){
            return 1;
        }
        logger.info("删除用户"+pro.getId());
        return projectService.del(pro.getId());
    }

    @RequestMapping("/update")
    @ResponseBody
    public int update(@RequestBody Project pro){
        //无此用户处理
        if(projectService.queryListbyparams(pro,1)==null){
            return 1;
        }
        logger.info("修改项目状态"+pro.getStatus());
        pro.setStatus(String.valueOf(Integer.parseInt(pro.getStatus())+1));
        return projectService.modProject(pro);
    }
}
