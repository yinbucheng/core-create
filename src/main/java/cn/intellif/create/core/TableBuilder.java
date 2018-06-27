package cn.intellif.create.core;

import cn.intellif.create.annotation.NotCreate;
import cn.intellif.create.config.CoreConfig;
import cn.intellif.create.utils.HelpUtils;
import cn.intellif.create.utils.ReflectUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class TableBuilder {
    private CoreConfig coreConfig;

    public TableBuilder(CoreConfig coreConfig){
        this.coreConfig = coreConfig;
    }

    public void create(){
         mysqlCreate();
    }


    private void mysqlCreate(){
        List<Class> clazzList =  coreConfig.getCreateClass();
        if(clazzList!=null&&clazzList.size()>0){
            //获取流文件
            String content = getResourceContent();
            StringBuilder sb  = new StringBuilder();
            sb.append("------------修改时间:"+ HelpUtils.formateDate(new Date())+"-----------------").append(HelpUtils.newLine());
            for(Class clazz:clazzList){
                if(clazz.getAnnotation(NotCreate.class)!=null)
                    continue;
                 sb.append(mysqlCreateTable(clazz,content));
            }
            //保存流文件
            content+=sb.toString();
            saveResource(content);
        }
    }

    public void saveResource(String content){
        String path = coreConfig.getTable();
        HelpUtils.copyToFile(new File(path),content);
    }


    private String getResourceContent(){
        String path = coreConfig.getTable();
        File file = new File(path);
        if(!file.exists())
            return "";
        BufferedReader reader = null;
        try{
            StringBuilder sb = new StringBuilder();
            reader =new BufferedReader(new InputStreamReader( new FileInputStream(path)));
            String line = null;
            while((line=reader.readLine())!=null){
                sb.append(line).append(HelpUtils.newLine());
            }
            return sb.toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String mysqlCreateTable(Class clazz,String content){
        TableName entity = (TableName) clazz.getAnnotation(TableName.class);
        if(entity==null)
            return "";
        String tableName = entity.value();
        if(content.contains(tableName+"(")){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists ");
        sb.append(tableName).append("(").append(HelpUtils.newLine());
        //创建Mysql的主键
        creatMysqlId(clazz, sb);
        //设置其他字段
        String otherContent = createMysqlOther(clazz);
        sb.append(otherContent);
        sb.append(");").append(HelpUtils.newLine());
        return sb.toString();
    }


    private String createMysqlOther(Class clazz){
        List<Field> fields = ReflectUtils.listAllFields(clazz);
        if(fields==null||fields.size()==0)
            return "";
        StringBuilder sb = new StringBuilder();
        for(Field field:fields){
            if(field.getAnnotation(TableId.class)!=null) {
                continue;
            }
            TableField tableField = field.getAnnotation(TableField.class);
            if(tableField!=null){
                if(!tableField.exist()){
                    continue;
                }
            }
            sb.append(getFieldName(field)).append(getMySqlType(field));
            sb.append(",");
            sb.append(HelpUtils.newLine());
        }
        String content = sb.toString();
        content = content.substring(0,content.length()-3);
        content+=HelpUtils.newLine();
        return content;
    }

    /**
     * 创建id
     * @param clazz
     * @param sb
     */
    private void creatMysqlId(Class clazz, StringBuilder sb) {
        //设置主键
        Field idField =  ReflectUtils.getIdField(clazz);
        TableId tableId = idField.getAnnotation(TableId.class);
        IdType idType = tableId.type();
        String idName = getFieldName(idField);

        if(idType.compareTo(IdType.AUTO)==0){
            sb.append(idName+getMySqlType(idField)).append(" primary key auto_increment,").append(HelpUtils.newLine());
        }else{
            sb.append(idName).append(getMySqlType(idField)).append("primary key,").append(HelpUtils.newLine());
        }
    }

    /**
     * 获取sql类型
     * @param field
     * @return
     */
    private String getMySqlType(Field field){
        Class clazz = field.getType();
        String type = " " +clazz.getSimpleName()+" ";
        if(clazz.equals(String.class)){
            type =" varchar(255) ";
        }else if(clazz.equals(Long.class)){
            type =" bigint ";
        }else if(clazz.equals(Integer.class)){
            type =" int ";
        }else if(clazz.equals(Date.class)){
            type =" datetime ";
        }else if(clazz.equals(Boolean.class)){
            type =" tinyint ";
        }else if(clazz.equals(Float.class)){
            type =" float  ";
        }else if(clazz.equals(Double.class)){
            type =" double ";
        }else if(clazz.equals(Boolean.class)){
            type ="tinyint";
        }
        return type;
    }

    private String getFieldName(Field field){
        String name = field.getName();
        TableField tableField =  field.getAnnotation(TableField.class);
        if(tableField!=null)
            name = tableField.value();
        return name;
    }

}
