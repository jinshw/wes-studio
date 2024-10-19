package com.site.mountain.component;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ArticleListTag implements TemplateDirectiveModel {

    //定义<@blogTag>标签中的参数名称
    private static final String pageSize = "pageSize";


    public Object recentBlog(String pageSize) {

        Map<String,String> map = new HashMap<String,String>();
        map.put("index","1");
        map.put("pageSize",pageSize);
        map.put("value","1111");
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("index","2");
        map2.put("pageSize",pageSize);
        map2.put("value","2222");
        List<Map> list = new ArrayList<>();
        list.add(map);
        list.add(map2);
        return list;
    }

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        String pageSize = map.get(this.pageSize).toString();
        environment.setVariable("list", getModel(recentBlog(pageSize)));
        //遇到一个坑，如果页面是这样写的<@blogTag  method="recentBlog"  pageSize="3" ></@blogTag>
        //中间没有任何内容，这里会一直报空指针异常
        templateDirectiveBody.render(environment.getOut());
    }

    private DefaultObjectWrapper getBuilder() {
        return new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28).build();
    }

    private TemplateModel getModel(Object o) throws TemplateModelException {
        return this.getBuilder().wrap(o);
    }


}
