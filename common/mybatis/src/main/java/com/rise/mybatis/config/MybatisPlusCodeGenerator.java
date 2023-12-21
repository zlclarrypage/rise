package com.rise.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author 张牧之
 * @date 2022-12-07 00:15:57
 * @Email zhanglichang99@gmail.com
 */
public class MybatisPlusCodeGenerator {
    public static void main(String[] args) {
        //====================配置变量区域=====================//
        String author="";//生成文件的作者，可以不填
        String rootPackage="";//生成的entity、controller、service等包所在的公共上一级包路径全限定名
        String moduleName="province-sess-platfrom-xqxy";
        //数据库配置
        String url="jdbc:mysql://localhost:3306/rise_sys?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String driverClassName="com.mysql.cj.jdbc.Driver";//或者com.mysql.cj.jdbc.Driver
        String username="root";
        String password="123456";
        String tableNames="sys_filters";//表名，多个使用,分隔
        //====================配置变量区域=====================//

        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = "/Users/zhanglichang/Documents/CodeGenerator";
        globalConfig.setOutputDir(projectPath +"/java");
        globalConfig.setFileOverride(false);//是否覆盖已有文件，默认false
        globalConfig.setOpen(false);//是否打开输出目录
        globalConfig.setAuthor(author);
        globalConfig.setServiceName("%sService");//去掉service接口的首字母I
        globalConfig.setBaseResultMap(true);//开启 BaseResultMap
        globalConfig.setDateType(DateType.ONLY_DATE);//只使用 java.util.date代替
        globalConfig.setIdType(IdType.ASSIGN_ID);//分配ID (主键类型为number或string）
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDbType(DbType.MYSQL);//数据库类型
        dataSourceConfig.setDriverName(driverClassName);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        //packageConfig.setModuleName(scanner("模块名"));
        packageConfig.setParent(rootPackage);//例：org.jeecg.modules.xqxy
        generator.setPackageInfo(packageConfig);

        //注意：模板引擎在mybatisplus依赖中的templates目录下，可以依照此默认模板进行自定义

        // 策略配置：配置根据哪张表生成代码
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tableNames);//表名，多个英文逗号分割（与exclude二选一配置）
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);//lombok模型，@Accessors(chain = true)setter链式操作
        strategy.setRestControllerStyle(true);//controller生成@RestController
        strategy.setEntityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解

        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }
}
