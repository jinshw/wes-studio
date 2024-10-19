package com.site.mountain.controller.map;

import com.site.mountain.utils.ShellUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        //整条命令执行
//        String mysqlCommand = "obj2gltf -i C:/Users/Administrator/Desktop/obj_test/test/test.obj -o C:/Users/Administrator/Desktop/obj_test/out/test.gltf";
////        String mysqlCommand = "docker cp D:\\docker\\docker-saipan\\corpus_init.sql pgsql:/tmp";
//        ShellUtils.dealCommand(mysqlCommand, true);


        String command = "docker exec -it obj23dtiles python3 /APP/software/obj23dtiles/batch_process.py -i /APP/data/yizhuangOBJ/ -o /APP/data/yizhuangOBJ_result/ -z 3 -r 0.005 -f Y -u Z -w 6 -j /APP/data/semanticyizhuang.json -e";


        ProcessBuilder processBuilder = new ProcessBuilder("docker","exec","-i","obj23dtiles","/bin/bash","-c","python3","/APP/software/obj23dtiles/batch_process.py","-i","/APP/data/yizhuangOBJ/","-o","/APP/data/yizhuangOBJ_result/","-z","3","-r","0.005","-f","Y","-u","Z","-w","6","-j","/APP/data/semanticyizhuang.json","-e");
//        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        Process process = null;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(line);
        }

        //使用powershell执行
//        List<String> commend = new ArrayList<>();
//        commend.add("powershell.exe");
//        commend.add("docker stop $(docker ps -a -q)");
//        ShellUtils.dealCommand(null,commend,true);

//        try {
//            // 执行命令行程序
////            Process process = Runtime.getRuntime().exec("obj2gltf -i C:/Users/Administrator/Desktop/obj_test/test/test.obj -o C:/Users/Administrator/Desktop/obj_test/out/test.gltf");
//            Process process = Runtime.getRuntime().exec("docker exec -it obj23dtiles python3 /APP/software/obj23dtiles/batch_process.py -i /APP/data/yizhuangOBJ/ -o /APP/data/yizhuangOBJ_result/ -z 3 -r 0.005 -f Y -u Z -w 6 -j /APP/data/semanticyizhuang.json  -e");
//
//            // 获取命令行程序的输出结果
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // 等待命令行程序执行完毕
//            int exitCode = process.waitFor();
//            System.out.println("命令行程序执行完毕，退出码：" + exitCode);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
