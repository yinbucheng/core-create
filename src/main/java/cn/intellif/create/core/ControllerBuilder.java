package cn.intellif.create.core;

import cn.intellif.create.annotation.NotCreate;
import cn.intellif.create.config.CoreConfig;
import cn.intellif.create.utils.HelpUtils;

import java.io.File;
import java.util.List;

public class ControllerBuilder {
    private CoreConfig coreConfig;

    public ControllerBuilder(CoreConfig coreConfig) {
        this.coreConfig = coreConfig;
    }

    public void create() {
        List<Class> classes = coreConfig.getCreateClass();
        if (classes != null && classes.size() > 0) {
            for (Class clazz : classes) {
                if (clazz.getAnnotation(NotCreate.class) != null)
                    continue;
                String className = clazz.getSimpleName();
                String controllerPath = coreConfig.getController();
                String controllerName = className + "Controller";
                File path = new File(controllerPath);
                if (!path.exists()) {
                    path.mkdirs();
                }
                File file = new File(controllerPath + "//" + controllerName + ".java");
                if (file.exists())
                    continue;
                StringBuilder sb = new StringBuilder("package " + coreConfig.getControllerPackage() + ";").append(HelpUtils.newLine())//
                        .append("import " + coreConfig.getEntityPackage() + "." + className + ";").append(HelpUtils.newLine())//
                        .append("import org.springframework.web.bind.annotation.RequestMapping;").append(HelpUtils.newLine())//
                        .append("import org.springframework.web.bind.annotation.RestController;").append(HelpUtils.newLine())//
                        .append("import " + coreConfig.getServicePackage() + ".*;").append(HelpUtils.newLine())//
                        .append("import org.springframework.beans.factory.annotation.Autowired;").append(HelpUtils.newLine())//
                        .append(coreConfig.getAuthor())//
                        .append("@RestController").append(HelpUtils.newLine())//
                        .append("@RequestMapping(\"" + className.substring(0, 1).toLowerCase() + className.substring(1) + "\")").append(HelpUtils.newLine())//
                        .append("public class " + controllerName + "{").append(HelpUtils.newLine())//
                        .append(HelpUtils.newLine())//
                        .append("\t@Autowired").append(HelpUtils.newLine())//
                        .append("\tprivate I" + className + "Service  " + className.substring(0, 1).toLowerCase() + className.substring(1) + "Service;").append(HelpUtils.newLine())//
                        .append(HelpUtils.newLine())//
                        .append("\t@RequestMapping(\"test\")").append(HelpUtils.newLine())//
                        .append("\tpublic Object test(){").append(HelpUtils.newLine())//
                        .append("\t\treturn \"success\";").append(HelpUtils.newLine())//
                        .append("\t}").append(HelpUtils.newLine())//
                        .append("}");// ;
                HelpUtils.copyToFile(file, sb.toString());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>生成 " + controllerName + " 完成");
            }
        }
    }
}
