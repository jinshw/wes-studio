package com.site.mountain.dao.mysql;

import com.site.mountain.entity.ProjectFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectFileDao {

    int insert(@Param("pojo") ProjectFile projectFile);

    int update(@Param("pojo") ProjectFile projectFile);

    int delete(@Param("pojo") ProjectFile pojo);

    int deleteIs(@Param("pojo") ProjectFile pojo);

    List<ProjectFile> findListByMasterId(ProjectFile ProjectFile);

    ProjectFile selectByPrimaryKey(@Param("pojo") ProjectFile ProjectFile);


}