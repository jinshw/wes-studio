package com.site.mountain.controller.nt;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtTrffassetChangeInfoMark;
import com.site.mountain.service.NtTrffassetChangeInfoMarkService;
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
@RequestMapping("/nt/changeInfoMark")
public class NtTrffassetChangeInfoMarkController {

    @Autowired
    NtTrffassetChangeInfoMarkService trffassetChangeInfoMarkService;

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils listByPage(@RequestBody NtTrffassetChangeInfoMark record) {
        PageInfo<NtTrffassetChangeInfoMark> dataList = trffassetChangeInfoMarkService.selectListByPage(record);
        return ResponseUtils.success(dataList.getTotal(),dataList);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getList(@RequestBody NtTrffassetChangeInfoMark record) {
        record.setPageSize(1000);
        List<NtTrffassetChangeInfoMark> dataList = trffassetChangeInfoMarkService.selectList(record);
        return ResponseUtils.success(dataList.size(),dataList);
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getChangeInfoMarkDataById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getChangeInfoMarkDataById(@RequestBody NtTrffassetChangeInfoMark record) {
        NtTrffassetChangeInfoMark data= trffassetChangeInfoMarkService.selectByPrimaryKey(record.getId());
        return ResponseUtils.success(data);
    }

    @ApiOperation(value = "添加数据", notes = "添加数据")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils add(@RequestBody NtTrffassetChangeInfoMark record) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        record.setOptTime(timestamp);
        record.setCreateTime(timestamp);
        record.setId(UUIDUtil.create32UUID());
        int addIndex = trffassetChangeInfoMarkService.insertSelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "修改数据", notes = "修改数据")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils update(@RequestBody NtTrffassetChangeInfoMark record) {
        int addIndex = trffassetChangeInfoMarkService.updateByPrimaryKeySelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "修改数据", notes = "修改数据")
    @RequestMapping(value = "verifyChangeInfoMarkCommit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils verifyChangeInfoMarkCommit(@RequestBody NtTrffassetChangeInfoMark record) {
        int addIndex = trffassetChangeInfoMarkService.updateByPrimaryKeySelective(record);
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }

    @ApiOperation(value = "删除数据", notes = "删除数据")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils delete(@RequestBody NtTrffassetChangeInfoMark record) {
        int addIndex = trffassetChangeInfoMarkService.updateIsDelByPrimaryKey(record.getId());
        if(addIndex > 0 ){
            return ResponseUtils.success("成功");
        }else{
            return ResponseUtils.error("失败");
        }
    }
}
