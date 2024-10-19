package com.site.mountain.controller.sys;

import com.site.mountain.entity.SysDictionary;
import com.site.mountain.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysdict")
public class SysDictionaryController {

    @Autowired
    public SysDictionaryService sysDictionaryService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map findList(@RequestBody SysDictionary sysDictionary) {
        Map map = new HashMap();
        List list = sysDictionaryService.findList(sysDictionary);
        map.put("code", 20000);
        map.put("data", list);
        return map;
    }

    @RequestMapping(value = "dictGroupByGroupName", method = RequestMethod.POST)
    @ResponseBody
    public Map dictGroupBy( @RequestBody Map<String,Object> params) {//List<String> GroupNames
        List<String> groupNamesList = new ArrayList<>();
        if (null != params.get("groupNames") && !"".equals(params.get("groupNames"))) {
            groupNamesList = (List<String>) params.get("groupNames");
        }

        Map map = new HashMap();
        List<SysDictionary> list = sysDictionaryService.findList(new SysDictionary());
        Map<String,List<Map<String,Object>>> reMap = new HashMap();
        for(int j=0;j<list.size();j++){
            SysDictionary entity = list.get(j);
            if(reMap.containsKey(entity.getDictGroupName())){
                List<Map<String,Object>> tempList = reMap.get(entity.getDictGroupName());
                Map<String,Object> tempMap= new HashMap<>();
                tempMap.put("code",Integer.valueOf(entity.getDictValue()));
                tempMap.put("name",entity.getDictName());
                tempList.add(tempMap);
            }else{
                List<Map<String,Object>> tempList = new ArrayList<>();
                Map<String,Object> tempMap= new HashMap<>();
                tempMap.put("code",Integer.valueOf(entity.getDictValue()));
                tempMap.put("name",entity.getDictName());
                tempList.add(tempMap);
                reMap.put(entity.getDictGroupName(),tempList);
            }
        }
        if(groupNamesList.size()>0){
            Map reMapParam = new HashMap();
            for(int t=0;t<groupNamesList.size();t++){
                String key = groupNamesList.get(t);
                if(reMap.containsKey(key)){
                    reMapParam.put(key,reMap.get(key));
                }
            }
            map.put("code", 20000);
            map.put("data", reMapParam);
        }else{
            map.put("code", 20000);
            map.put("data", reMap);
        }
        return map;
    }

    @RequestMapping(value = "dictGroupByGroupNameAll", method = RequestMethod.POST)
    @ResponseBody
    public Map dictGroupByGroupNameAll(@RequestBody SysDictionary sysDictionary) {
        Map map = new HashMap();
        List<SysDictionary> list = sysDictionaryService.findList(sysDictionary);
        Map<String,List<Map<String,Object>>> reMap = new HashMap();
        for(int j=0;j<list.size();j++){
            SysDictionary entity = list.get(j);
            if(reMap.containsKey(entity.getDictGroupName())){
                List<Map<String,Object>> tempList = reMap.get(entity.getDictGroupName());
                Map<String,Object> tempMap= new HashMap<>();
                tempMap.put("code",Integer.valueOf(entity.getDictValue()));
                tempMap.put("name",entity.getDictName());
                tempList.add(tempMap);
            }else{
                List<Map<String,Object>> tempList = new ArrayList<>();
                Map<String,Object> tempMap= new HashMap<>();
                tempMap.put("code",Integer.valueOf(entity.getDictValue()));
                tempMap.put("name",entity.getDictName());
                tempList.add(tempMap);
                reMap.put(entity.getDictGroupName(),tempList);
            }
        }
        map.put("code", 20000);
        map.put("data", reMap);
        return map;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map insert(@RequestBody SysDictionary sysDictionary) {
        Map map = new HashMap();
        int flag = sysDictionaryService.insert(sysDictionary);
        if (flag > 0) {
            map.put("status", 30000);
        } else {
            map.put("status", 30001);
        }
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Map edit(@RequestBody SysDictionary sysDictionary) {
        Map map = new HashMap();
        int flag = sysDictionaryService.update(sysDictionary);
        if (flag > 0) {
            map.put("status", 30000);
        } else {
            map.put("status", 30001);
        }
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map delete(@RequestBody SysDictionary sysDictionary) {
        Map map = new HashMap();
        int flag = sysDictionaryService.delete(sysDictionary);
        if (flag > 0) {
            map.put("status", 30000);
        } else {
            map.put("status", 30001);
        }
        map.put("code", 20000);
        return map;
    }

}
