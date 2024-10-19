package com.site.mountain.controller.nt;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdAssetSection;
import com.site.mountain.entity.NtIbdAssetSectionDirection;
import com.site.mountain.service.NtIbdAssetSectionDirectionService;
import com.site.mountain.service.NtIbdAssetSectionService;
import com.site.mountain.utils.GeoPolygonUtils;
import com.site.mountain.utils.ResponseUtils;
import com.site.mountain.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/nt/ibdAssetSection")
public class NtIbdAssetSectionController {

    @Autowired
    NtIbdAssetSectionService ibdAssetSectionService;

    @Autowired
    NtIbdAssetSectionDirectionService assetSectionDirectionService;

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils listByPage(@RequestBody NtIbdAssetSection record) {
        PageInfo<NtIbdAssetSection> dataList = ibdAssetSectionService.selectListByPage(record);
        return ResponseUtils.success(dataList.getTotal(),dataList);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getList(@RequestBody NtIbdAssetSection record) {
        record.setPageSize(1000);
        List<NtIbdAssetSection> dataList = ibdAssetSectionService.selectList(record);
        return ResponseUtils.success(dataList.size(),dataList);
    }

    @ApiOperation(value = "添加数据", notes = "添加数据")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils add(@RequestBody NtIbdAssetSection record) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        record.setOptTime(timestamp);
        record.setCreateTime(timestamp);
        record.setSectionId(UUIDUtil.create32UUID());
        String polygon = record.getPolygon();
        if(!StringUtil.isEmpty(polygon) && polygon.contains("[[[")){
            JSONArray pointsArr = JSONArray.parseArray(polygon).getJSONArray(0);
            String point = GeoPolygonUtils.getCenterPoint(pointsArr);
            record.setPolygonCenter(point);
        }
        int addIndex0 = ibdAssetSectionService.insertSelective(record);
        for(NtIbdAssetSectionDirection entityOne:record.getDirections()){
            entityOne.setSectionId(record.getSectionId());
            String polygonSub = entityOne.getPolygon();
            if(!StringUtil.isEmpty(polygonSub) && polygonSub.contains("[[[")){
                JSONArray pointsArrSub = JSONArray.parseArray(polygonSub).getJSONArray(0);
                String pointSub = GeoPolygonUtils.getCenterPoint(pointsArrSub);
                entityOne.setPolygonCenter(pointSub);
            }
        }
        int addIndex = assetSectionDirectionService.replaceSelectiveBatch(record.getDirections());
        if(addIndex0>0 && addIndex > 0){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "修改数据", notes = "修改数据")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils update(@RequestBody NtIbdAssetSection record) {
        String polygon = record.getPolygon();
        if(!StringUtil.isEmpty(polygon) && polygon.contains("[[[")){
            JSONArray pointsArr = JSONArray.parseArray(polygon).getJSONArray(0);
            String point = GeoPolygonUtils.getCenterPoint(pointsArr);
            record.setPolygonCenter(point);
        }
        int addIndex0 = ibdAssetSectionService.updateByPrimaryKeySelective(record);
        for(NtIbdAssetSectionDirection entityOne:record.getDirections()){
            entityOne.setSectionId(record.getSectionId());
            String polygonSub = entityOne.getPolygon();
            if(!StringUtil.isEmpty(polygonSub) && polygonSub.contains("[[[")){
                JSONArray pointsArrSub = JSONArray.parseArray(polygonSub).getJSONArray(0);
                String pointSub = GeoPolygonUtils.getCenterPoint(pointsArrSub);
                entityOne.setPolygonCenter(pointSub);
            }
        }
        int addIndex = assetSectionDirectionService.replaceSelectiveBatch(record.getDirections());
        if(addIndex0>0 && addIndex > 0){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "删除数据", notes = "删除数据")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils delete(@RequestBody NtIbdAssetSection record) {
        int addIndex = ibdAssetSectionService.updateIsDelByPrimaryKey(record.getSectionId());
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }
}
