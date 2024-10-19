package com.site.mountain.component;

import com.site.mountain.entity.CmsContent;
import com.site.mountain.service.CmsContentService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TopicListTag implements TemplateDirectiveModel {
    // @Autowired 使用这个标签不能注入
    private static CmsContentService cmsContentService;

    //定义<@topicListTag>标签中的参数名称
    private static final String pageSize = "pageSize";
    private static final String pageNo = "pageNo";
    private static final String columnId = "columnId";

    @Autowired
    public void setCmsContentService(CmsContentService cmsContentService) {
        TopicListTag.cmsContentService = cmsContentService;
    }

//    public Object articleList(String columnId, String pageSize, String pageNo) {
    public Object articleList(CmsContent cmsContent) {
        Map<String, String> map = new HashMap<String, String>();
        List<CmsContent> list = new ArrayList<>();
//        CmsContent cmsContent = new CmsContent();
//        cmsContent.setColumnId(columnId);
//        cmsContent.setPageSize(Integer.valueOf(pageSize));
//        cmsContent.setPageNo(Integer.valueOf(pageNo));
//        list = getCmsContentService().findListByPage(cmsContent);
        list = cmsContentService.findListByPage(cmsContent);
        return list;
    }

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        String pageSize = map.get(this.pageSize).toString();
        String pageNo = map.get(this.pageNo).toString();
        String columnId = map.get(this.columnId).toString();

        CmsContent cmsContent = new CmsContent();
        cmsContent.setColumnId(columnId);
        cmsContent.setPageSize(Integer.valueOf(pageSize));
        cmsContent.setPageNo(Integer.valueOf(pageNo));

        environment.setVariable("list", getModel(articleList(cmsContent)));
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
