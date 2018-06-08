package com.cyx.service;

import com.cyx.mapper.MenuMapper;
import com.cyx.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private Menu menu;

    public List<Menu> queryList(){
        return menuMapper.queryList();
    }
    public int add(Menu record){
        return menuMapper.insert(record);
    }

    /*public int getMaxSort(){
        return menuMapper.getMaxSort();
    }*/
    public Menu queryById(String id){
        return menuMapper.selectByPrimaryKey(id);
    }

    public int del(String id){
        return menuMapper.deleteByPrimaryKey(id);
    }
    public int mod(Menu record){
        return menuMapper.updateByPrimaryKey(record);
    }
}
