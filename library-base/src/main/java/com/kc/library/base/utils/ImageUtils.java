package com.kc.library.base.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * @data on 5/8/21 2:28 PM
 * @auther KC
 * @describe 将图片资源转换为Base64格式的
 */
public class ImageUtils {

    //编码
    final static Base64.Encoder encoder = Base64.getEncoder();
    //解码
    final static Base64.Decoder decoder = Base64.getDecoder();

    /**
     * @Descriptionmap 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @author temdy
     * @Date 2015-01-26
     * @param path 图片路径
     * @return
     */
    public static byte[] imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * @Descriptionmap 对字节数组字符串进行Base64解码并生成图片
     * @author temdy
     * @Date 2015-01-26
     * @param base64 图片Base64数据
     * @param path 图片路径
     * @return
     */
    public static boolean base64ToImage(String base64, String path) {// 对字节数组字符串进行Base64解码并生成图片
        if (base64 == null){ // 图像数据为空
            return false;
        }
        try {
            // Base64解码
            byte[] bytes = decoder.decode(base64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
