package com.site.mountain.dll;

import com.sun.jna.Platform;

public class MainTest {
    public static void main(String[] args) {
        //System.out.println(System.getProperty("user.dir"));

        /*System.out.println(Platform.isWindows());
        int value = MyDll.mydll.add(1, 1);
        System.out.println("value = " + value);*/

       /* String filePath = "C:\\Users\\Administrator\\Desktop\\obj_test\\xodr_out\\test1211.xodr";
        String fileName = "test1211";
        String outFolderPath = "C:\\Users\\Administrator\\Desktop\\obj_test\\xodr_out\\out";
        String userDir = System.getProperty("user.dir");
        String strtextName = userDir + "/dll/texture";

        String outPath = GenerateObjFromXodr.generateObjFromXodr.GenerateObjModelByXodr(filePath,fileName,outFolderPath,strtextName);

        System.out.println(outPath);*/

//        String filePath = "C:\\Users\\DELL\\Desktop\\GeoJsonToXmlDLL\\1663570700852252494.geojson";
//        boolean result = GeoJsontoXml.geoJsontoXml.fnTestDll("123");
//        boolean result = GeoJsontoXml.geoJsontoXml.generateXmlByGeoJson(filePath);
//        System.out.println(result);

//        String xmlPath = "C:/Users/DELL/Desktop/GeoJsonToXmlDLL/1663570700852252494.xml";
//        boolean xmlToXodr = XmlToXodr.xmlToXodr.GeneXodr16Border(xmlPath);
//        System.out.println(xmlToXodr);
//        boolean add = MathLibrary.mathLibrary.Add(4, 9);
//        System.out.println(add);


//        DeleteFile.deleteFile.DeleteFileA("C:/Users/DELL/Desktop/test.txt");

//        String strLonLat = GISReferenceCoord.gisReferenceCoord.strXYToLatLon(5751.169999999, 10286.029999999);
//        System.out.println("strLonLat="+strLonLat);

        String ff = "{\n" +
                "    \"LedColorType\": \"2\",\n" +
                "    \"LedWidth\": \"320\",\n" +
                "    \"LedHeight\": \"64\",\n" +
                "    \"ProgramID\": \"1100\",\n" +
                "    \"ProgramSum\": \"2\",\n" +
                "    \"ProgramNo1\": \n" +
                "        {\n" +
                "           \n" +
                "            \"ChangeInterval\": \"0\",\n" +
                "            \"AreaSum\": \"2\",\n" +
                "            \"AreaNo1\": \n" +
                "                {\n" +
                "                    \"AreaType\": \"1\",\n" +
                "                    \"PosX\": \"0\",\n" +
                "                    \"PosY\": \"0\",\n" +
                "                    \"Width\": \"320\",\n" +
                "                    \"Height\": \"16\",\n" +
                "                    \"ObjSum\": \"1\",\n" +
                "                    \"Obj\": {\n" +
                "                        \"ObjNo\": \"1\",\n" +
                "                        \"InType\": \"1\",\n" +
                "                        \"TimeLen\": \"5\",\n" +
                "                        \"Speed\": \"1\",\n" +
                "                        \"FontColor\": \"255255000\",\n" +
                "                        \"BgColor\": \"000000000\",\n" +
                "                        \"Transparent\": \"100\",\n" +
                "                        \"FontSize\": \"16\",\n" +
                "                        \"FontStyle\": \"宋体\",\n" +
                "                        \"FontExtra\": \"2\",\n" +
                "                        \"AlignMode\": \"左对齐\",\n" +
                "                        \"Content\": \"谨慎驾驶，一路平安\"\n" +
                "                    }\n" +
                "                },\n" +
                "                \n" +
                "                    \"AreaNo2\": \n" +
                "{\n" +
                "                    \"AreaType\": \"2\",\n" +
                "                    \"PosX\": \"0\",\n" +
                "                    \"PosY\": \"16\",\n" +
                "                    \"Width\": \"32\",\n" +
                "                    \"Height\": \"48\",\n" +
                "                    \"ObjSum\": \"1\",\n" +
                "                    \"Obj\": {\n" +
                "                        \"ObjNo\": \"1\",\n" +
                "                        \"InType\": \"1\",\n" +
                "                        \"TimeLen\": \"5\",\n" +
                "                        \"Speed\": \"1\",\n" +
                "                        \"PicType\": \"0\",\n" +
                "                        \"DisplayMode\": \"平铺\",\n" +
                "                        \"Content\": \"002.bmp\"\n" +
                "                    }\n" +
                "                }\n" +
                "            \n" +
                "        },\n" +
                " \"ProgramNo2\": \n" +
                "        {\n" +
                "           \n" +
                "            \"ChangeInterval\": \"0\",\n" +
                "            \"AreaSum\": \"2\",\n" +
                "            \"AreaNo1\": \n" +
                "                {\n" +
                "                   \n" +
                "                    \"AreaType\": \"1\",\n" +
                "                    \"PosX\": \"0\",\n" +
                "                    \"PosY\": \"0\",\n" +
                "                    \"Width\": \"320\",\n" +
                "                    \"Height\": \"16\",\n" +
                "                    \"ObjSum\": \"1\",\n" +
                "                    \"Obj\": {\n" +
                "                        \"ObjNo\": \"1\",\n" +
                "                        \"InType\": \"1\",\n" +
                "                        \"TimeLen\": \"5\",\n" +
                "                        \"Speed\": \"1\",\n" +
                "                        \"FontColor\": \"255255000\",\n" +
                "                        \"BgColor\": \"000000000\",\n" +
                "                        \"Transparent\": \"100\",\n" +
                "                        \"FontSize\": \"16\",\n" +
                "                        \"FontStyle\": \"宋体\",\n" +
                "                        \"FontExtra\": \"2\",\n" +
                "                        \"AlignMode\": \"左对齐\",\n" +
                "                        \"Content\": \"注意安全旅途愉快\"\n" +
                "                    }\n" +
                "                },\n" +
                "\"AreaNo2\": \n" +
                "                {\n" +
                "                    \n" +
                "                    \"AreaType\": \"2\",\n" +
                "                    \"PosX\": \"0\",\n" +
                "                    \"PosY\": \"16\",\n" +
                "                    \"Width\": \"32\",\n" +
                "                    \"Height\": \"48\",\n" +
                "                    \"ObjSum\": \"1\",\n" +
                "                    \"Obj\": {\n" +
                "                        \"ObjNo\": \"1\",\n" +
                "                        \"InType\": \"1\",\n" +
                "                        \"TimeLen\": \"5\",\n" +
                "                        \"Speed\": \"1\",\n" +
                "                        \"PicType\": \"0\",\n" +
                "                        \"DisplayMode\": \"平铺\",\n" +
                "                        \"Content\": \"112.bmp\"\n" +
                "                    }\n" +
                "                }\n" +
                "            \n" +
                "        }\n" +
                "    \n" +
                "}\n";

    }
}
