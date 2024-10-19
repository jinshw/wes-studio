package com.site.mountain.utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShellUtils {

    private static ProcessBuilder processBuilder = new ProcessBuilder();

    /**
     * 执行脚本命令
     *
     * @param commend
     * @return java.lang.Process
     * @throws
     * @author feiyang
     * @date 2019/9/26
     */
    public static Process exec(List<String> commend) {
        Process process = null;
        try {
            String[] commends = new String[commend.size()];
            commend.toArray(commends);
            processBuilder.command(commends);
            process = processBuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

    public static Process exec(String path, List<String> commend) {
        Process process = null;
        try {
            String[] commends = new String[commend.size()];
            commend.toArray(commends);
            processBuilder.command(commends);
            processBuilder.directory(new File(path));
            process = processBuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

    public static Process exec(String commendStr) {
        List<String> commend = new ArrayList<>();
//        commend.add("cmd");
//        commend.add("/c");
        String[] commendArr = commendStr.split(" ");
        for (int i = 0; i < commendArr.length; i++) {
            commend.add(commendArr[i]);
        }

//        commend.add(commendStr);
        Process process = exec(commend);
        return process;
    }

    /**
     * 在指定路径执行命令
     *
     * @param path
     * @param commendStr
     * @return
     */
    public static Process exec(String path, String commendStr) {
        List<String> commend = new ArrayList<>();
        commend.add("cmd");
        commend.add("/c");
        commend.add(commendStr);
        Process process = exec(path, commend);
        return process;
    }

    /**
     * @param process
     * @return java.lang.String
     * @throws
     * @author feiyang
     * @date 2019/9/26
     */
    public static String getOutput(Process process) {
        String output = null;
        BufferedReader reader = null;
        try {
            if (process != null) {
                StringBuffer stringBuffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
                while (reader.read() != -1) {
                    stringBuffer.append("\n" + reader.readLine());
//                    System.out.println(reader.readLine());
                }
                output = stringBuffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeQuietly(reader);
        return output;
    }

    /**
     * 获取错误信息
     *
     * @param process
     * @return java.lang.String
     * @throws
     * @author feiyang
     * @date 2019/9/26
     */
    public static String getError(Process process) {
        String errput = null;
        BufferedReader reader = null;
        try {
            if (process != null) {
                StringBuffer stringBuffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                while (reader.read() != -1) {
                    stringBuffer.append("\n" + reader.readLine());
                }
                errput = stringBuffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeQuietly(reader);
        return errput;
    }

    /**
     * 关流
     *
     * @param reader
     * @return void
     * @throws
     * @author feiyang
     * @date 2019/9/26
     */
    public static void closeQuietly(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 终止进程
     *
     * @param process
     * @return void
     * @throws
     * @author feiyang
     * @date 2019/9/26
     */
    public static void destroy(Process process) {
        if (process != null) {
            process.destroyForcibly();
        }
    }

    public static Integer waitFor(Process process) {
        Integer exitCode = null;
        if (process != null) {
            try {
                exitCode = process.waitFor();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return exitCode;
    }

    /**
     * 执行命令操作
     *
     * @param commend
     */
    public static void dealCommand(String path, List<String> commend, boolean wait) {
        Process process;
        if (path != null) {
            process = ShellUtils.exec(path, commend);
        } else {
            process = ShellUtils.exec(commend);
        }
        if (wait) {
//            String message = ShellUtils.getOutput(process);
//            String error = ShellUtils.getError(process);
//            System.out.println(message);
//            System.out.println(error);
            Integer exitCode = ShellUtils.waitFor(process);
            if (0 == exitCode) {
                System.out.println("脚本文件执行成功:" + exitCode);
            } else {
                System.out.println("脚本文件执行失败:" + exitCode);
            }
        }
    }

    public static void dealCommandWithPath(String path, String commendStr, boolean wait) {
        Process process;
        if (path != null) {
            process = ShellUtils.exec(path, commendStr);
        } else {
            process = ShellUtils.exec(commendStr);
        }
        _dealCommand(process, wait);

    }

    public static void dealCommand(String commendStr, boolean wait) {
        Process process = ShellUtils.exec(commendStr);
        _dealCommand(process, wait);
    }

    private static void _dealCommand(Process process, boolean wait) {
        if (wait) {
//            String message = ShellUtils.getOutput(process);
//            String error = ShellUtils.getError(process);
//            System.out.println(message);
//            System.out.println(error);
            Integer exitCode = ShellUtils.waitFor(process);
            if (0 == exitCode) {
                System.out.println("脚本文件执行成功:" + exitCode);
            } else {
                System.out.println("脚本文件执行失败:" + exitCode);
            }
        }
    }


}



