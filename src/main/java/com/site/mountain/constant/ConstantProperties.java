package com.site.mountain.constant;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.file")
@PropertySource("classpath:env/${spring.profiles.active}/constant.properties")
public class ConstantProperties {
    private String imgUploadPath;
    private String imgUrl;
    private String fileUploadPath;
    private String resetPassword;
    private String ftlOutput;
    private String fileUrl;
    private String inputDir;
    private String outPutDir;

    private String hostBasePath;
    private String dockerBasePath;
    private String jsonParam;
    private String containerName;
    private String dockerHost;
    private String dockerApiVersion;

    private String tilesUri;


    public String getTilesUri() {
        return tilesUri;
    }

    public void setTilesUri(String tilesUri) {
        this.tilesUri = tilesUri;
    }

    public String getHostBasePath() {
        return hostBasePath;
    }

    public void setHostBasePath(String hostBasePath) {
        this.hostBasePath = hostBasePath;
    }

    public String getDockerBasePath() {
        return dockerBasePath;
    }

    public void setDockerBasePath(String dockerBasePath) {
        this.dockerBasePath = dockerBasePath;
    }

    public String getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(String jsonParam) {
        this.jsonParam = jsonParam;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getDockerHost() {
        return dockerHost;
    }

    public void setDockerHost(String dockerHost) {
        this.dockerHost = dockerHost;
    }

    public String getDockerApiVersion() {
        return dockerApiVersion;
    }

    public void setDockerApiVersion(String dockerApiVersion) {
        this.dockerApiVersion = dockerApiVersion;
    }

    public String getInputDir() {
        return inputDir;
    }

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }

    public String getOutPutDir() {
        return outPutDir;
    }

    public void setOutPutDir(String outPutDir) {
        this.outPutDir = outPutDir;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFtlOutput() {
        return ftlOutput;
    }

    public void setFtlOutput(String ftlOutput) {
        this.ftlOutput = ftlOutput;
    }

    public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getImgUploadPath() {
        return imgUploadPath;
    }

    public void setImgUploadPath(String imgUploadPath) {
        this.imgUploadPath = imgUploadPath;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }
}
