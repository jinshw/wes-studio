package com.site.mountain.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface GenerateObjFromXodr extends Library {
    String property = System.getProperty("user.dir");
    String filePath = property + "/dll";

    GenerateObjFromXodr generateObjFromXodr = Native.loadLibrary(filePath + "/" + "GenerateObjFromXodr", GenerateObjFromXodr.class);

    String GenerateObjModelByXodr(String filePath, String fileName, String outFolderPath, String strtextName);

}
