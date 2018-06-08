package com.cyx.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//静态页面访问
@Controller
public class StaticController {

    @RequestMapping(value = "/roleManage")
    public String into1() {
        return "roleManage";
    }

    @RequestMapping(value = "/links")
    public String into2() {
        return "links";
    }

    @RequestMapping(value = "/newsKind")
    public String into3() {
        return "newsKind";
    }
    @RequestMapping(value = "/newsShow")
    public String into4() {
        return "newsShow";
    }

    @RequestMapping(value = "/adminHomePage")
    public String into5() {
        return "adminHomePage";
    }

    @RequestMapping(value = "/userAdd")
    public String into6() {
        return "userAdd";
    }

    @RequestMapping(value = "/newsAdd")
    public String into7() {
        return "newsAdd";
    }

    @RequestMapping(value = "/newsKindDetail")
    public String into8() {
        return "newsKindDetail";
    }

    @RequestMapping(value = "/userManage")
    public String into9() {
        return "userManage";
    }

    @RequestMapping(value = "/projectAdd")
    public String into10() {
        return "projectAdd";
    }

    @RequestMapping(value = "/docKind")
    public String into11() {
        return "docKind";
    }

    @RequestMapping(value = "/docShow")
    public String into12() {
        return "docShow";
    }

    @RequestMapping(value = "/docAdd")
    public String into13() {
        return "docAdd";
    }

    @RequestMapping(value = "/adminProject")
    public String into14() {
        return "adminProject";
    }

    @RequestMapping(value = "/adminProjectAdd")
    public String into15() {
        return "adminProjectAdd";
    }

    @RequestMapping(value = "/userLinks")
    public String into16() {
        return "userLinks";
    }

    @RequestMapping(value = "/userNewsShow")
    public String into17() {
        return "userNewsShow";
    }

    @RequestMapping(value = "/userDocShow")
    public String into18() {
        return "userDocShow";
    }

    @RequestMapping(value = "/userProjectAdd")
    public String into19() {
        return "userProjectAdd";
    }

    @RequestMapping(value = "/userDocAdd")
    public String into20() {
        return "userDocAdd";
    }

    @RequestMapping(value = "/userNewsAdd")
    public String into21() {
        return "userNewsAdd";
    }

}
