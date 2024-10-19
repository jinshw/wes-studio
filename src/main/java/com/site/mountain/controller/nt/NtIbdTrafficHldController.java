package com.site.mountain.controller.nt;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdTrafficHld;
import com.site.mountain.service.NtIbdTrafficHldService;
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
@RequestMapping("/nt/ibdTrafficHld")
public class NtIbdTrafficHldController {

    @Autowired
    NtIbdTrafficHldService trafficHldService;

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils listByPage(@RequestBody NtIbdTrafficHld record) {
        PageInfo<NtIbdTrafficHld> dataList = trafficHldService.selectListByPage(record);
        return ResponseUtils.success(dataList.getTotal(),dataList);
    }

    @ApiOperation(value = "添加数据", notes = "添加数据")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils add(@RequestBody NtIbdTrafficHld record) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        record.setOptTime(timestamp);
        record.setCreateTime(timestamp);
        record.setLightId(UUIDUtil.create32UUID());
        int addIndex = trafficHldService.insertSelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "修改数据", notes = "修改数据")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils update(@RequestBody NtIbdTrafficHld record) {
        int addIndex = trafficHldService.updateByPrimaryKeySelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "删除数据", notes = "删除数据")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils delete(@RequestBody NtIbdTrafficHld record) {
        int addIndex = trafficHldService.updateIsDelByPrimaryKey(record.getLightId());
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }
}
