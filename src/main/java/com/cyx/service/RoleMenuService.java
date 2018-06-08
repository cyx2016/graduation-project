package com.cyx.service;

import com.cyx.mapper.RoleMenuMapper;
import com.cyx.pojo.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleMenu roleMenu;

   /* public List<RoleMenu> queryList(){
        return roleMenuMapper.queryList();
    }*/
    public int add(RoleMenu record){
        return roleMenuMapper.insert(record);
    }

    /*public int getMaxSort(){
        return roleMenuMapper.getMaxSort();
    }*/
    public RoleMenu queryById(String id){
        return roleMenuMapper.selectByPrimaryKey(id);
    }

    public int del(String id){
        return roleMenuMapper.deleteByPrimaryKey(id);
    }
    public int mod(RoleMenu record){
        return roleMenuMapper.updateByPrimaryKey(record);
    }

    public int delByMenuId(String id){
        return roleMenuMapper.del(Integer.parseInt(id));
    }

    public List<RoleMenu> qryByRole(int roleId){
        RoleMenu role = new RoleMenu();
        role.setRoleid(roleId);
        List<RoleMenu> temp=roleMenuMapper.getMenuListByParams(role);
        return temp;
    }
}
