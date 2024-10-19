package com.site.mountain.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    public static void main(String[] args) {
        String filePath = "d:/b.sql";
        File file = new File(filePath);
        try {
            Socket socket = new Socket("127.0.0.1", 9090);
            int bufferSize = 8192;
            byte[] buf = new byte[bufferSize];
            DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            int read = 0;
            int passedlen = 0;

            String fileName = file.getName();
            dos.write(fileName.getBytes());
            dos.flush();

            logger.info("开始发送文件" + filePath);
            while ((read = fis.read(buf)) != -1) {
                passedlen += read;
                dos.write(buf, 0, read);
                dos.flush();
            }
            dos.flush();
            fis.close();
            dos.close();
            socket.close();
            logger.info("文件传输完成！" + filePath);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
