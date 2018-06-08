package com.cyx.service;

import com.cyx.mapper.ProjectMapper;
import com.cyx.mapper.UserMapper;
import com.cyx.pojo.Project;
import com.cyx.pojo.Project;
import com.cyx.pojo.User;
import com.cyx.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Project project;

    public List<Project> qryByLeader(String id){
        return projectMapper.qryByLeader(id);
    }

    public List<Project> queryList(){
        return projectMapper.queryList();
    }

    public Page<Project> qry(int pageNo){
        Page<Project> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        PageHelper.startPage(pageNo, 10);
        List<Project> list = projectMapper.queryList();
        for(Project temp:list){
            temp.setLeader(userMapper.selectByPrimaryKey(temp.getLeader()).getName());
        }
        PageInfo<Project> pageInfo = new PageInfo<Project>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;

    }

    //根据用户id查询项目
    public Page<Project> qryByUserId(String userid,int pageNo){
        Page<Project> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        PageHelper.startPage(pageNo, 10);
        List<Project> list = projectMapper.qryByLeaderOrMember(userid);
        for(Project temp:list){
            temp.setLeader(userMapper.selectByPrimaryKey(temp.getLeader()).getName());
        }
        PageInfo<Project> pageInfo = new PageInfo<Project>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;

    }

    //模糊查询项目信息
    public Page<Project> queryListByUserId(Project record,int pageNo) {
        Page<Project> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        PageHelper.startPage(pageNo, 10);
        List<Project> list = projectMapper.getProjectListByParamsAndUserId(record);
        for(Project temp:list){
            temp.setLeader(userMapper.selectByPrimaryKey(temp.getLeader()).getName());
        }
        PageInfo<Project> pageInfo = new PageInfo<Project>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;
    }

    //模糊查询项目信息
    public Page<Project> queryListbyparams(Project record,int pageNo) {
        Page<Project> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        PageHelper.startPage(pageNo, 10);
        List<Project> list = projectMapper.getProjectListByParams(record);
        for(Project temp:list){
            temp.setLeader(userMapper.selectByPrimaryKey(temp.getLeader()).getName());
        }
        PageInfo<Project> pageInfo = new PageInfo<Project>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;
    }
    public int add(Project record){
        return projectMapper.insert(record);
    }

    public Project queryById(String id){
       Project pro =projectMapper.selectByPrimaryKey(id);
       //负责人id 改为name
       pro.setLeader(userMapper.selectByPrimaryKey(pro.getLeader()).getName());
       String []memberId=pro.getMember().split(",");
       String member="";
       for(String temp:memberId){
           member=member+userMapper.selectByPrimaryKey(temp).getName()+";";
       }
       pro.setMember(member);
        switch (pro.getStatus()){
            case "1":pro.setStatus("申请中");break;
            case "2":pro.setStatus("审核过");break;
            case "3":pro.setStatus("进行中");break;
            case "4":pro.setStatus("结束");break;
            case "5":pro.setStatus("失败");break;
            default:pro.setStatus("异常");break;
        }
       return pro;
    }

    public Project queryById1(String id){
        Project pro =projectMapper.selectByPrimaryKey(id);
        //负责人id 改为name
        pro.setLeader(userMapper.selectByPrimaryKey(pro.getLeader()).getName());
        /*String []memberId=pro.getMember().split(",");
        String member="";
        for(String temp:memberId){
            member=member+userMapper.selectByPrimaryKey(temp).getName()+";";
        }
        pro.setMember(member);*/
        switch (pro.getStatus()){
            case "1":pro.setStatus("申请中");break;
            case "2":pro.setStatus("审核过");break;
            case "3":pro.setStatus("进行中");break;
            case "4":pro.setStatus("结束");break;
            case "5":pro.setStatus("失败");break;
            default:pro.setStatus("异常");break;
        }
        return pro;
    }

    public int delProject(String id){
        return projectMapper.deleteByPrimaryKey(id);
    }
    public int modProject(Project record){
        return projectMapper.updateByPrimaryKeySelective(record);
    }

    public int del(String id){
        return projectMapper.deleteByPrimaryKey(id);
    }
}
