package com.lmy.gradle.config;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class XMLMapperLoader {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private SqlSessionFactory sqlSessionFactory;
    private Resource[] mapperLocations;
    private String packageSearchPath = "/mapper";

    public XMLMapperLoader(SqlSessionFactory sqlSessionFactory, String packageSearchPath) {
        this.sqlSessionFactory = sqlSessionFactory;
        if (packageSearchPath != null && packageSearchPath != "") {
            this.packageSearchPath = packageSearchPath;
        }
        startThreadListener();
    }

    public void startThreadListener() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        //每10秒执行一次
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                readMapperXml();
            }
        }, 0, 10, TimeUnit.SECONDS);
        readMapperXml();
    }

    public String readMapperXml() {
        try {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            // 扫描文件
            this.scanMapperXml();

            if (true) {
                // 清空configuration map的数据
                this.removeConfig(configuration);

                // 将xml 重新加载
                for (Resource configLocation : mapperLocations) {
                    if ("TestMapper.xml".equals(configLocation.getFilename())) {

                        try {
                            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configLocation.getInputStream(), configuration, configLocation.toString(), configuration.getSqlFragments());
                            xmlMapperBuilder.parse();
                            logger.debug("mapper文件[" + configLocation.getFilename() + "]缓存加载成功");
                        } catch (IOException e) {
                            logger.debug("mapper文件[" + configLocation.getFilename() + "]不存在或内容格式不对");
                            continue;
                        }
                    }
                }
            }

            return "refresh mybatis xml succssful ";
        } catch (Exception e) {
            e.printStackTrace();
            return "refresh mybatis xml fail";
        }
    }


    /**
     * 扫描xml文件所在的路径
     *
     * @throws IOException
     */
    private void scanMapperXml() {
        //根据自己项目的实际路径查替换,最终是找到非编译的 xml所在的文件夹路径
        String fileUrl = this.getClass().getResource(packageSearchPath)
                .getPath().replace("/main", "")
                .replace("build", "src/main");
        File file = new File(fileUrl);
        File[] matchingFiles = file.listFiles();
        Set<Resource> result = new LinkedHashSet<>(matchingFiles.length);
        for (File files : matchingFiles) {
            result.add(new FileSystemResource(files));
        }
        this.mapperLocations = result.toArray(new Resource[0]);
    }

    /**
     * 清空Configuration中几个重要的缓存
     *
     * @param configuration
     * @throws Exception
     */
    private void removeConfig(Configuration configuration) throws Exception {
        Class<?> classConfig = configuration.getClass();
        clearMap(classConfig, configuration, "mappedStatements");
        clearMap(classConfig, configuration, "caches");
        clearMap(classConfig, configuration, "resultMaps");
        clearMap(classConfig, configuration, "parameterMaps");
        clearMap(classConfig, configuration, "keyGenerators");
        clearMap(classConfig, configuration, "sqlFragments");
        clearSet(classConfig, configuration, "loadedResources");

    }

    private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
        Field field = null;

        if (configuration.getClass().getName().equals("com.baomidou.mybatisplus.core.MybatisConfiguration")) {
            field = classConfig.getSuperclass().getDeclaredField(fieldName);
        } else {
            field = classConfig.getClass().getDeclaredField(fieldName);
        }
        field.setAccessible(true);
        Map mapConfig = (Map) field.get(configuration);
        mapConfig.clear();
    }


    private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
        Field field = null;
        if (configuration.getClass().getName().equals("com.baomidou.mybatisplus.core.MybatisConfiguration")) {
            field = classConfig.getSuperclass().getDeclaredField(fieldName);
        } else {
            field = classConfig.getClass().getDeclaredField(fieldName);
        }
        field.setAccessible(true);
        Set setConfig = (Set) field.get(configuration);
        setConfig.clear();
    }


}