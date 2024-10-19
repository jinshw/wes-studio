package com.site.mountain.controller.map;

import com.site.mountain.entity.Alarm;
import com.site.mountain.service.AlarmServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

    @Autowired
    private AlarmServer alarmServer;


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public List<Alarm> selectAll() throws UnsupportedEncodingException {
        List<Alarm> alarms = alarmServer.selectAll(new Alarm());
//        for(Alarm alarmTemp : alarms){
//            byte[] point = alarmTemp.getPoint();
//            String pStrGBK = new String(point,"GBK");
//            String pStrUTF8 = new String(point,"UTF-8");
//            String pStrISO = new String(point,"ISO8859-1");
//            System.out.println(pStrGBK);
//            System.out.println(pStrUTF8);
//            System.out.println(pStrISO);
//        }
        return alarms;
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public int insert(@RequestBody Alarm alarm){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        alarm.setCreateTimestamp(timestamp);
        alarm.setModifyTimestamp(timestamp);
        alarm.setTime(timestamp);
        int result = alarmServer.insert(alarm);
        return result;
    }

}
