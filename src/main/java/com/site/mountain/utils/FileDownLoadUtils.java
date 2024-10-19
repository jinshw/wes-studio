package com.site.mountain.utils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class FileDownLoadUtils {
    public static void main(String[] args) {
        //指定URL
        //String url = "https://mirrors.aliyun.com/centos/2/centos2-scripts-v1.tar";
        String url = "http://cdn.aerohuanyou.com//ota/1709803191971/map2023Q1_D07120.zip/data/ADASRoute.dat";
        //下载文件后的本地存储路径
        String downLoadFilePath = "D:\\test202407";
        long startByte = 0;
        //下载文件
        Date startDate = new Date();
        downLoadFile(url, downLoadFilePath);
        Date endDate = new Date();
        System.out.println("执行完成，执行时间:" + (endDate.getTime() - startDate.getTime()) + "毫秒");
    }

    /**
     * 读取目录地址，创建临时文件
     * @param url              文件下载地址
     * @param downLoadFilePath 下载文件本地文件夹存储路径
     */
    public static void downLoadFile(String url, String downLoadFilePath) {
        //临时文件后缀名
        String temp = ".temp";
        try {
            //获取文件路径
            URL uri = new URL(url);
            String filePath = uri.getFile();

            //指定本地下载的目录地址
            File file = new File(downLoadFilePath);
            //生成一个临时文件(目的：主要是记录数据的下标位置(字节长度))
            String tempFilePath = file.getAbsolutePath() + File.separator + filePath.substring(filePath.lastIndexOf("/") + 1) + temp;
            File tempFile = new File(tempFilePath);
            //如果目录不存在，则创建
            if (!tempFile.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //将url中的数据流对象，写入到临时文件中file
            writeFile(uri, tempFile);
            //将临时文件重命名为正常文件名
            String orgFilePath = tempFilePath.substring(0, tempFilePath.lastIndexOf(temp));
            File orgFile = new File(orgFilePath);
            if(orgFile.exists()){
                orgFile.delete();
            }
            tempFile.renameTo(orgFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过url获取服务器对象流，并写入到文件中file
     * @param uri  文件下载地址
     * @param file 临时文件
     */
    private static void writeFile(URL uri, File file) {
        byte[] bytes = new byte[1024 * 1024];
        //定义流对象
        InputStream inputStream = null;//数据流
        FileOutputStream fileOutputStream = null;//写入file对象数据流
        int byteCount = 0;
        //根据URL和服务器建立连接---数据流
        try {
            fileOutputStream = new FileOutputStream(file, true);
            //建立连接，获取对象流
            inputStream = getInputStream(uri, file.length());

            while (inputStream!=null && (byteCount = inputStream.read(bytes)) != -1) {
                //数据流---file临时文件中
                fileOutputStream.write(bytes, 0, byteCount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流对象
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 建立与下载文件的连接，获取下载文件的对象流
     * @param uri             文件下载地址
     * @param startFileLength 临时文件已下载数量
     * @return
     */
    private static InputStream getInputStream(URL uri, long startFileLength) {
        InputStream inputStream = null;
        HttpURLConnection connection;

        try {
            //URL filePath = new URL(url);//和服务器建立连接、获取文件路径
            connection = (HttpURLConnection) uri.openConnection();//开启连接
            connection.setConnectTimeout(30 * 1000);//连接超时时间
            long endFileLength = connection.getContentLengthLong();//文件的总长度
            if (startFileLength < endFileLength) {//还没下载完毕
                connection.disconnect();//销毁链接
                connection = (HttpURLConnection) uri.openConnection();//开启连接
                connection.setConnectTimeout(30 * 1000);//连接超时时间
                connection.setRequestProperty("RANGE", "bytes=" + startFileLength + "-");//设置请求发送下标的对象
                System.out.println("本次下载总大小=="+connection.getContentLengthLong());
                inputStream = connection.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     *
     * @param url
     * @param startFileLength
     * @return
     */
    public static byte[] downLoadFileBytes(String url, long startFileLength){
        byte[] bytes = new byte[1024 * 1024];
        InputStream inputStream = null;//数据流 定义流对象
        int byteCount = 0;
        byte[] in_b = new byte[0];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            URL uri = new URL(url);
            //建立连接，获取对象流
            inputStream = getInputStream(uri, startFileLength);

            while (inputStream!=null && (byteCount = inputStream.read(bytes)) != -1) {
                //数据流---file临时文件中
                baos.write(bytes, 0, byteCount);
            }
            //baos.flush();
            in_b = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流对象
            try {
                if (inputStream != null) {
                    inputStream.close();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return in_b;
    }

}
