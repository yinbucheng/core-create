package cn.intellif.create.config;

import cn.intellif.create.utils.HelpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CoreConfig {
    private String shortPath="src//main//java";
    private String parentPackage="";
    private String controller;
    private String entity;
    private String service;
    private String serviceImpl;
    private String dao;
    private String xml;
    private String entityPackage;
    private String controllerPackage;
    private String servicePackage;
    private String daoPackage;
    private String author;
    private String table;
    private String resources = "src//main//resources";

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setMapper(String shortPacakge){
        dao = (HelpUtils.getProjectPath()+"//"+shortPath+"//"+shortPacakge).replace(".","//");
        daoPackage = shortPacakge;
    }


    public void setMapper(String module,String shortPath,String shortPackage){
        if(shortPath==null||shortPath.equals("")){
            shortPath = "src//main//java";
        }
        if(module==null||module.equals("")){
            dao = (HelpUtils.getProjectPath()  + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }else {
            dao = (HelpUtils.getProjectPath() + "//" + module + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }
        daoPackage = shortPackage;
    }

    /**
     * 指定controller路径
     * @param module 模块名称
     * @param shortPath 断路径
     * @param shortPackage 包名
     */
    public void setController(String module,String shortPath,String shortPackage){
        if(shortPath==null||shortPackage.equals("")){
            shortPath = "src//main//java";
        }
        if(module!=null&&!module.equals("")) {
            controller = (HelpUtils.getProjectPath() + "//" + module + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }else{
            controller = (HelpUtils.getProjectPath() + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }
        controllerPackage = shortPackage;
    }

    public void setService(String module,String shortPath,String shortPackage){
        if(shortPath==null||shortPackage.equals("")){
            shortPath = "src//main//java";
        }
        if(module!=null&&!module.equals("")) {
            service = (HelpUtils.getProjectPath() + "//" + module + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }else{
            service = (HelpUtils.getProjectPath() + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }
        servicePackage =shortPackage;
    }

    public void setEntity(String module,String shortPath,String shortPackage){
        if(shortPath==null||shortPath.equals("")){
            shortPath="src//main//java";
        }
        if(module==null||module.equals("")){
            entity = (HelpUtils.getProjectPath()  + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }else {
            entity = (HelpUtils.getProjectPath() + "//" + module + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }
        entityPackage = shortPackage;
    }

    public void setServiceImpl(String module,String shortPath,String shortPackage){
        if(shortPath==null||shortPath.equals("")){
            shortPath = "src//main//java";
        }
        if(module==null||module.equals("")){
            serviceImpl = (HelpUtils.getProjectPath() + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }else {
            serviceImpl = (HelpUtils.getProjectPath() + "//" + module + "//" + shortPath + "//" + shortPackage).replace(".", "//");
        }
    }

    public void setXml(String module,String shortPath){
        if(shortPath==null||shortPath.equals("")){
            shortPath = "src//main//resources";
        }
        if(module==null||module.equals("")){
            xml = HelpUtils.getProjectPath()+"//"+shortPath;
        }else {
            xml = HelpUtils.getProjectPath() + "//" + module + "//" + shortPath;
        }
    }

    public void setTable(String module,String shortPath,String tableName){
        if(shortPath==null||shortPath.equals("")){
            shortPath="src//main//resources";
        }
        if(module==null||module.equals("")){
            table = HelpUtils.getProjectPath()+"//"+shortPath+"//"+tableName;
        }else {
            table = HelpUtils.getProjectPath() + "//" + module + "//" + shortPath + "//" + tableName;
        }
    }

    public String getMapper() {
        return dao;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setDao(String dao) {
        this.dao = dao;
    }

    public String getParentPackage() {
        return parentPackage;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public void setParentPackage(String parentPackage) {
        if(controller==null){
            controller =  (HelpUtils.getProjectPath()+"//"+shortPath+"//"+parentPackage+".controller").replace(".","//");
        }
        if(service==null){
            service = (HelpUtils.getProjectPath()+"//"+shortPath+"//"+parentPackage+".service").replace(".","//");
        }
        if(serviceImpl==null){
            serviceImpl = (HelpUtils.getProjectPath()+"//"+shortPath+"//"+parentPackage+".service.impl").replace(".","//");
        }
        if(entity==null){
            entity =  (HelpUtils.getProjectPath()+"//"+shortPath+"//"+parentPackage+".po").replace(".","//");
        }
        if(dao==null){
            dao =  (HelpUtils.getProjectPath()+"//"+shortPath+"//"+parentPackage+".mapper").replace(".","//");
        }
        if(table==null){
            table =  HelpUtils.getProjectPath()+"//"+resources+"//schema.sql";
        }
        if(xml==null){
            xml = HelpUtils.getProjectPath()+"//"+resources+"//mybatis//mapper";
        }
        if(controllerPackage==null){
            controllerPackage = parentPackage+".controller";
        }
        if(servicePackage==null){
            servicePackage = parentPackage+".service";
        }
        if(entityPackage==null){
            entityPackage=parentPackage+".po";
        }
        if(daoPackage==null){
            daoPackage = parentPackage+".mapper";
        }
        this.parentPackage = parentPackage;
    }

    //是否允许覆盖
    private boolean rebuild = false;

    private List<Class> createClass = new LinkedList<Class>();

    private List<String> createClassNames = new LinkedList<String>();

    public List<Class> getCreateClass() {
        return createClass;
    }

    public void setCreateClass(List<Class> createClass) {
        this.createClass = createClass;
    }

    public boolean isRebuild() {
        return rebuild;
    }

    public void setRebuild(boolean rebuild) {
        this.rebuild = rebuild;
    }

    public List<String> getCreateClassNames() {
        return createClassNames;
    }

    public void setCreateClassNames(List<String> createClassNames) {
        this.createClassNames = createClassNames;
    }

    public String getShortPath() {
        return shortPath;
    }

    public void setShortPath(String shortPath) {
        this.shortPath = shortPath;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public String getXml() {
        return xml;
    }

    public String getAuthor() {
        if(author==null) {
            return "";
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append("/**").append(HelpUtils.newLine());
            sb.append("* 作者:").append(author).append(HelpUtils.newLine());
            sb.append("* 创建时间:").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).append(HelpUtils.newLine());
            sb.append("**/").append(HelpUtils.newLine());
            return sb.toString();
        }

    }

    public void setXml(String xml) {
        this.xml = xml;
    }
}
