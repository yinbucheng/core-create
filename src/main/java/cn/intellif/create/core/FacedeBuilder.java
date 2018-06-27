package cn.intellif.create.core;

import cn.intellif.create.config.CoreConfig;
import cn.intellif.create.utils.HelpUtils;

import java.util.LinkedList;
import java.util.List;

public class FacedeBuilder {
    private DaoBuilder daoBuilder;
    private ServiceBuilder serviceBuilder;
    private ControllerBuilder controllerBuilder;
    private TableBuilder tableBuilder;
    private XmlBuilder xmlBuilder;
    private CoreConfig coreConfig;


    public FacedeBuilder(CoreConfig coreConfig){
        this.coreConfig = coreConfig;
        daoBuilder = new DaoBuilder(coreConfig);
        serviceBuilder = new ServiceBuilder(coreConfig);
        xmlBuilder = new XmlBuilder(coreConfig);
        tableBuilder = new TableBuilder(coreConfig);
        controllerBuilder = new ControllerBuilder(coreConfig);
    }

    private void init(){
       List<String> classNames =  HelpUtils.loadClassName(coreConfig.getEntity());
       if(classNames!=null&&classNames.size()>0){
           List<Class> classList = new LinkedList<Class>();
           for(String className:classNames){
               try {
                   classList.add(Class.forName(coreConfig.getEntityPackage()+"."+className));
               } catch (ClassNotFoundException e) {
                  throw new RuntimeException(e);
               }
           }
           coreConfig.setCreateClass(classList);
       }
    }

    public void startCreate(){
        init();
        daoBuilder.create();
        xmlBuilder.create();
        serviceBuilder.create();
        tableBuilder.create();
        controllerBuilder.create();
    }

}
