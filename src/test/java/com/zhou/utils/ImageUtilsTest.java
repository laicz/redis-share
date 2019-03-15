package com.zhou.utils;

import com.zhou.redisshare.utils.ImageUtils;

import java.io.IOException;

/**
 * Created by zhoumb on 2019/3/14
 */
public class ImageUtilsTest {
    public static void main(String[] args) throws IOException {
        String srcImg = "C:\\Users\\dev\\Desktop\\微信头像2.jpg";
        int maxPixel = 500;
        String tarImg = "C:\\Users\\dev\\Desktop\\微信头像2_compress_" + maxPixel + ".jpg";
//        ImageUtils.transformer(srcImg, tarImg, maxPixel);
        ImageUtils.compressPic(srcImg, tarImg);
    }
}
