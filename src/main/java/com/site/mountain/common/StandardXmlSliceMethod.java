package com.site.mountain.common;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.utils.GisUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.locationtech.jts.geom.Coordinate;

import java.io.*;
import java.util.List;
import java.util.Map;

public class StandardXmlSliceMethod extends SliceMethod {
    public StandardXmlSliceMethod() {
    }

    @Override
    public JSONObject slice() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileType", "xml");
        jsonObject.put("fileData", "data");

        /**
         * 步骤：
         * 1、读取原始XML文件
         * 2、把原始XML文件结构化
         * 3、根据参数，筛选出来符合的node
         * 4、重新组合成XML对象
         * 5、把XML对象输出到文件中，并发布出去。
         */
        Map<String, String> param = getParam();
        String effectiveRadius = param.get("effectiveRadius") == null ? "0" : param.get("effectiveRadius");
        String lonStr = param.get("lon") == null ? "0" : param.get("lon");
        String latStr = param.get("lat") == null ? "0" : param.get("lat");
        Double lon = Double.valueOf(lonStr);
        Double lat = Double.valueOf(latStr);
        Coordinate c1 = new Coordinate(lon, lat);
        String sourceFilePath = param.get("sourceFilePath");
        String outFilePath = param.get("outFilePath");

//        String filePath = "D:\\项目\\地图服务\\数据\\101map-02.xml";
        SAXReader reader = new SAXReader();
        XMLWriter writer = null;
        Document document = null;
        try {
            document = reader.read(new File(sourceFilePath)); // 获取文档根节点
            Element rootElm = document.getRootElement();
            // 取得某个节点的子节点.
            Element mapFrameEle = rootElm.element("mapFrame");
            Element nodesEle = mapFrameEle.element("nodes");
            List<Element> nodes = nodesEle.elements();
            Coordinate c2 = new Coordinate();
            int index = 0;
            for (Element element : nodes) {
                String lonRef = element.element("refPos").element("long").getText();
                String latRef = element.element("refPos").element("lat").getText();
                String elevation = element.element("refPos").element("elevation").getText();
                Integer lonInt = Integer.parseInt(lonRef);
                Integer latInt = Integer.parseInt(latRef);
                Double latDouble = latInt / 10000000.0;
                Double lonDouble = lonInt / 10000000.0;
                c2.setX(lonDouble);
                c2.setY(latDouble);
                double pointDistance = GisUtils.getPointDistance(c1, c2);
                if (Double.valueOf(effectiveRadius) < (pointDistance * 1000)) {
                    nodesEle.remove(element);
                }
            }
            try {
                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setEncoding("UTF-8");
                writer = new XMLWriter(new FileOutputStream(outFilePath), format);
                writer.write(rootElm);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        jsonObject.put("outFilePath", outFilePath);
        return jsonObject;
    }
}
