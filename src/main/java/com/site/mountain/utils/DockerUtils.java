package com.site.mountain.utils;
import com.alibaba.fastjson.JSON;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.LoadImageCmd;
import com.github.dockerjava.api.command.TagImageCmd;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class DockerUtils {
    private final static Logger logger = LoggerFactory.getLogger(DockerUtils.class);

    // docker服务端IP地址
    public static final String DOCKER_HOST="tcp://127.0.0.1:2375";
    // docker安全证书配置路径
    public static final String DCOEKR_CERT_PATH="";
    // docker是否需要TLS认证
    public static final Boolean DOCKER_TLS_VERIFY=false;
    // Harbor仓库的IP
    public static final String REGISTRY_URL="192.168.79.131:8443";
    // Harbor仓库的名称
    public static final String REGISTRY_PROJECT_NAME="test";
    // Harbor仓库的登录用户名
    public static final String REGISTRY_USER_NAME="admin";
    // Harbor仓库的登录密码
    public static final String REGISTRY_PASSWORD="Harbor12345";
    // docker远程仓库的类型，此处默认是harbor
    public static final String REGISTRY_TYPE="harbor";

    public static final String REGISTRY_PROTOCAL="https://";


    /**
     * 构建DocekrClient实例
     * @param dockerHost
     * @param tlsVerify
     * @param dockerCertPath
     * @param registryUsername
     * @param registryPassword
     * @param registryUrl
     * @return
     */
    public static DockerClient getDocekrClient(String dockerHost,boolean tlsVerify,String dockerCertPath,
                                               String registryUsername, String registryPassword,String registryUrl){
        DefaultDockerClientConfig dockerClientConfig = null;
        if(tlsVerify){
            dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
                    .withDockerHost(DOCKER_HOST)
                    .withDockerTlsVerify(true)
                    .withDockerCertPath(DCOEKR_CERT_PATH)
                    .withRegistryUsername(REGISTRY_USER_NAME)
                    .withRegistryPassword(REGISTRY_PASSWORD)
                    .withRegistryUrl(registryUrl)
                    .build();
        }else {
            dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
                    .withDockerHost(DOCKER_HOST)
                    .withDockerTlsVerify(false)
                    .withDockerCertPath(DCOEKR_CERT_PATH)
                    .withRegistryUsername(REGISTRY_USER_NAME)
                    .withRegistryPassword(REGISTRY_PASSWORD)
                    .withRegistryUrl(registryUrl)
                    .build();
        }
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost())
                .sslConfig(dockerClientConfig.getSSLConfig())
                .build();

        return DockerClientImpl.getInstance(dockerClientConfig,httpClient);
    }

    public static DockerClient getDockerClient(){
        return getDocekrClient(DOCKER_HOST,DOCKER_TLS_VERIFY,DCOEKR_CERT_PATH,REGISTRY_USER_NAME,REGISTRY_PASSWORD,REGISTRY_URL);
    }

    /**
     * 获取docker基础信息
     * @param dockerClient
     * @return
     */
    public static String getDockerInfo(DockerClient dockerClient){
        Info info = dockerClient.infoCmd().exec();
        return JSON.toJSONString(info);
    }

    /**
     * 给镜像打标签
     * @param dockerClient
     * @param imageIdOrFullName
     * @param respository
     * @param tag
     */
    public static void tagImage(DockerClient dockerClient, String imageIdOrFullName, String respository,String tag){
        TagImageCmd tagImageCmd = dockerClient.tagImageCmd(imageIdOrFullName, respository, tag);
        tagImageCmd.exec();
    }

    /**
     * load镜像
     * @param dockerClient
     * @param inputStream
     */
    public static void loadImage(DockerClient dockerClient, InputStream inputStream){
        LoadImageCmd loadImageCmd = dockerClient.loadImageCmd(inputStream);
        loadImageCmd.exec();
    }

    /**
     * 推送镜像
     * @param dockerClient
     * @param imageName
     * @return
     * @throws InterruptedException
     */
    public static Boolean pushImage(DockerClient dockerClient,String imageName) throws InterruptedException {
        final Boolean[] result = {true};
        ResultCallback.Adapter<PushResponseItem> callBack = new ResultCallback.Adapter<PushResponseItem>() {
            @Override
            public void onNext(PushResponseItem pushResponseItem) {
                if (pushResponseItem != null){
                    ResponseItem.ErrorDetail errorDetail = pushResponseItem.getErrorDetail();
                    if (errorDetail!= null){
                        result[0] = false;
                        logger.error(errorDetail.getMessage(),errorDetail);
                    }
                }
                super.onNext(pushResponseItem);
            }
        };
        dockerClient.pushImageCmd(imageName).exec(callBack).awaitCompletion();
        return result[0];
    }

    /**
     * 从镜像的tar文件中获取镜像名称
     * @param imagePath
     * @return
     */
//    public static String getImageName(String imagePath){
//        try {
//            return UnCompress.getImageName(imagePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * 通过dockerFile构建镜像
     * @param dockerClient
     * @param dockerFile
     * @return
     */
    public static String buildImage(DockerClient dockerClient, File dockerFile){
        BuildImageCmd buildImageCmd = dockerClient.buildImageCmd(dockerFile);
        BuildImageResultCallback buildImageResultCallback = new BuildImageResultCallback() {
            @Override
            public void onNext(BuildResponseItem item) {
                logger.info("{}", item);
                super.onNext(item);
            }
        };

        return buildImageCmd.exec(buildImageResultCallback).awaitImageId();
//        logger.info(imageId);
    }

    /**
     * 获取镜像列表
     * @param dockerClient
     * @return
     */
    public static List<Image> imageList(DockerClient dockerClient){
        List<Image> imageList = dockerClient.listImagesCmd().withShowAll(true).exec();
        return imageList;
    }
}
