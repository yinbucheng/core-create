package cn.intellif.create;

import cn.intellif.create.config.CoreConfig;
import cn.intellif.create.core.FacedeBuilder;

public class Test {
    public static void main(String[] args) {
        CoreConfig coreConfig = new CoreConfig();
        coreConfig.setMapper(null,null,"cn.intellif.create.mapper");
        coreConfig.setServiceImpl(null,null,"cn.intellif.create.service.impl");
        coreConfig.setService(null,null,"cn.intellif.create.service");
        coreConfig.setEntity(null,null,"cn.intellif.create.po");
        coreConfig.setXml(null,"src//main//resources//mybatis//mapper");
        coreConfig.setController(null,null,"cn.intellif.create.controller");
        coreConfig.setTable(null,null,"schema-mysql.sql");
        FacedeBuilder facedeBuilder = new FacedeBuilder(coreConfig);
        facedeBuilder.startCreate();
    }
}
