package com.cyx.service;



import com.cyx.mapper.UserMapper;
import com.cyx.pojo.User;
import com.cyx.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private User user;

    public List<User> queryList() {
        List<User> list = userMapper.queryList();
        return list;
    }

    public User queryUser(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
    public List<User> queryListbyparams(User user){
        return userMapper.getUserListByParams(user);
    }

    //模糊查询用户信息
    public Page<User> queryListbyparams(User user,int pageNo) {
        Page<User> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(7);
        PageHelper.startPage(pageNo, 7);
        List<User> list = userMapper.getUserListByParams(user);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;
    }

    //分页查询测试
    public Page<User> qry(int pageNo){
        Page<User> page=new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(7);
        PageHelper.startPage(pageNo, 7);
        List<User> list = userMapper.queryList();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        page.setTotalRecord(pageInfo.getTotal());
        page.setResults(list);
        return page;

    }

    public Integer validate(String name,String password){
        user.setPassword(password);
        user.setName(name);
        return userMapper.selectByNameAndPassword(user);
    }

    public int updateUser(User user){
        return userMapper.updateByPrimaryKey(user);
    }
    public int addUser(User user){
        return userMapper.insertSelective(user);
    }

    public int delUser(String id){
        return userMapper.deleteByPrimaryKey(id);
    }
}
