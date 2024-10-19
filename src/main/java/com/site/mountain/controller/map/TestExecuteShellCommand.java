package com.site.mountain.controller.map;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.ExecStartCmd;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.site.mountain.utils.DockerUtils;
import com.site.mountain.utils.FileUtils;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class TestExecuteShellCommand {
    public static void main(String[] args) throws InterruptedException {
//        DockerClient docekrClient = DockerUtils.getDocekrClient(DockerUtils.DOCKER_HOST, DockerUtils.DOCKER_TLS_VERIFY,
//                DockerUtils.DCOEKR_CERT_PATH, DockerUtils.REGISTRY_USER_NAME, DockerUtils.REGISTRY_PASSWORD,
//                DockerUtils.REGISTRY_URL);


        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerTlsVerify(false)
                // 这里填最上面填的ip端口号，ip换成服务器ip
                .withDockerHost("tcp://localhost:2375")
                // docker API版本号，可以用docker version查看
                .withApiVersion("24.0.6")
                // 默认
                .withRegistryUrl("https://index.docker.io/v1/").build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(10000)
                .connectionTimeout(Duration.ofSeconds(60 * 20))
                .responseTimeout(Duration.ofSeconds(60 * 30))
                .build();
//
        DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);


        // 容器名称
        String containerName = "obj23dtiles";

        // 获取容器id
        String containerId = null;
        List<Container> containers = dockerClient.listContainersCmd().exec();
        for (Container container : containers) {
            String[] names = container.getNames();
            if (container.getNames()[0].equals("/" + containerName)) {
                containerId = container.getId();
                break;
            }

        }

        // 输出容器id
        if (containerId != null) {
            System.out.println("容器名称：" + containerName);
            System.out.println("容器id：" + containerId);
        } else {
            System.out.println("未找到名为" + containerName + "的容器");
        }

        String hostBasePath = "D:\\obj23dtiles\\data";
        String dockerBasePath = "/APP/data";
        String relativeInputPath = "/yizhuangOBJ/";
        String relativeOutpuPath = "/yizhuangOBJ_result/";
        String inputPath = dockerBasePath + relativeInputPath;
        String outputPath = dockerBasePath + relativeOutpuPath;
        String hostOutputPath = hostBasePath + relativeOutpuPath;
        if(FileUtils.isDir(hostOutputPath)){
            File file = new File(hostOutputPath);
            FileUtils.deleteFolder(file);
        }

        ExecCreateCmdResponse response = dockerClient.execCreateCmd(containerId)
//        ExecCreateCmdResponse response = dockerClient.execCreateCmd("c863b407b73e49bf069b377ea5fa76952ca986a0c1f72d3e1e512d486f1eaca5")
//                .withCmd("bash", "-c","ls","-l")
                .withEnv(Arrays.asList("PATH=/root/.nvm/versions/node/v16.13.0/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"))
                .withCmd("python3", "/APP/software/obj23dtiles/batch_process.py", "-i", inputPath, "-o", outputPath, "-z", "3", "-r", "0.005", "-f", "Y", "-u", "Z", "-w", "6", "-j", "/APP/data/semanticyizhuang.json", "-e")
                .withAttachStdout(true)
                .withAttachStderr(true)
//                .withCmd(commands)
//                .withCmd( "./usr/bin/python3", "/APP/software/obj23dtiles/batch_process.py", "-i", "/APP/data/yizhuangOBJ/", "-o", "/APP/data/yizhuangOBJ_result/", "-z", "3", "-r", "0.005", "-f", "Y", "-u", "Z", "-w", "6", "-j", "/APP/data/semanticyizhuang.json", "-e")
                .exec();
        ExecStartCmd startCmd = dockerClient.execStartCmd(response.getId());
        startCmd.exec(new ExecStartResultCallback(System.out, System.err) {
            @Override
            public void onComplete() {
                System.out.println("1111111111111");
                super.onComplete();
            }
        }).awaitCompletion();
//        try {
//            startCmd.exec(new ExecStartResultCallback()).awaitCompletion();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//    ExecStartCmd startCmd = docekrClient.execStartCmd(response.getId())
//            .withDetach(false)
//            .withTty(true).exec(new ExecStartResultCallback(System.out,System.err));
//    ExecStartResultCallback callback = new ExecStartResultCallback();
//    startCmd.(callback);
    }

}
