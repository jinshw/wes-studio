package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.Map3dtiles;

public interface Map3dtilesService {

    PageInfo<Map3dtiles> selectAll(Map3dtiles map3dtiles);

    int add(Map3dtiles map3dtiles);

    int delete(Map3dtiles map3dtiles);

    int update(Map3dtiles map3dtiles);
}
