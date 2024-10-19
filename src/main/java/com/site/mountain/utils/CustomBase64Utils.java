package com.site.mountain.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class CustomBase64Utils {

    private static final Logger logger = LogManager.getLogger(CustomBase64Utils.class);


    /**
     * base64字符串加密
     *
     * @param text
     * @return
     */
    public static byte[] base64Encoder(String text) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encode(text.getBytes());
    }

    /**
     * base64字符数组加密
     *
     * @param text
     * @return
     */
    public static byte[] base64Encoder(byte[] text) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encode(text);
    }

    /**
     * base64加密返回字符串
     *
     * @param text
     * @return
     */
    public static String base64EncoderToString(byte[] text) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(text);
    }


    /**
     * base64 解码
     *
     * @param encodedText
     * @return
     */
    public static byte[] base64DecodeToBytes(byte[] encodedText) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(encodedText);
    }

    /**
     * 解码
     *
     * @param encodedText
     * @return
     */
    public static byte[] base64DecodeToBytes(String encodedText) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(encodedText);
    }

    /**
     * 解码
     *
     * @param encodedText
     * @param charsetName
     * @return
     */
    public static String base64Decode(String encodedText, String charsetName) {
        Base64.Decoder decoder = Base64.getDecoder();
        if (charsetName == null) {
            charsetName = "UTF-8";
        }
        try {
            String text = new String(decoder.decode(encodedText), charsetName);
            return text;
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }
        return null;
    }

}
