package com.zhou.hd;

import com.zhou.redisshare.RedisShareApplication;
import com.zhou.redisshare.utils.MD5;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;

/**
 * Created by zhoumb on 2019/3/11
 */
//@ContextConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = RedisShareApplication.class)
public class ImgHdTest {
//    @Autowired
//    private RestTemplate restTemplate;

    @Test
    public void restTemplate() throws IOException {
        String url = "http://wx.qlogo.cn/mmopen/6GEtlGJRz8FbMpdX4ZsJehQ9m7FBiaqW5zicHUIYPKut0r2pwq1ZibiaDwlFiaibCtBToZ4ts9MRbbjb0uPNwoVLI4WWjNJcVI5ic9J/0";
        String ur2 = "https://wx.qlogo.cn/mmopen/vi_32/zBZticicTY3TOqFHYWnMQ6icvxkZJm5HMJZEJOTPJnHMbV0tBsJaf31atk8stedhpdXPPTsdoT1K6k3BTKERUERCA/0";
//        new FileOutputStream(new File("C:\\Users\\dev\\Desktop\\微信头像2.jpg")).write(bytes);*/
        String str1 = MD5.streamToMD5(getImgInputStream(url));
        String str2 = MD5.streamToMD5(getImgInputStream(ur2));
        System.out.println(str1 + " ----  " + str2);
        System.out.println(StringUtils.equals(str1, str2));
        InputStream inputStream = getImgInputStream(url);
        if (inputStream == null) {
            System.out.println("没有");
            return;
        }
        byte[] bytes1 = inputToByte(inputStream);
        System.out.println(bytes1.length);
    }

    public InputStream getImgInputStream(String url) throws IOException {
        InputStream inputStream = null;
        Request request = (new Request.Builder()).url(url).get().build();
        Response response = new OkHttpClient().newCall(request).execute();
        if (!response.isSuccessful()) {
            System.out.println("调用失败 ... ");
            return null;
        }
        inputStream = response.body().byteStream();
        return inputStream;
    }

    /**
     * 流转byte数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static byte[] inputToByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc;
        while ((rc = inputStream.read(buff, 0, 1024)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}
