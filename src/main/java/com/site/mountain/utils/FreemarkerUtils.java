package com.site.mountain.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Map;

public class FreemarkerUtils {

    public static String getTemplate(String template, Map<String, Object> map) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        String templatePath = FreemarkerUtils.class.getResource("/").getPath() + "/templates";
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        Template temp = cfg.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        temp.process(map, stringWriter);
        return stringWriter.toString();
    }

    public static String freeMarkerContent(Map<String, Object> root, String tempPath, String tempName, String htmlName) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {
            cfg.setClassForTemplateLoading(FreemarkerUtils.class, tempPath);
            cfg.setDefaultEncoding("UTF-8");
            Template temp = cfg.getTemplate(tempName);
            //TemplateLoader templateLoader = cfg.setTemplateLoader(temp);
            //templateLoader.findTemplateSource("MainTest.ftl");
            //cfg.setTemplateLoader(templateLoader);

            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            String path = htmlName;
//            String path = FreemarkerUtils.class.getResource("/").toURI().getPath() + htmlName;
            Writer file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8"));
//            Writer file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path.substring(path.indexOf("/")))), "UTF-8"));
            //Writer file = new FileWriter(new File(path.substring(path.indexOf("/"))));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "页面生成成功";
    }

}
