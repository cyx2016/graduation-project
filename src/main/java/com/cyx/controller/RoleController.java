package com.cyx.controller;


import com.cyx.pojo.Links;
import com.cyx.pojo.NewsKinds;
import com.cyx.pojo.Role;
import com.cyx.pojo.User;
import com.cyx.service.RoleService;
import com.cyx.service.UserService;
import com.cyx.util.IdTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private Role role;


    @RequestMapping("/show")
    public Object showRole(){
        logger.info("从数据库读取Role集合");
        List<Role> list= roleService.qry();
        List<Role> list1=new ArrayList<>();
        int flag=0;
        String name;
        list1.add(list.get(0));
        for(int i = 1 ; i < list.size() ; i++) {
            for(int j = 0 ; j < list1.size() ; j++){
                flag=j;
                if(list1.get(j).getId()==list.get(i).getId()){
                    list1.get(j).setUsername(list1.get(j).getUsername()+";"+list.get(i).getUsername());
                    break;
                }
            }
            if(list1.get(flag).getId()!=list.get(i).getId()&&(flag+1)==list1.size()){
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addUser(@RequestBody Role role){
        logger.info("添加角色");
        if(roleService.add(role)!=0){
            return "1";
        }
        else
            return "error";
    }

    @RequestMapping("/del")
    @ResponseBody
    public int delLinks(@RequestBody Role role){
        logger.info("删除友情链接");
        //无此用户处理
        if(roleService.queryById(role.getId())==null){
            return 1;
        }
        return roleService.del(role.getId());
    }

    @RequestMapping("/intoRoleMod")
    public ModelAndView intoMod(String id) {
        logger.info("进入权限修改页面");
        ModelAndView modelAndView = new ModelAndView("/menuMod");
        //NewsKinds newsKind = newsKindsService.queryById(id);
        modelAndView.addObject("id", id);
        return modelAndView;
    }

}
