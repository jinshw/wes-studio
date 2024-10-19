package com.site.mountain.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileOtaUtils {

    /*public static void downloadFile(String fileURL, String saveDir, String fileName, long startByte) {
        try {
            URL url = new URL(fileURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // 设置请求头为GET
            httpURLConnection.setRequestMethod("GET");

            // 如果有startByte，则设置Range
            if (startByte > 0) {
                String rangeHeader = "bytes=" + startByte + "-";
                httpURLConnection.setRequestProperty("Range", rangeHeader);
            }

            // 连接服务器
            httpURLConnection.connect();

            // 获取输入流
            InputStream inputStream = httpURLConnection.getInputStream();

            // 文件保存路径
            File file = new File(saveDir + File.separator + fileName);

            // 如果文件已存在，则打开追加模式
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(startByte); // 移动到开始位置

            // 创建缓冲输出流
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(randomAccessFile.getChannel().toString()));

            // 读取数据并写入文件
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(dataBuffer)) != -1) {
                bufferedOutputStream.write(dataBuffer, 0, bytesRead);
            }

            // 关闭流
            bufferedOutputStream.close();
            inputStream.close();
            randomAccessFile.close();

            System.out.println("下载完成：" + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadFileTest2(String fileURL) throws IOException {
        String url = fileURL;
        String fileName = "aa02.dat";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        File file = new File(fileName);
        if (file.exists()) {
            httpGet.addHeader("Range", "bytes=" + file.length() + "-");
        }

        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        try (InputStream in = entity.getContent();
             FileOutputStream out = new FileOutputStream(file, true)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        httpClient.close();
    }*/

    public static void downloadFileTest3(HttpServletRequest request,
                                         HttpServletResponse response, Long fileLength, String contentType,
                                         String fileName, String fileId, String fileURL) {
        ServletOutputStream out = null;
        response.reset();

        // 记录断点续传的開始点
        long pos = 0;
        if (null != request.getHeader("Range")) {
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            try {
                pos = Long.parseLong(request.getHeader("Range")
                        .replaceAll("bytes=", "").replaceAll("-.*", ""));
            } catch (NumberFormatException e) {
                pos = 0;
            }
            String contentRange = new StringBuffer("bytes ").append(pos + "")
                    .append("-").append((fileLength.intValue() - 1) + "")
                    .append("/").append(fileLength.intValue() + "").toString();
            response.setHeader("Content-Range", contentRange);
        }

        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-Length",
                String.valueOf(fileLength.intValue() - pos));
        response.setCharacterEncoding("UTF-8");
        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment;filename=\""
                + fileName + "\"");
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.getMessage();
        }

        // 断点下载
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(fileURL);//SysConf.getString("fezo.download.url"));

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        //nvps.add(new BasicNameValuePair(SysConf.getString("fezo.download.param"), fileId));

        HttpResponse httpResponse = null;
        BufferedInputStream input = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            httpPost.setHeader("Range", "bytes=" + pos + "-");
            httpResponse = httpClient.execute(httpPost);

            input = new BufferedInputStream(httpResponse.getEntity().getContent());

            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = input.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            input.close();
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        } catch (ClientProtocolException e) {
            e.getMessage();
        } catch (IOException e) {
            // 能够忽略这个异常。有可能是用户暂停下载，或者迅雷等下载工具分块下载
        } finally {
            try {
                if (httpClient != null) httpClient.close();
            } catch(IOException e) {
                e.getMessage();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        String fileURL = "http://cdn.aerohuanyou.com//ota/1709803191971/map2023Q1_D07120.zip/data/ADASRoute.dat";
        String saveDir = "D:\\test202407";
        String fileName = "aa.dat";
        long startByte = 0; // 初始下载时从0开始

        // 假设这是第二次下载，从某个字节开始
        // startByte = 1024 * 1024; // 例如从1MB开始

        //测试001
        //downloadFile(fileURL, saveDir, fileName, startByte);

        //测试002
        //downloadFileTest2(fileURL);

        //测试003
        //downloadFileTest3(fileURL);

    }
}
