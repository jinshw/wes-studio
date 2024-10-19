package com.site.mountain.controller.cms;

import com.site.mountain.component.ArticleListTag;
import com.site.mountain.component.BlogTag;
import com.site.mountain.component.TopicListTag;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.CmsContent;
import com.site.mountain.utils.FreemarkerUtils;
import com.site.mountain.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fmk")
public class FreemarkerController {

    @Autowired
    ConstantProperties constantProperties;

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("msg", "测试index freemarker页面");
        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/output")
    public String output() {
        String str = "Welcome to this world!";
        Map map = new HashMap<>();
        map.put("msg", str);
        map.put("name", str + " jinshw");

        Map<String, Object> root = new HashMap<String, Object>();
        root.put("blogTag", new BlogTag());

        //String result = FreemarkerUtils.freeMarkerContent(map,"/templates","static/index.html");
//        String result = FreemarkerUtils.freeMarkerContent(map,"/templates","index.html","static/index.html");
        String result = FreemarkerUtils.freeMarkerContent(root, "/templates", "index.html", "static/index.html");
        //model.addAttribute("srt",str);
        return result;
    }

    @RequestMapping("/articleList")
    public String articleList() {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("blogTag", new BlogTag());
        root.put("articleListTag", new ArticleListTag());
        root.put("testTag", new ArticleListTag());

        String result = FreemarkerUtils.freeMarkerContent(root, "/templates", "index.html", "static/index.html");
        return result;
    }

    @RequestMapping("/detail")
    public String detail() {
        CmsContent cmsContent = new CmsContent();
        cmsContent.setId(UUIDUtil.create32UUID());
        cmsContent.setTitle("标题");
        cmsContent.setOldTitle("原标题");
        cmsContent.setAuthor("作者");
        cmsContent.setEditor("编辑者");
        cmsContent.setContent("<h1>今夕是何年</h1>");
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("data", cmsContent);

        String result = FreemarkerUtils.freeMarkerContent(root, "/templates", "detail.html", "static/detail.html");
        return result;
    }

    @RequestMapping("/publishMain")
    public String publishMain() {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("topicListTag", new TopicListTag());
        String ftlOutput = constantProperties.getFtlOutput();
        String result = FreemarkerUtils.freeMarkerContent(root, "/templates", "main.html", ftlOutput + "main.html");
        return result;
    }


}
