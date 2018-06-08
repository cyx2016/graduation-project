package com.cyx.util;

import java.io.*;

public final class FileTool {
    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file,int stratline,int endline){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));//构造一个BufferedReader类来读取文件
            String s = null;
            int i=0;
            while(i<stratline){
                br.readLine();
                i++;
            }
            if(endline!=0) {
                while (i <= endline && (s = br.readLine()) != null) {//使用readLine方法，一次读一行
                    //System.out.println(s);
                    result.append(System.lineSeparator() + s);
                    i++;
                }
            }else{
                while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                    //System.out.println(s);
                    result.append(System.lineSeparator() + s);
                    i++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
