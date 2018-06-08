package com.cyx.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class DateTool {
    public final static  String[] mon={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
    //形如29 五月 2018格式修改
    public static String formate(String date){
        String[] sourceStrArray = date.split(" ");
        List<String> monList = new ArrayList<String>(Arrays.asList(mon));
        String Mon=String.valueOf(monList.indexOf(sourceStrArray[1])+1);
        if(Integer.parseInt(Mon)>Integer.parseInt("0")&&Integer.parseInt(Mon)<Integer.parseInt("10")){
            Mon="0"+Mon;
        }
        return sourceStrArray[2]+'-'+Mon+'-'+sourceStrArray[0];
    }
    //形如2018-5-12格式修改
    public static String reformate(String date){
        if(null!=date&&""!=date) {
            String[] sourceStrArray = date.split("-");
            String day = sourceStrArray[2];
            String month = mon[Integer.parseInt(sourceStrArray[1])-1];
            String year = sourceStrArray[0];
            return day + " " + month + " " + year;
        }
        return " ";
    }

    //生成当天日期，形如20180512
    public static String today(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

}
