package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.site.mountain.utils.*;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;

@Controller
@RequestMapping("/test")
@ResponseBody
public class TestController {


    @RequestMapping(value = "getzlibmap", method = RequestMethod.POST)
    public JSONObject getZlibMap(@RequestBody JSONObject paramObj, HttpServletRequest request, HttpResponse httpResponse) {
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("status", 200);

        int blockSize = 1300;

        // 1、获取地图文件
        String filePath = "D:\\workspace\\golang\\mec_server\\testfile\\output.json";
        String outPath = "D:\\zip4j_test";

        // 2、使用zlib压缩文件
        byte[] mapDataTxt = FileUtils.file2byte(filePath);

        byte[] compressBytes = ZlibUtils.compress(mapDataTxt);

        outPath = outPath + "\\" + MD5Util.getMD5Byte(mapDataTxt);

        // 3、切分文件
        int rawBlockSize = (blockSize * 70) / 100;
        int blockNum = compressBytes.length / rawBlockSize;

//        byte[] sectionIdBytes = new byte[8];
//        sectionIdBytes[0] = 1;
        // ByteBuffer 默认为大端存储
        ByteBuffer sectionIdBytes = ByteBuffer.wrap(new byte[8]);
        sectionIdBytes.asLongBuffer().put(1);


        ByteBuffer fileLenBytes = ByteBuffer.wrap(new byte[4]);
        fileLenBytes.asIntBuffer().put(mapDataTxt.length);
//        fileLenBytes[0] = (byte) mapDataTxt.length;

        JSONArray blocks = new JSONArray();
        StringBuffer wholeBuf = new StringBuffer();
        for (int i = 0; i <= blockNum; i++) {
            int start = i * rawBlockSize;
            int end = (i + 1) * rawBlockSize;
            byte[] content = new byte[2];
            content[0] = (byte) i;
            content[1] = (byte) (blockNum + 1);

            if (i == 0) {
                content = CustomArrayUtils.byteMerger(sectionIdBytes.array(), CustomArrayUtils.byteMerger(content, fileLenBytes.array()));
            } else {
                content = CustomArrayUtils.byteMerger(sectionIdBytes.array(), content);
            }
            if (i == blockNum) {
//                content = CustomArrayUtils.getBytesByIndexToLastIndex(compressBytes, start);
                content = CustomArrayUtils.byteMerger(content, CustomArrayUtils.getBytesByIndexToLastIndex(compressBytes, start));
            } else {
//                content = CustomArrayUtils.getBytesByIndex(compressBytes, start, end);
                content = CustomArrayUtils.byteMerger(content, CustomArrayUtils.getBytesByIndex(compressBytes, start, end));
            }

            // 4、对切分后的文件进行base64转码
//            byte[] encodeBuf = CustomBase64Utils.base64Encoder(content);
            String encodeStr = CustomBase64Utils.base64EncoderToString(content);

            // 5、生成切分转后的文件
            FileUtils.mkdirs(outPath);
            String fulllPath = outPath + "\\" + "sharding-" + String.valueOf(i + 1) + ".dat";
            try {
                FileUtils.writeByteToFile(encodeStr.getBytes(), fulllPath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject tempJson = new JSONObject();
            tempJson.put("block", encodeStr);
            blocks.add(tempJson);
            wholeBuf.append(encodeStr);
        }


        // 6、返回数据
        jsonObject.put("section_id", "1");
        jsonObject.put("block_num", blockNum + 1);
        jsonObject.put("section_map", wholeBuf.toString());
        jsonObject.put("HDMap_list", blocks);
        jsonObject.put("error_code", "");
        jsonObject.put("error_msg", "");


        return jsonObject;
    }

    @RequestMapping(value = "getzlib", method = RequestMethod.POST)
    public JSONObject getZlib(HttpServletRequest request, HttpResponse httpResponse) {
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("status", 200);

        int blockSize = 1300;

        // 1、获取地图文件
        String filePath = "D:\\workspace\\golang\\mec_server\\testfile\\output.json";
        String outPath = "D:\\zip4j_test";

        // 2、使用zlib压缩文件
        byte[] mapDataTxt = FileUtils.file2byte(filePath);
        byte[] compressBytes = ZlibUtils.compress(mapDataTxt);


        // 3、切分文件
        int rawBlockSize = (blockSize * 70) / 100;
        int blockNum = compressBytes.length / rawBlockSize;


        JSONArray blocks = new JSONArray();
        StringBuffer wholeBuf = new StringBuffer();
        byte[] wholeBufByte = new byte[0];

        for (int i = 0; i <= blockNum; i++) {
            int start = i * rawBlockSize;
            int end = (i + 1) * rawBlockSize;
            byte[] content = new byte[2];
            content[0] = (byte) i;
            content[1] = (byte) (blockNum + 1);

            if (i == blockNum) {
                content = CustomArrayUtils.getBytesByIndexToLastIndex(compressBytes, start);
            } else {
                content = CustomArrayUtils.getBytesByIndex(compressBytes, start, end);
            }

            // 4、对切分后的文件进行base64转码
            byte[] encodeBuf = CustomBase64Utils.base64Encoder(content);
            byte[] bytes = CustomBase64Utils.base64DecodeToBytes(encodeBuf);
//            wholeBuf.append(bytes);
            wholeBufByte = CustomArrayUtils.byteMerger(wholeBufByte, bytes);
        }


        // base64转码
//        byte[] encodeBuf = CustomBase64Utils.base64Encoder(mapDataTxt);

        // base64解码
//        byte[] blockBytes = CustomBase64Utils.base64DecodeToBytes(wholeBuf.toString().getBytes());
//        byte[] blockBytes = base64DecodeToBytes(wholeBuf.toString().getBytes());

//        byte[] compressBytes = ZlibUtils.compress(blockBytes);


        // 解压
        outPath = outPath + "\\" + "decompress.json";
        byte[] decompress = ZlibUtils.decompress(wholeBufByte);
        try {
            FileUtils.writeByteToFile(decompress, outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回数据
        jsonObject.put("compressBytes", compressBytes);

        return jsonObject;
    }

    @RequestMapping(value = "/zlibbase64")
    public JSONObject processZlibBase64(@RequestBody JSONObject paramObj, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 2000);
        JSONArray hdMapList = paramObj.getJSONArray("HDMap_list");
        String blockStr = "";
        byte[] blockBytes = null;
        JSONObject blockJsonObj = new JSONObject();
        StringBuffer resultStringBuffer = new StringBuffer();
        byte[] zipData = new byte[0];
        int mapDataLength = 0;
        for (int i = 0; i < hdMapList.size(); i++) {
            blockJsonObj = hdMapList.getJSONObject(i);
            blockStr = blockJsonObj.getString("block");
//            byte[] blockStrByte = blockStr.getBytes();
//            blockBytes = base64DecodeToBytes(blockStrByte);
            blockBytes = CustomBase64Utils.base64DecodeToBytes(blockStr);
            byte[] bytesByIndex = getBytesByIndex(blockBytes, 0, 8);

            int sid = byteArrayToIntIgnoreSign(bytesByIndex);
//            int sid = byteArrayToIntIgnoreSign(getBytesByIndex(blockBytes, 0, 8));
            int blockTotal = blockBytes[9];
            int blockIndex = blockBytes[8];
            if (blockIndex == 0) {
                mapDataLength = byteArrayToIntIgnoreSign(getBytesByIndex(blockBytes, 10, 14));
//                zipData = byteMerger(zipData, blockBytes);
                zipData = byteMerger(zipData, getBytesByIndex(blockBytes, 14, blockBytes.length));
            } else {
//                zipData = byteMerger(zipData, blockBytes);
                zipData = byteMerger(zipData, getBytesByIndex(blockBytes, 10, blockBytes.length));
            }

        }
        blockBytes.clone();

        // 解压
        ByteArrayInputStream bais = new ByteArrayInputStream(zipData);
        InflaterInputStream iis = new InflaterInputStream(bais);
        String result = "";
        StringBuffer stringBuffer = new StringBuffer();

        byte[] buf = new byte[1024];
        int rlen = -1;
        try {
            while ((rlen = iis.read(buf)) != -1) {
                stringBuffer.append(new String(Arrays.copyOf(buf, rlen)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] decompress = stringBuffer.toString().getBytes();

        //测试文件
        String filename = "D:/zip4j_test/zlib0310.json";
        File file = new File(filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(decompress);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @RequestMapping(value = "/zlib")
    public JSONObject processZip(@RequestBody JSONObject paramObj, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 2000);
        JSONArray hdMapList = paramObj.getJSONArray("HDMap_list");
        String blockStr = "";
        byte[] blockBytes = null;
        JSONObject blockJsonObj = new JSONObject();
        StringBuffer resultStringBuffer = new StringBuffer();
        byte[] zipData = new byte[0];
        int mapDataLength = 0;
        for (int i = 0; i < hdMapList.size(); i++) {
            blockJsonObj = hdMapList.getJSONObject(i);
            blockStr = blockJsonObj.getString("block");
            byte[] blockStrByte = blockStr.getBytes();
            blockBytes = base64DecodeToBytes(blockStrByte);

//            BASE64Decoder bd = new BASE64Decoder();
//            try {
//                blockBytes = bd.decodeBuffer(blockStr);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            int sid = byteArrayToIntIgnoreSign(getBytesByIndex(blockBytes, 0, 8));
            int blockTotal = blockBytes[9];
            int blockIndex = blockBytes[8];


            if (blockIndex == 0) {
                mapDataLength = byteArrayToIntIgnoreSign(getBytesByIndex(blockBytes, 10, 14));
                zipData = byteMerger(zipData, getBytesByIndex(blockBytes, 14, blockBytes.length));
                // zipData0 = oneBlock[14:]
            } else {
                // zipData = append(zipData, oneBlock[10:len(oneBlock)-1]...)
                zipData = byteMerger(zipData, getBytesByIndex(blockBytes, 10, blockBytes.length));
                // zipData0 = append(zipDataNil, oneBlock[10:920]...)
            }

//            zipData = byteMerger(zipData, blockBytes);
        }
        blockBytes.clone();

        // 解压
        ByteArrayInputStream bais = new ByteArrayInputStream(zipData);
//        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        InflaterInputStream iis = new InflaterInputStream(bais);
        String result = "";
        StringBuffer stringBuffer = new StringBuffer();

        byte[] buf = new byte[1024];
        int rlen = -1;
        try {
            while ((rlen = iis.read(buf)) != -1) {
//                result += new String(Arrays.copyOf(buf, rlen));
                stringBuffer.append(new String(Arrays.copyOf(buf, rlen)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        buf.clone();

//        byte[] decompress = result.getBytes();
        byte[] decompress = stringBuffer.toString().getBytes();

//        byte[] decompress = compressStrToBytes(zipData);
//        byte[] decompress = decompress(zipData);
//        byte[] decompress = decompressData(zipData);
        //测试文件
        String filename = "D:/zip4j_test/zlib.json";
        File file = new File(filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(decompress);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return jsonObject;
    }

    public static byte[] base64DecodeToBytes(byte[] encodedText) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            return decoder.decode(encodedText);
//            String text = new String(decoder.decode(encodedText), "UTF-8");
            //System.out.println(text);
//            return text.getBytes();
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return null;
    }

    // 使用两个 for 语句
    //java 合并两个byte数组
    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        int i = 0;
        for (byte bt : bt1) {
            bt3[i] = bt;
            i++;
        }

        for (byte bt : bt2) {
            bt3[i] = bt;
            i++;
        }
        return bt3;
    }

    public static byte[] decompressData(byte[] endata) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        InflaterOutputStream zos = new InflaterOutputStream(bos);
        try {
            zos.write(endata);
            zos.close();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] decompress(byte[] data) {
        byte[] output = new byte[0];
        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);
        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);

        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        decompresser.end();
        return output;
    }

    public static int byteArrayToInt(byte[] bytes) {
        int res = 0;
        for (int i = 0; i < bytes.length; i++) {
            res += (bytes[i] & 0xff) << i * 8;
        }
        return res;
    }

    public static int byteArrayToIntReverse(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value += (bytes[i] & 0xff) << (3 - i) * 8;
        }
        return value;
    }

    //采用循环累加也行
    public static int byteArrayToIntIgnoreSign(byte[] bytes) {
        return (bytes[0] & 0xff)
                + ((bytes[1] & 0xff) << 8)
                + ((bytes[2] & 0xff) << 16)
                + ((bytes[3] & 0xff) << 24);
    }

    public static byte[] getBytesByIndex(byte[] srcBytes, int startIndex, int endIndex) {
        byte[] resultByte = new byte[endIndex - startIndex];
        int index = 0;
        for (int i = 0; i < srcBytes.length; i++) {
            if (i >= startIndex && i < endIndex) {
                resultByte[index] = srcBytes[i];
                index = index + 1;
            }
        }
        return resultByte;
    }

    public static byte[] compressStrToBytes(byte[] str) {
        byte[] outinput = new byte[0];

        Inflater decompresser = new Inflater(true); //设置为Ture
        decompresser.reset();
        decompresser.setInput(str);

        ByteArrayOutputStream out = new ByteArrayOutputStream(str.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                out.write(buf, 0, i);
            }
            outinput = out.toByteArray();
        } catch (Exception e) {
            outinput = str;
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        decompresser.end();
//        return new String(outinput);
        return outinput;
    }


}


