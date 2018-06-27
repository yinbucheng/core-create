package cn.intellif.create.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class HelpUtils {

    //获取当前项目路径 如G:\spring-boot-first\spring-boot-first
    public static String getProjectPath(){
        String path = System.getProperty("user.dir");
        return path.replace("\\","//");
    }

    //换行
    public static String newLine(){
        return System.getProperty("line.separator");
    }

    public static void copyToFile(File file, String content){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bw.write(content);
            bw.flush();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String formateDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 从指定的位置加载类型全名称
     * @param entityPath
     * @return
     */
    public static List<String> loadClassName(String entityPath){
        String path = entityPath;
        List<String> entityNames = new LinkedList<String>();
        File filePath = new File(path);
        if(!filePath.exists())
            throw new RuntimeException("文件路径不存在请检测是否配置正确");
        File[] files = filePath.listFiles();
        if(files!=null){
            for(File file:files){
                if(file.isDirectory())
                    continue;
                String fileName = file.getName();
                if(fileName.endsWith(".java")){
                    String data = fileName.replace(".java","");
                   entityNames.add(data);
                }
            }
        }
       return entityNames;
    }
}
