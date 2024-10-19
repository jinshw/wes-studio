package com.site.mountain.controller.nt;

import com.site.mountain.entity.NtIbdFactorData;
import com.site.mountain.service.NtIbdFactorDataService;
import com.site.mountain.sqlite.SqliteIbdResolveUtils;
import com.site.mountain.utils.MapToBeanUtils;
import com.site.mountain.utils.ResponseUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/nt/ibdFactorData")
public class NtIbdFactorDataController {

    @Autowired
    NtIbdFactorDataService ibdFactorDataService;

    @ApiOperation(value = "同步某表要素数据", notes = "同步某表要素数据")
    @RequestMapping(value = "/syncIbdFactorData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils syncIbdFactorBaseData(@RequestBody Map<String,Object> params) {
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        int inxSussNum = 0;
        int inxErrNum = 0;
        String reMsg = "";
        List<NtIbdFactorData> factorBaseList = new ArrayList<>();
        try{
            List<Map<String,Object>> ibdBizList = SqliteIbdResolveUtils.queryIbdBizList("","","");
            for(Map<String,Object> temp:ibdBizList){
                NtIbdFactorData fFactorBase = new NtIbdFactorData();
                MapToBeanUtils.mapToBean(temp,fFactorBase);
                Date date=format.parse(fFactorBase.getPid().toString().substring(0,14));
                fFactorBase.setGmtCreate(date);
                fFactorBase.setUserCreate("");
                factorBaseList.add(fFactorBase);
            }
            System.out.println(factorBaseList.size());
            int inx = 0;
            if(factorBaseList.size()>0){
                //inx= ibdFactorDataService.replactrueeSelectiveBatch(factorBaseList);
                for(NtIbdFactorData fbase:factorBaseList){
                    try{
                        inx = ibdFactorDataService.insertSelective(fbase);
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                        if(ex.getMessage().indexOf("duplicate key") >=0){
                            inx = ibdFactorDataService.updateByPrimaryKeySelective(fbase);
                        }
                    }
                    if(inx>0){
                        inxSussNum++;
                    }else{
                        inxErrNum++;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("失败！");
        }
        reMsg = "导入成功"+inxSussNum+"条，失败"+inxErrNum+"条。";
        return ResponseUtils.success(reMsg);
    }

    @ApiOperation(value = "查询ibd_log，修改数据", notes = "查询ibd_log，修改数据")
    @RequestMapping(value = "/changeIbdBizData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils changeIbdBizData(@RequestBody Map<String,Object> params) {
        String reMsg = "";
        try{
            ibdFactorDataService.changeIbdBizData();
            return ResponseUtils.success("成功!");
        }catch (Exception e){
            return ResponseUtils.error("失败!");
        }
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getList(@RequestBody NtIbdFactorData record) {
        List<NtIbdFactorData> factorDataList = ibdFactorDataService.selectList(record);
        return ResponseUtils.success(factorDataList.size(),factorDataList);
    }

}
