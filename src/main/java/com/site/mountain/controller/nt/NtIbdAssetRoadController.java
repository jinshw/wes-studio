package com.site.mountain.controller.nt;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdAssetRoad;
import com.site.mountain.service.NtIbdAssetRoadService;
import com.site.mountain.utils.GeoPolygonUtils;
import com.site.mountain.utils.ResponseUtils;
import com.site.mountain.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/nt/ibdAssetRoad")
public class NtIbdAssetRoadController {

    @Autowired
    NtIbdAssetRoadService ibdAssetRoadService;

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils listByPage(@RequestBody NtIbdAssetRoad record) {
        PageInfo<NtIbdAssetRoad> dataList = ibdAssetRoadService.selectListByPage(record);
        return ResponseUtils.success(dataList.getTotal(),dataList);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getList(@RequestBody NtIbdAssetRoad record) {
        record.setPageSize(1000);
        List<NtIbdAssetRoad> dataList = ibdAssetRoadService.selectList(record);
        return ResponseUtils.success(dataList.size(),dataList);
    }

    @ApiOperation(value = "添加数据", notes = "添加数据")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils add(@RequestBody NtIbdAssetRoad record) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        record.setOptTime(timestamp);
        record.setCreateTime(timestamp);
        record.setRoadId(UUIDUtil.create32UUID());
        String polygon = record.getPolygon();
        if(!StringUtil.isEmpty(polygon) && polygon.contains("[[[")){
            JSONArray pointsArr = JSONArray.parseArray(polygon).getJSONArray(0);
            String point = GeoPolygonUtils.getCenterPoint(pointsArr);
            record.setPolygonCenter(point);
        }
        int addIndex = ibdAssetRoadService.insertSelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "修改数据", notes = "修改数据")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils update(@RequestBody NtIbdAssetRoad record) {
        String polygon = record.getPolygon();
        if(!StringUtil.isEmpty(polygon) && polygon.contains("[[[")){
            JSONArray pointsArr = JSONArray.parseArray(polygon).getJSONArray(0);
            String point = GeoPolygonUtils.getCenterPoint(pointsArr);
            record.setPolygonCenter(point);
        }
        int addIndex = ibdAssetRoadService.updateByPrimaryKeySelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "删除数据", notes = "删除数据")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils delete(@RequestBody NtIbdAssetRoad record) {
        int addIndex = ibdAssetRoadService.updateIsDelByPrimaryKey(record.getRoadId());
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }
    
}
