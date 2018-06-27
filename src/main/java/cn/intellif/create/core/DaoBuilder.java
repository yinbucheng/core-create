package cn.intellif.create.core;

import cn.intellif.create.annotation.NotCreate;
import cn.intellif.create.config.CoreConfig;
import cn.intellif.create.utils.HelpUtils;

import java.io.File;
import java.util.List;

public class DaoBuilder {
    private CoreConfig coreConfig;

    public DaoBuilder(CoreConfig coreConfig){
        this.coreConfig = coreConfig;
    }

    public void create(){
        List<Class> classList = coreConfig.getCreateClass();
        if(classList!=null&&classList.size()>0){
            for(Class clazz:classList){
                if(clazz.getAnnotation(NotCreate.class)!=null)
                    continue;
                String className = clazz.getSimpleName();
                String daoPath =  coreConfig.getMapper();
                String daoName = className+"Mapper";
                File path = new File(daoPath);
                if(!path.exists()) {
                    path.mkdirs();
                }
                File file = new File(daoPath+"//"+daoName+".java");
                if(file.exists())
                    continue;
                StringBuilder sb = new StringBuilder("package "+coreConfig.getDaoPackage()+";").append(HelpUtils.newLine())//
                        .append("import com.baomidou.mybatisplus.mapper.BaseMapper;").append(HelpUtils.newLine())//
                        .append("import "+coreConfig.getEntityPackage()+"."+className+";").append(HelpUtils.newLine())//
                        .append(coreConfig.getAuthor())//
                        .append("public interface "+daoName+" extends BaseMapper<"+className+">{").append(HelpUtils.newLine())//
                        .append("}");// ;
                HelpUtils.copyToFile(file,sb.toString());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>生成 "+daoName+" 完成");
            }
        }
    }
}
