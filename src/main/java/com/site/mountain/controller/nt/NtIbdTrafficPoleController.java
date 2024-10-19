package com.site.mountain.controller.nt;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdCrossingData;
import com.site.mountain.entity.NtIbdTrafficPole;
import com.site.mountain.service.NtIbdCrossingDataService;
import com.site.mountain.service.NtIbdTrafficPoleService;
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
@RequestMapping("/nt/ibdTrafficPole")
public class NtIbdTrafficPoleController {

    @Autowired
    NtIbdTrafficPoleService trafficPoleService;

    @Autowired
    NtIbdCrossingDataService crossingDataService;
    
    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils listByPage(@RequestBody NtIbdTrafficPole record) {
        PageInfo<NtIbdTrafficPole> dataList = trafficPoleService.selectListByPage(record);
        return ResponseUtils.success(dataList.getTotal(),dataList);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getList(@RequestBody NtIbdTrafficPole record) {
        record.setPageSize(1000);
        List<NtIbdTrafficPole> dataList = trafficPoleService.selectList(record);
        return ResponseUtils.success(dataList.size(),dataList);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getCrossingDataByPoleId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getCrossingDataByPoleId(@RequestBody NtIbdTrafficPole record) {
        NtIbdTrafficPole poleOne = trafficPoleService.selectByPrimaryKey(record.getPoleId());
        NtIbdCrossingData crossingData = crossingDataService.selectByPrimaryKey(poleOne.getCrossId());
        return ResponseUtils.success(crossingData);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getTrafficPoleDataById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getTrafficPoleDataById(@RequestBody NtIbdTrafficPole record) {
        NtIbdTrafficPole data= trafficPoleService.selectByPrimaryKey(record.getPoleId());
        return ResponseUtils.success(data);
    }

    @ApiOperation(value = "添加数据", notes = "添加数据")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils add(@RequestBody NtIbdTrafficPole record) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        record.setOptTime(timestamp);
        record.setCreateTime(timestamp);
        record.setPoleId(UUIDUtil.create32UUID());
        int addIndex = trafficPoleService.insertSelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "修改数据", notes = "修改数据")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils update(@RequestBody NtIbdTrafficPole record) {
        int addIndex = trafficPoleService.updateByPrimaryKeySelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "修改数据", notes = "修改数据")
    @RequestMapping(value = "verifyTrafficPoleCommit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils verifyTrafficPoleCommit(@RequestBody NtIbdTrafficPole record) {
        int addIndex = trafficPoleService.updateByPrimaryKeySelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "删除数据", notes = "删除数据")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils delete(@RequestBody NtIbdTrafficPole record) {
        int addIndex = trafficPoleService.updateIsDelByPrimaryKey(record.getPoleId());
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }
}
