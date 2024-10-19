package com.site.mountain.utils;

public class CustomArrayUtils {

    /**
     * 2个byte数组合并
     *
     * @param bt1
     * @param bt2
     * @return
     */
    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        int i = 0;
        for (byte bt : bt1) {
            bt3[i] = bt;
            i++;
        }

        for (byte bt : bt2) {
            bt3[i] = bt;
            i++;
        }
        return bt3;
    }

    /**
     *  获取byte[]下标[start-end)数据组
     * @param srcBytes
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static byte[] getBytesByIndex(byte[] srcBytes, int startIndex, int endIndex) {
        byte[] resultByte = new byte[endIndex - startIndex];
        int index = 0;
        for (int i = 0; i < srcBytes.length; i++) {
            if (i >= startIndex && i < endIndex) {
                resultByte[index] = srcBytes[i];
                index = index + 1;
            }
        }
        return resultByte;
    }

    /**
     *  获取byte[]下标到最后的子数组
     * @param srcBytes
     * @param startIndex
     * @return
     */
    public static byte[] getBytesByIndexToLastIndex(byte[] srcBytes, int startIndex) {
        int endIndex = srcBytes.length;
        byte[] resultByte = new byte[endIndex - startIndex];
        int index = 0;
        for (int i = 0; i < srcBytes.length; i++) {
            if (i >= startIndex && i < endIndex) {
                resultByte[index] = srcBytes[i];
                index = index + 1;
            }
        }
        return resultByte;
    }
}
