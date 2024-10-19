package com.site.mountain.controller.pjmanage;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.controller.sys.SysFilesController;
import com.site.mountain.entity.ProjectFile;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.ProjectFileService;
import com.site.mountain.utils.ResponseUtils;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XU
 * @Title:
 * @Package
 * @Description:
 * @date 2021/5/19 001915:16
 */
@CrossOrigin
@RequestMapping(value = "/projectFile")
@Controller
public class ProjectFileController {
    private final static Logger logger = LoggerFactory.getLogger(SysFilesController.class);

    @Autowired
    ProjectFileService projectFileService;

    @Autowired
    ConstantProperties constantProperties;

    private String masterType = "contractFile";

    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public void fileUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        ProjectFile projectFile = getProjectFile(file);
        //获取文件名
        String filePathName = UUIDUtil.create32UUID() + "." +projectFile.getType();
        String path = constantProperties.getFileUploadPath();
        String filePath = path + filePathName;
        File saveFile = new File(filePath);
        boolean isCreateSuccess = saveFile.createNewFile();
        if (isCreateSuccess) {
            //写入文件
            file.transferTo(saveFile);
            String coverUrl = constantProperties.getFileUploadPath() + "/" + filePathName;
            projectFile.setPath(coverUrl);
            projectFile.setStoreName(filePathName);
            projectFile.setId(UUIDUtil.create32UUID());
            /*Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();*/
            projectFile.setMasterType(masterType);
            int flag = projectFileService.insert(projectFile);
            jsonObject.put("status", 200);
            jsonObject.put("id", projectFile.getId());
            jsonObject.put("fileName", projectFile.getFileName());
            jsonObject.put("size", projectFile.getSize());
            jsonObject.put("type", projectFile.getType());
            jsonObject.put("path", projectFile.getPath());
            jsonObject.put("storeName", projectFile.getStoreName());
            jsonObject.put("masterType", projectFile.getMasterType());
            jsonObject.put("masterId", projectFile.getMasterId());
            jsonObject.put("isDelete", projectFile.getIsDelete());
            jsonObject.put("gmtCreate", projectFile.getGmtCreate());
            jsonObject.put("gmtModify", projectFile.getGmtModify());
            if (flag == 0) {
                jsonObject.put("status", 501);
                jsonObject.put("msg", "文件上传失败");
            }
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("msg", "文件已存在");
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ProjectFile getProjectFile (MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        suffix = suffix.substring(1,suffix.length());
        long size = file.getSize();
        ProjectFile pjFile = new ProjectFile();
        pjFile.setFileName(fileName);
        pjFile.setSize((double)size);
        pjFile.setType(suffix);
        return pjFile;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map delete(@RequestBody ProjectFile projectFile) {
        Map map = new HashMap();
        int flag = projectFileService.delete(projectFile);
        if (flag > 0) {
            map.put("status", 30000);
        } else {
            map.put("status", 30001);
        }
        map.put("code", 20000);
        return map;
    }



    @RequestMapping(value = "fileDownLoad", method = RequestMethod.POST)
    public void fileDownLoad(@RequestBody ProjectFile projectFile, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String fileName = projectFile.getFileName();
        String filePath = projectFile.getPath();
        String path = filePath;
        File file = new File(path);

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream;charset=utf-8");
        try {
            if (!file.exists()) {
                logger.info("文件不存在");
                jsonObject.put("code", 20000);
                jsonObject.put("status", 500);
                jsonObject.put("data", "");
                jsonObject.put("msg", "文件不存在");
                response.setContentType("application/force-download");
                response.getWriter().print(jsonObject.toJSONString());
            }
            response.addHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

            byte[] buffer = new byte[1024];
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //通用附件上传(单个文件)
    @RequestMapping(value = "attachFileUpload", method = RequestMethod.POST)
    public void attachFileUpload(@RequestParam("file") MultipartFile files, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        String attachType = request.getParameter("attachTypeDict");
        ProjectFile projectFile = getProjectFile(files);
        //获取文件名
        String filePathName = UUIDUtil.create32UUID() + "." +projectFile.getType();
        String path = constantProperties.getFileUploadPath()+attachType;
        String filePath = path +"/"+ filePathName;//添加文件类型
        File saveFile = new File(filePath);
        if(!saveFile.getParentFile().exists()){
            saveFile.getParentFile().mkdirs();
        }
        boolean isCreateSuccess = saveFile.createNewFile();
        if (isCreateSuccess) {
            //写入文件
            files.transferTo(saveFile);
            String coverUrl = path+ "/" + filePathName;
            projectFile.setPath(coverUrl);
            projectFile.setStoreName(filePathName);
            projectFile.setId(UUIDUtil.create32UUID());
            /*Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();*/
            projectFile.setMasterType(attachType);
            int flag = projectFileService.insert(projectFile);
            jsonObject.put("status", 200);
            jsonObject.put("id", projectFile.getId());
            jsonObject.put("fileName", projectFile.getFileName());
            jsonObject.put("size", projectFile.getSize());
            jsonObject.put("type", projectFile.getType());
            jsonObject.put("path", projectFile.getPath());
            jsonObject.put("storeName", projectFile.getStoreName());
            jsonObject.put("masterType", projectFile.getMasterType());
            jsonObject.put("masterId", projectFile.getMasterId());
            jsonObject.put("isDelete", projectFile.getIsDelete());
            jsonObject.put("gmtCreate", projectFile.getGmtCreate());
            jsonObject.put("gmtModify", projectFile.getGmtModify());
            jsonObject.put("accessPath", constantProperties.getFileUrl()+"/"+projectFile.getMasterType()+ "/"+projectFile.getStoreName());
            if (flag == 0) {
                jsonObject.put("status", 501);
                jsonObject.put("msg", "文件上传失败");
            }
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("msg", "文件已存在");
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        }
    }

    //通用附件上传(多个批量文件)
    @RequestMapping(value = "attachBatchFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils attachBatchFileUpload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request){
        if (files.length == 0) {
            return ResponseUtils.error("上传文件不能为空");
        }
        //存放文件集合
        List<Map<String,Object>> fileBatchList = new ArrayList();
        try{
            String attachType = request.getParameter("attachTypeDict");
            for(MultipartFile fileTemp:files){
                JSONObject jsonObject = new JSONObject();

                ProjectFile projectFile = getProjectFile(fileTemp);
                //获取文件名
                String filePathName = UUIDUtil.create32UUID() + "." +projectFile.getType();
                String path = constantProperties.getImgUploadPath()+attachType;
                String filePath = path +"/"+ filePathName;//添加文件类型
                File saveFile = new File(filePath);
                if(!saveFile.getParentFile().exists()){
                    saveFile.getParentFile().mkdirs();
                }
                boolean isCreateSuccess = saveFile.createNewFile();
                if (isCreateSuccess) {
                    //写入文件
                    fileTemp.transferTo(saveFile);
                    String coverUrl = path+ "/" + filePathName;
                    projectFile.setPath(coverUrl);
                    projectFile.setStoreName(filePathName);
                    projectFile.setId(UUIDUtil.create32UUID());
                    projectFile.setMasterType(attachType);
                    int flag = projectFileService.insert(projectFile);
                    jsonObject.put("status", 200);
                    jsonObject.put("id", projectFile.getId());
                    jsonObject.put("fileName", projectFile.getFileName());
                    jsonObject.put("size", projectFile.getSize());
                    jsonObject.put("type", projectFile.getType());
                    jsonObject.put("path", projectFile.getPath());
                    jsonObject.put("storeName", projectFile.getStoreName());
                    jsonObject.put("masterType", projectFile.getMasterType());
                    jsonObject.put("masterId", projectFile.getMasterId());
                    jsonObject.put("isDelete", projectFile.getIsDelete());
                    jsonObject.put("gmtCreate", projectFile.getGmtCreate());
                    jsonObject.put("gmtModify", projectFile.getGmtModify());
                    jsonObject.put("accessPath", constantProperties.getImgUrl()+"/"+projectFile.getMasterType()+ "/"+projectFile.getStoreName());
                    if (flag == 0) {
                        jsonObject.put("status", 501);
                        jsonObject.put("msg", "文件上传失败");
                    }
                } else {
                    jsonObject.put("status", 500);
                    jsonObject.put("msg", "文件已存在");
                }
                fileBatchList.add(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.error("上传失败！");
        }
        return ResponseUtils.success("上传成功！",fileBatchList);
    }

    @RequestMapping(value = "attachFileOneDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils attachFileOneDelete(@RequestBody ProjectFile projectFile) {
        int flag = 0;
        if(projectFile.getId() !=null && !"".equals(projectFile.getId())){
            flag = projectFileService.deleteIs(projectFile);
        }
        if (flag > 0) {
            return ResponseUtils.success("删除成功！");
        } else {
            return ResponseUtils.success("删除失败！");
        }
    }

    @RequestMapping(value = "attachFileDelete", method = RequestMethod.POST)
    @ResponseBody
    public Map attachFileDelete(@RequestBody ProjectFile projectFile) {
        Map map = new HashMap();
        int flag = projectFileService.deleteIs(projectFile);
        if (flag > 0) {
            map.put("status", 200);
        } else {
            map.put("status", 500);
            map.put("msg", "失败");
        }
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "attachFileDownLoad", method = RequestMethod.POST)
    public void attachFileDownLoad(@RequestBody ProjectFile projectFile, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        projectFile = projectFileService.selectByPrimaryKey(projectFile);
        String fileName = projectFile.getFileName();
        String filePath = projectFile.getPath();
        String path = filePath;
        File file = new File(path);

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream;charset=utf-8");

        try {
            if (!file.exists()) {
                logger.info("文件不存在");
                jsonObject.put("code", 20000);
                jsonObject.put("status", 500);
                jsonObject.put("data", "");
                jsonObject.put("msg", "文件不存在");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print(jsonObject.toJSONString());
            }
            response.addHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

            byte[] buffer = new byte[1024];
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
