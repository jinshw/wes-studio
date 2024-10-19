package com.site.mountain.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface DeleteFile extends Library {

    DeleteFile deleteFile = Native.loadLibrary("Kernel32", DeleteFile.class);

    boolean DeleteFileA(String filename);

}
