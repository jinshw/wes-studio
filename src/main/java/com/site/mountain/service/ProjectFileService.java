package com.site.mountain.service;

import com.site.mountain.entity.ProjectFile;

import java.util.List;

public interface ProjectFileService {

    public int insert(ProjectFile projectFile);

    public int delete(ProjectFile pojo);

    public int deleteIs(ProjectFile pojo);

    public List<ProjectFile> findListByMasterId(ProjectFile projectFile);

    public int update(ProjectFile projectFile);

    public ProjectFile selectByPrimaryKey(ProjectFile projectFile);
}
