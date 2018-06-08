package com.cyx.controller;

import com.cyx.pojo.Menu;
import com.cyx.pojo.RoleMenu;
import com.cyx.service.MenuService;
import com.cyx.service.RoleMenuService;
import com.cyx.util.IdTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;

    @Autowired
    private Menu menu;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleMenu roleMenu;

    @RequestMapping("/show")
    public List<Menu> showMenu(){
        logger.info("从数据库读取Menu集合");
        return menuService.queryList();
    }

    @RequestMapping("/showRole")
    public List<RoleMenu> showRoleMenu(@RequestBody RoleMenu role){
        logger.info("从数据库读取RoleMenu集合"+role.getRoleid());
        List<RoleMenu> temp= roleMenuService.qryByRole(role.getRoleid());
        return temp;
    }
    @RequestMapping("/mod")
    @ResponseBody
    public void update(String id,String del,  HttpServletResponse response) throws IOException {
        roleMenuService.delByMenuId(id);
        roleMenu.setRoleid(Integer.parseInt(id));
        logger.info(id+"测试"+del);
        String[] menuid=del.split(",");
        for(String menu:menuid){
            roleMenu.setId(IdTool.randoom(8));
            roleMenu.setMenuid(menu);
            if(roleMenuService.add(roleMenu)==0){
                response.sendRedirect("/error");
            }
        }
        //logger.info(menuid[1]);
        //if (1 == count) {
            response.sendRedirect("/roleManage");
        //} else
            //response.sendRedirect("/error.html");
    }
}
