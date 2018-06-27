package cn.intellif.create.core;

import cn.intellif.create.annotation.NotCreate;
import cn.intellif.create.config.CoreConfig;
import cn.intellif.create.utils.HelpUtils;

import java.io.File;
import java.util.List;

public class XmlBuilder {
    private CoreConfig coreConfig;

    public XmlBuilder(CoreConfig coreConfig){
        this.coreConfig = coreConfig;
    }

    public void create(){
        List<Class> classList = coreConfig.getCreateClass();
        if(classList!=null&&classList.size()>0){
            for(Class clazz:classList){
                if(clazz.getAnnotation(NotCreate.class)!=null)
                    continue;
                String className = clazz.getSimpleName();
                String xmlPath = coreConfig.getXml();
                File path = new File(xmlPath);
                if(!path.exists())
                    path.mkdirs();
                String fileName = className+"Mapper.xml";
                String content=content(coreConfig.getDaoPackage()+"."+className+"Mapper");
                HelpUtils.copyToFile(new File(path+"//"+fileName),content);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+className+" xml生成完成");
            }
        }
    }



    private String content(String dao){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
                "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                "<mapper namespace=\""+dao+"\">\n" +
                "\n" +
                "</mapper>";
    }
}
