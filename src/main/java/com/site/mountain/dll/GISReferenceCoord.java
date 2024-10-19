package com.site.mountain.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface GISReferenceCoord extends Library {
    String property = System.getProperty("user.dir");
    String filePath = property + "/dll";

    GISReferenceCoord gisReferenceCoord = Native.loadLibrary(filePath + "/" + "GISReferenceCoord", GISReferenceCoord.class);

    String strXYToLatLon(double x, double y);

    String strLatLonToXY(double lon, double lat);
}
