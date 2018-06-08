package com.cyx.service;

import com.cyx.mapper.RoleMapper;
import com.cyx.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private Role role;

    public List<Role> qry(){
        return roleMapper.queryList();
    }

    public int add(Role role){
        return roleMapper.insert(role);
    }

    public Role queryById(int id){
        return roleMapper.selectByPrimaryKey(id);
    }

    public int del(int id){
        return roleMapper.deleteByPrimaryKey(id);
    }




}
