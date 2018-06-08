/*
package com.cyx.util;

import java.io.File;

public final class DocToHtml {
    */
/**
     *
     * @param path
     * @param paths
     * @param savepaths
     *//*

    public static void change(String path, String paths, String savepaths) {
        File d = new File(paths);
        // 取得当前文件夹下所有文件和目录的列表
        File lists[] = d.listFiles();
        String pathss = new String("");
        // 对当前目录下面所有文件进行检索
        for (int i = 0; i < lists.length; i++) {
            if (lists[i].isFile()) {
                // 得到doc文件名
                String filename = lists[i].getName();
                // 定义最简文件名
                String realfilename = new String(filename.substring(0,
                        (filename.length() - 4)));

                // 定义文件全名为路径加文件名
                String fullsavefilename = paths + realfilename;
                fullsavefilename = fullsavefilename.replace('/', '_');// /////
                fullsavefilename = fullsavefilename.replace(':', '@');
                // fullsavefilename = fullsavefilename.substring(47,
                // fullsavefilename.length());

                // 定义文件类型
                String filetype = new String("");

                filetype = filename.substring((filename.length() - 3),
                        filename.length());

                if (filetype.equals("doc")) {
                    System.out.println("--------/n当前正在转换......");
                    // 打印当前目录路径
                    System.out.println(paths);
                    // 打印doc文件名
                    System.out.println(realfilename);

                    // 启动word
                    ActiveXComponent app = new ActiveXComponent(
                            "Word.Application");

                    String docpath = paths + filename;
                    String txtpath = savepaths + fullsavefilename + ".htm";

                    // 格式化文件名
                    txtpath = formatname(txtpath);
                    // 将中文文件名改为拼音
                    txtpath = pinyin(path, txtpath);

                    // 要转换的word文件
                    String inFile = docpath;
                    // txt文件
                    String tpFile = txtpath;
                    boolean flag = false;

                    try {
                        // 设置word不可见
                        app.setProperty("Visible", new Variant(false));
                        Object docs = app.getProperty("Documents").toDispatch();
                        // 打开word文件
                        Object doc = Dispatch.invoke(
                                (Dispatch) docs,
                                "Open",
                                Dispatch.Method,
                                new Object[] { inFile, new Variant(false),
                                        new Variant(true) }, new int[1])
                                .toDispatch();
                        // 作为txt格式保存到临时文件
                        Dispatch.invoke((Dispatch) doc, "SaveAs",
                                Dispatch.Method, new Object[] { tpFile,
                                        new Variant(10) }, new int[1]);
                        Variant f = new Variant(false);
                        Dispatch.call((Dispatch) doc, "Close", f);
                        flag = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        app.invoke("Quit", new Variant[] {});
                    }
                    System.out.println("转化完毕！/n--------");
                }
            } else {
                pathss = paths;
                // 进入下一级目录
                pathss = pathss + lists[i].getName() + "//";
                // 递归遍历所有目录
                change(path, pathss, savepaths);
            }
        }

    }

    public static String formatname(String name) {
        name = name.replace('、', '_');
        name = name.replace('）', ')');
        name = name.replace('（', '(');
        return name;
    }

    public static String pinyin(String path, String hanzi) {
        File f = new File(path);
        String pinyin = new String("");
        pinyin = hanzi;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = new String("");
            line = br.readLine();
            int count = 0;
            int location = 0;
            while (line != null) {
                while (pinyin.indexOf(line.charAt(0)) != -1) {
                    location = pinyin.indexOf(line.charAt(0));
                    pinyin = pinyin.substring(0, location)
                            + line.substring(1, 2)
                            + pinyin.substring(location + 1, pinyin.length());
                }
                line = br.readLine();
                System.out.print("/b/b/b/b/b/b" + count++);
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            // System.err.println(e);
        }
        return pinyin;
    }

    public static void main(String[] args) {

        String paths = new String("e://360Downloads//");
        String savepaths = new String("e://360Downloads//Video//");
        String path = new String("e://360Downloads//pinyin.doc");

        change(path, paths, savepaths);
    }
}
*/
