package com.site.mountain.controller.cms;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.component.TopicListTag;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.CmsColumn;
import com.site.mountain.entity.CmsContent;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.CmsColumnService;
import com.site.mountain.service.CmsContentService;
import com.site.mountain.utils.FileUtils;
import com.site.mountain.utils.FreemarkerUtils;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cmsContent")
public class CmsContentController {

    @Autowired
    private CmsContentService cmsContentService;

    @Autowired
    private ConstantProperties constantProperties;

    @Autowired
    private CmsColumnService cmsColumnService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody CmsContent cmsContent, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cmsContent.setSaveTime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        cmsContent.setOptPerson(sysUser.getUserId());
        cmsContent.setId(UUIDUtil.create32UUID());
        int flag = cmsContentService.insert(cmsContent);
        if (flag > 0) {
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }

        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "copy", method = RequestMethod.POST)
    public void copy(@RequestBody CmsContent cmsContent, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cmsContent.setSaveTime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        cmsContent.setOptPerson(sysUser.getUserId());
        cmsContent.setId(UUIDUtil.create32UUID());
        cmsContent.setStatus(4);
        int flag = cmsContentService.insert(cmsContent);
        if (flag > 0) {
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }

        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@RequestBody CmsContent cmsContent, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cmsContent.setSaveTime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        cmsContent.setOptPerson(sysUser.getUserId());
        int flag = cmsContentService.updateOne(cmsContent);
        if (flag > 0) {
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }

        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findList(@RequestBody CmsContent cmsContent) {
        JSONObject jsonObject = new JSONObject();
        List<CmsContent> list = cmsContentService.findList(cmsContent);
        jsonObject.put("code", 20000);
        jsonObject.put("data", list);
        return jsonObject;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody CmsContent cmsContent, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        int flag = cmsContentService.delete(cmsContent);
        if (flag > 0) {
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }

        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布栏目
     *
     * @param cmsContent
     * @param request
     * @param response
     * @throws URISyntaxException
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    public void publish(@RequestBody CmsContent cmsContent, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        List<CmsContent> list = cmsContentService.findList(cmsContent);
        CmsContent lineObj = null;
        CmsColumn cmsColumn = new CmsColumn();
        cmsColumn.setCode(cmsContent.getColumnId());
        List<CmsColumn> columns = cmsColumnService.findList(cmsColumn);
        cmsColumn = columns.get(0);
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("list", list);
        root.put("column", cmsColumn);
        String ftlOutput = constantProperties.getFtlOutput();
        String pathColumn = ftlOutput + "/" + cmsColumn.getCode();
        FileUtils.mkdirs(pathColumn);

        String homepagetemplPc = cmsColumn.getHomepagetemplPc();
        String listtemplPc = cmsColumn.getListtemplPc();
        String articledetailtemplPc = cmsColumn.getArticledetailtemplPc();

        // 栏目列表静态页生成（分页）
        String listPagesizeStr = cmsColumn.getListPagesize();
        int listPagesize = Integer.valueOf(listPagesizeStr);
        int listSize = list.size();

        int pageTotal = (int) Math.ceil(Double.valueOf(listSize) / Double.valueOf(listPagesize));
        List<CmsContent> listChd = null;
        int fromIndex = 0;
        int toIndex = 0;
        int pageNum = 0;
        for (int i = 0; i < pageTotal; i++) {
            fromIndex = i * listPagesize;
            toIndex = fromIndex + listPagesize;
            if (toIndex > listSize) {
                toIndex = listSize;
            }
            listChd = list.subList(fromIndex, toIndex);
            System.out.println(listChd.size());
            pageNum = i + 1;
            root.put("list", listChd);
            root.put("count", listSize);
            root.put("pageNum", pageNum);
            root.put("pageSize", listPagesize);
            root.put("content", cmsContent);

            FreemarkerUtils.freeMarkerContent(root, "/templates", listtemplPc, pathColumn + "/" + cmsContent.getColumnId() + "-" + pageNum + ".html");
        }

//        FreemarkerUtils.freeMarkerContent(root, "/templates", listtemplPc, pathColumn + "/" + cmsContent.getColumnId() + ".html");

        Timestamp timestamp = null;
        CmsContent prevContent = null;
        CmsContent nextContent = null;
        for (int i = 0; i < list.size(); i++) {
            lineObj = list.get(i);
            // 更新发布时间
            timestamp = new Timestamp(System.currentTimeMillis());
            lineObj.setPublishTime(timestamp);
            lineObj.setOptPerson(sysUser.getUserId());
            cmsContentService.updateOne(lineObj);
            if(list.size() == 1){
                prevContent = new CmsContent();
                prevContent.setTitle("");
                prevContent.setId("");
                nextContent = new CmsContent();
                nextContent.setTitle("");
                nextContent.setId("");
            }else if(i == 0){
                prevContent = new CmsContent();
                prevContent.setTitle("");
                prevContent.setId("");
                nextContent = list.get(i+1);
            }else if(i == (list.size()-1)){
                prevContent = list.get(i-1);
                nextContent = new CmsContent();
                nextContent.setTitle("");
                nextContent.setId("");
            }else{
                prevContent = list.get(i-1);
                nextContent = list.get(i+1);
            }
            root.put("prevContent",prevContent);
            root.put("nextContent",nextContent);
            root.put("data", lineObj);
//            String path = constantProperties.getFtlOutput() + lineObj.getColumnId() + "/" + cmsColumn.getAlias();
//            FileUtils.mkdirs(path);
            String result = FreemarkerUtils.freeMarkerContent(root, "/templates", articledetailtemplPc, pathColumn + "/" + lineObj.getId() + ".html");
        }

        jsonObject.put("status", 200);
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布首页
     *
     * @param cmsContent
     * @param request
     * @param response
     * @throws URISyntaxException
     */
    @RequestMapping(value = "publishMain", method = RequestMethod.POST)
    public void publishMain(@RequestBody CmsContent cmsContent, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        Map<String, Object> root = new HashMap<String, Object>();
        root.put("topicListTag", new TopicListTag());
        String ftlOutput = constantProperties.getFtlOutput();
//        FreemarkerUtils.freeMarkerContent(root, "/templates", "main.html", ftlOutput + "main.html");
        FreemarkerUtils.freeMarkerContent(root, "/templates", "index.html", ftlOutput + "index.html");


        jsonObject.put("status", 200);
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
