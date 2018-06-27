package cn.intellif.create.core;

import cn.intellif.create.annotation.NotCreate;
import cn.intellif.create.config.CoreConfig;
import cn.intellif.create.utils.HelpUtils;

import java.io.File;
import java.util.List;

public class ServiceBuilder {
    private CoreConfig coreConfig;

    public ServiceBuilder(CoreConfig coreConfig){
        this.coreConfig = coreConfig;
    }

    public void create(){
        List<Class> clazzList = coreConfig.getCreateClass();
        if(clazzList!=null&&clazzList.size()>0){
            for(Class clazz:clazzList){
                if(clazz.getAnnotation(NotCreate.class)!=null)
                    continue;
                String className = clazz.getSimpleName();
                String servicePath =  coreConfig.getService();
                String serviceName = "I"+className+"Service";
                File path = new File(servicePath);
                if(coreConfig.getService()==null) {
                    path = new File(servicePath + "//impl");
                }
                if(!path.exists()) {
                    path.mkdirs();
                }

                File file = new File(servicePath+"//"+serviceName+".java");
                if(file.exists())
                    continue;
                StringBuilder sb = new StringBuilder("package "+coreConfig.getServicePackage()+";").append(HelpUtils.newLine())//
                        .append("import com.baomidou.mybatisplus.service.IService;").append(HelpUtils.newLine())//
                        .append("import "+coreConfig.getEntityPackage()+"."+className+";").append(HelpUtils.newLine())//
                        .append(coreConfig.getAuthor())//
                        .append("public interface "+serviceName+" extends IService<"+className+">{").append(HelpUtils.newLine())//
                        .append("}");// ;
                HelpUtils.copyToFile(file,sb.toString());


                String serviceImplPath = coreConfig.getServiceImpl();
                path = new File(serviceImplPath);
                if(!path.exists()){
                    path.mkdirs();
                }
                String serviceImplName = className+"ServiceImpl";
                file = new File(serviceImplPath+"//"+serviceImplName+".java");
                sb = new StringBuilder("package "+coreConfig.getServicePackage()+".impl;").append(HelpUtils.newLine())//
                        .append("import com.baomidou.mybatisplus.service.impl.ServiceImpl;").append(HelpUtils.newLine())//
                        .append("import "+coreConfig.getEntityPackage()+"."+className+";").append(HelpUtils.newLine())//
                        .append("import "+coreConfig.getDaoPackage()+"."+className+"Mapper;").append(HelpUtils.newLine())//
                        .append("import org.springframework.stereotype.Service;").append(HelpUtils.newLine())//
                        .append("import "+coreConfig.getServicePackage()+"."+serviceName+";").append(HelpUtils.newLine())//
                        .append(coreConfig.getAuthor())//
                        .append("@Service").append(HelpUtils.newLine())//
                        .append("public class "+serviceImplName+" extends ServiceImpl<"+className+"Mapper,"+className+"> implements "+serviceName+"{").append(HelpUtils.newLine())//
                        .append("}");
                HelpUtils.copyToFile(file,sb.toString());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>生成 "+serviceImplName+" 完成");
            }
        }
    }
}
