package com.cyx.pojo;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class User {
    private  String id ;
    private  String  name;
    private  String sex;
    private  String birth;
    private  String develope;
    private  String introduction;
    private  String password;
    private  int role;
    private  String rolename;


    public String getRolename() {
        return rolename;
    }
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    public User() {
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setDevelope(String develope) {
        this.develope = develope;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getBirth() {
        return birth;
    }

    public String getDevelope() {
        return develope;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

}
