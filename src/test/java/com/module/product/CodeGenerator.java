package com.module.product;

import freemarker.template.TemplateExceptionHandler;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by on 2017/5/11.
 *
 * 后台代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller、DataTableSearchBean，简化开发。
 *
 */
public abstract class CodeGenerator {

    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "\\src\\test\\resources\\generator\\template";

    private static final String JAVA_PATH = "\\src\\main\\java";
    private static final String RESOURCES_PATH = "\\src\\main\\resources";
    private static final String PACKAGE_PATH_SERVICE = "\\com\\module\\product\\service\\";
    private static final String PACKAGE_PATH_SERVICE_IMPL = "\\com\\module\\product\\service\\impl\\";
    private static final String PACKAGE_PATH_SEARCH_BEAN = "\\com\\module\\product\\common\\search\\";
    private static final String PACKAGE_PATH_CONTROLLER = "\\com\\module\\product\\web\\";

    private static final String AUTHOR = "CodeGenerator";
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    public static void main(String[] args) {
        genCode("admin_log");
    }

    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genModelAndMapper(tableName);
            genService(tableName);
//            genController(tableName);
        }
    }


    public static void genModelAndMapper(String tableName) {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File configFile = new File(Thread.currentThread().getContextClassLoader().getResource("generator/mybatis-generatpr-config.xml").toURI());
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            Context context = config.getContext("Mysql");

            JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = context.getJavaModelGeneratorConfiguration();
            javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);

            SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = context.getSqlMapGeneratorConfiguration();
            sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);

            JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = context.getJavaClientGeneratorConfiguration();
            javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
            //javaClientGeneratorConfiguration.setTargetPackage("com.ruhang.hf.orm.mapper");


            context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
            context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
            context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

            TableConfiguration table = context.getTableConfigurations().get(0);
            table.setTableName(tableName);
            //table.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));


            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            String modelName = tableNameConvertUpperCamel(tableName);
            System.out.println(modelName + ".java 生成成功");
            System.out.println(modelName + "Mapper.java 生成成功");
            System.out.println(modelName + "Mapper.xml 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }
    }

    public static void genService(String tableName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));

            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java")));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java")));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public static void genController(String tableName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            data.put("baseRequestMapping", tableNameConvertMappingPath(tableName));
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("viewName", tableNameConvertViewName(tableName));
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));


            cfg.getTemplate("search-bean.ftl")
                    .process(data, new FileWriter(
                            new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SEARCH_BEAN + modelNameUpperCamel + "SearchBean.java")));
            System.out.println(modelNameUpperCamel + "SearchBean.java 生成成功");

            cfg.getTemplate("controller.ftl")
                    .process(data, new FileWriter(
                            new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java")));
            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }


    private static String tableNameConvertLowerCamel(String tableName) {
        StringBuilder result = new StringBuilder();
        if (tableName != null && tableName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < tableName.length(); i++) {
                char ch = tableName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        String camel = tableNameConvertLowerCamel(tableName);
        return camel.substring(0, 1).toUpperCase() + camel.substring(1);

    }

    private static String tableNameConvertViewName(String tableName) {
        return (tableName.contains("_") ? tableName.replaceAll("_", "-") : tableName) + "-manager";
    }

    private static String tableNameConvertMappingPath(String tableName) {
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

}
