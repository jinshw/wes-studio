package com.site.mountain.service.impl;

import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.dao.mysql.ProjectFileDao;
import com.site.mountain.entity.ProjectFile;
import com.site.mountain.service.ProjectFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectFileServiceImpl implements ProjectFileService {

    @Autowired
    public ProjectFileDao projectFileDao;

    @Autowired
    ConstantProperties constantProperties;

    @Override
    public int insert(ProjectFile projectFile){
        return projectFileDao.insert(projectFile);
    }

    @Override
    public ProjectFile selectByPrimaryKey(ProjectFile projectFile){
        return projectFileDao.selectByPrimaryKey(projectFile);
    }

    @Override
    public int delete(ProjectFile pojo){
        return projectFileDao.delete(pojo);
    }

    @Override
    public int deleteIs(ProjectFile pojo){
        return projectFileDao.deleteIs(pojo);
    }

    @Override
    public List<ProjectFile> findListByMasterId(ProjectFile projectFile) {
        List<ProjectFile> fileList = projectFileDao.findListByMasterId(projectFile);
        for(int k=0;k<fileList.size();k++){
            ProjectFile tFile = fileList.get(k);
            tFile.setAccessPath(constantProperties.getImgUrl()+"/"+tFile.getMasterType()+ "/"+tFile.getStoreName());
            fileList.set(k,tFile);
        }
        return fileList;
    }

    @Override
    public int update(ProjectFile projectFile) {
        return projectFileDao.update(projectFile);
    }

}
