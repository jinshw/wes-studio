package com.site.mountain.controller.nt;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtHikCameras;
import com.site.mountain.service.NtHikCamerasService;
import com.site.mountain.utils.ResponseUtils;
import com.site.mountain.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/nt/cameras")
public class NtHikCamerasController {

    @Autowired
    NtHikCamerasService camerasService;

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils listByPage(@RequestBody NtHikCameras record) {
        PageInfo<NtHikCameras> dataList = camerasService.selectListByPage(record);
        return ResponseUtils.success(dataList.getTotal(),dataList);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getList(@RequestBody NtHikCameras record) {
        List<NtHikCameras> dataList = camerasService.selectList(record);
        return ResponseUtils.success(dataList.size(),dataList);
    }

    @ApiOperation(value = "添加数据", notes = "添加数据")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils add(@RequestBody NtHikCameras record) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        record.setInstallationTime(timestamp);
        record.setCameraIndexCode(UUIDUtil.create32UUID());
        int addIndex = camerasService.insertSelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "修改数据", notes = "修改数据")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils update(@RequestBody NtHikCameras record) {
        int addIndex = camerasService.updateByPrimaryKeySelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "删除数据", notes = "删除数据")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils delete(@RequestBody NtHikCameras record) {
        int addIndex = camerasService.updateIsDelByPrimaryKey(record.getCameraIndexCode());
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "审批数据", notes = "审批数据")
    @RequestMapping(value = "verifyAssetCamerasCommit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils verifyAssetCamerasCommit(@RequestBody NtHikCameras record) {
        int addIndex = camerasService.updateByPrimaryKeySelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }
}
