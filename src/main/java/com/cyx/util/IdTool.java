package com.cyx.util;

import java.util.Random;

public final class IdTool {
    private static  String id;

    public static String randoom(int num){
        StringBuilder str=new StringBuilder();//定义变长字符串
        Random random=new Random();
         //随机生成数字，并添加到字符串
        for(int i=0;i<num;i++){
            str.append(random.nextInt(10));
        }
        id=str.toString();
        return id;
    }

}
