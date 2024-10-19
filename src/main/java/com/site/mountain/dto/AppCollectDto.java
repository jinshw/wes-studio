package com.site.mountain.dto;

import com.site.mountain.entity.NtHikCameras;
import com.site.mountain.entity.NtIbdTrafficPole;

import java.util.List;

public class AppCollectDto {
    public NtIbdTrafficPole devicePole=null;
    public List<NtHikCameras> shexiangtou;

    public String[] imgs;

    public NtIbdTrafficPole getDevicePole() {
        return devicePole;
    }

    public void setDevicePole(NtIbdTrafficPole devicePole) {
        this.devicePole = devicePole;
    }

    public List<NtHikCameras> getShexiangtou() {
        return shexiangtou;
    }

    public void setShexiangtou(List<NtHikCameras> shexiangtou) {
        this.shexiangtou = shexiangtou;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }
}
