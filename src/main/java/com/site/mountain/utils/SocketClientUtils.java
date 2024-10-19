package com.site.mountain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class SocketClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(SocketClientUtils.class);

    public static boolean sendFile(String socketConn, String filePath) {
        boolean result = true;
        String[] connArr = socketConn.split(":");
        String connIP = connArr[0];
        String portStr = connArr[1];
        int port = Integer.valueOf(portStr);
        File file = new File(filePath);
        Socket socket = null;
        DataInputStream fis = null;
        DataOutputStream dos = null;
        String fileName = file.getName();
        try {
            socket = new Socket(connIP, port);
            int bufferSize = 8192;
            byte[] buf = new byte[bufferSize];
            fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
            dos = new DataOutputStream(socket.getOutputStream());
            int read = 0;
            int passedlen = 0;

//            String fileName = file.getName() +"####";
            dos.write(fileName.getBytes());
            dos.flush();

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String info = null;
            char[] chars = new char[5];
            bufferedReader.read(chars);
//            String centent = String.valueOf(chars);
//            socket.shutdownInput();

            if (chars[0] == 'o' && chars[1] == 'k') {
                logger.info("开始发送文件" + filePath);
                while ((read = fis.read(buf)) != -1) {
                    passedlen += read;
                    dos.write(buf, 0, read);
                    dos.flush();
                }

                dos.flush();
                bufferedReader.close();
                inputStream.close();
                fis.close();
                dos.close();
                socket.close();
                logger.info("文件传输完成！" + filePath);
            }
        } catch (IOException e) {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dos != null) {
                    dos.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            e.printStackTrace();
            result = false;
        }

        return result;
    }
}
