package com.site.mountain.controller.nt;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdCrossingData;
import com.site.mountain.service.NtIbdCrossingDataService;
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

@CrossOrigin
@Controller
@RequestMapping("/nt/ibdCrossingData")
public class NtIbdCrossingDataController {

    @Autowired
    NtIbdCrossingDataService ibdCrossingDataService;

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils listByPage(@RequestBody NtIbdCrossingData record) {
        PageInfo<NtIbdCrossingData> dataList = ibdCrossingDataService.selectListByPage(record);
        return ResponseUtils.success(dataList.getTotal(),dataList);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getList(@RequestBody NtIbdCrossingData record) {
        record.setPageSize(1000);
        List<NtIbdCrossingData> dataList = ibdCrossingDataService.selectList(record);
        return ResponseUtils.success(dataList.size(),dataList);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getCrossingDataById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getCrossingDataById(@RequestBody NtIbdCrossingData record) {
        NtIbdCrossingData data= ibdCrossingDataService.selectByPrimaryKey(record.getCrossId());
        return ResponseUtils.success(data);
    }

    @ApiOperation(value = "添加数据", notes = "添加数据")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils add(@RequestBody NtIbdCrossingData record) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        record.setOptTime(timestamp);
        record.setCreateTime(timestamp);
        record.setCrossId(UUIDUtil.create32UUID());
        String polygon = record.getPolygon();
        if(!StringUtil.isEmpty(polygon) && polygon.contains("[[[")){
            JSONArray pointsArr = JSONArray.parseArray(polygon).getJSONArray(0);
            String point = GeoPolygonUtils.getCenterPoint(pointsArr);
            record.setPolygonCenter(point);
        }
        int addIndex = ibdCrossingDataService.insertSelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "修改数据", notes = "修改数据")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils update(@RequestBody NtIbdCrossingData record) {
        String polygon = record.getPolygon();
        if(!StringUtil.isEmpty(polygon) && polygon.contains("[[[")){
            JSONArray pointsArr = JSONArray.parseArray(polygon).getJSONArray(0);
            String point = GeoPolygonUtils.getCenterPoint(pointsArr);
            record.setPolygonCenter(point);
        }
        int addIndex = ibdCrossingDataService.updateByPrimaryKeySelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "删除数据", notes = "删除数据")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils delete(@RequestBody NtIbdCrossingData record) {
        int addIndex = ibdCrossingDataService.updateIsDelByPrimaryKey(record.getCrossId());
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }
}
