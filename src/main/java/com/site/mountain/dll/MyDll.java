package com.site.mountain.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface MyDll extends Library {
    String property = System.getProperty("user.dir");
    String filePath = property + "\\dll";
    //String filePath = "D:\\JavaIdeaWorkspace\\gitlab27\\nantongrule\\nantongrule-studio\\dll";
    MyDll mydll = Native.loadLibrary(filePath + "\\" + "TESTDLL", MyDll.class);//.dll

    int add(int a, int b);
}
