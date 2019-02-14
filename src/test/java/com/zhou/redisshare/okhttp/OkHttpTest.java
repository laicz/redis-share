package com.zhou.redisshare.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhoumb on 2019/1/8
 */
public class OkHttpTest {

    @Test
    public void post() throws IOException {
        String requestBody = "[{\n" +
                "\t\"id\": \"5b643e59b6751e4854740a9a\",\n" +
                "\t\"_class\": \"com.weway.bizcard.common.model.Member\",\n" +
                "\t\"personPubKey\": \"LS0tLS1CRUdJTiBSU0EgUFVCTElDIEtFWS0tLS0tCk1JR0pBb0dCQUpQc0VpOGZxMjI2YUVvYWNObGNpbCtLM0pYVmVEcnRIdEJSNkN2eU9MWUNPZDZhbEpiVnhhODEKN3hsbXJqQUVySk14RmdPQncrd0FMSElTeDhMSUVHc2hmTG02amF5OUpqWk9WQWtqNkp1VnErTjhjamZWRXh4eApyWmRQb3BEVUs4WDlaaXE5MDczT2ZEM09XQ0ZRYmJWa09NUVJ3eklpbXpkSW9Pdjh2aUJOQWdNQkFBRT0KLS0tLS1FTkQgUlNBIFBVQkxJQyBLRVktLS0tLQo=\",\n" +
                "\t\"personPriKey\": \"LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlDWVFJQkFBS0JnUUNUN0JJdkg2dHR1bWhLR25EWlhJcGZpdHlWMVhnNjdSN1FVZWdyOGppMkFqbmVtcFNXCjFjV3ZOZThaWnE0d0JLeVRNUllEZ2NQc0FDeHlFc2ZDeUJCcklYeTV1bzJzdlNZMlRsUUpJK2libGF2amZISTMKMVJNY2NhMlhUNktRMUN2Ri9XWXF2ZE85em53OXpsZ2hVRzIxWkRqRUVjTXlJcHMzU0tEci9MNGdUUUlEQVFBQgpBb0dBT05ML1NVc0pOK1hjR2x0OTdvSTJSZTI2UDNUNTh1a1VaVUtSeGhBTzZSeUJ6cG5qYXU1RU9zS2xUMFM2CnRZZVdKQlUvTjZtcDhnYy9DN05hLzBwKysvd0M5Z3grWUxhZWhyanBwYjNtSFhIY2d6WUJLMlNlSWxPRGlkalQKazlBcG1JZk0vT1NDSWRTQWhZNXBNZmxyY0VuOTQ4VHRTcml5OWJNWXRYZmZIS0VDUlFDWWdhS0lvU0hDRy9mOQp4T09Tb2N3Q0hMSFRxZkN3TUEzaTJmZGl2cmttU0w4OGZqMlBJWFA0MytkY1NWUVhaSjZDQk93OUFLdHp3WFhrClU5NURLSXNheTdEaWh3STlBUGhPQ01nVGMydjU3NVdFUFhja2Q3U2JpQkZTMi9uQmhEQm1Sb0E1eGdIdHJzQjQKWklNODlRRWZZKyt0c284TGR4Z0daN0hUUitGWHB4OFhpd0pGQUpWNDBUMktlQlRRNXRHaGJNN2xwNWNXV3JBeQo2b1UweEJkb1BKQmFrQXRXMTZCSnRyb1hOcHhoajFnalFya2UxRUJvNU1XeFoxbXY3b1ZZTWdxdXJSQVpQZ2YzCkFqMEFyYnZ6MndINURZRElTTVpKTjNhMXB4SDM1NThoT1NSVXVJU25LWXJDNTVDRmFEZ1VvOXJmMXBRL0dYRmcKY1VQbFpWTDk5T2RPOFBXaWdsZTNBa1FtODhXQ210bmJqRnloYTQxVjZtNDJQc0xjSXZJUjFqZFpRYUFISWNPTQo4TWcySVpGeitYSVFXQVRZQ0lzWmtYOEtDbmZhTUhlWHpLd1pKZlYvVjdHdkMzVENKQT09Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tCg==\",\n" +
                "\t\"personFp\": \"8f3d444930b011f1465a7e23c60ea51a9eb2ec5f90da69e4a5ccb077eb444e90\",\n" +
                "\t\"companyId\": \"5a6055ad741aac3cf435f8b4\",\n" +
                "\t\"token\": \"96cb01773804bf0de70ff4bdc25c097b\",\n" +
                "\t\"name\": \"测试1-test-001\",\n" +
                "\t\"mobile\": \"13714064646\",\n" +
                "\t\"company\": \"广东合富房地产置业有限公司-new3\",\n" +
                "\t\"address\": \"天河区中心大道华景路87号103A铺-test-001\",\n" +
                "\t\"department\": \"\",\n" +
                "\t\"office\": \"直接请求测试-test-002\",\n" +
                "\t\"job\": \"\",\n" +
                "\t\"telephone\": \"\",\n" +
                "\t\"email\": \"@ink.cn\",\n" +
                "\t\"wx\": \"\",\n" +
                "\t\"fax\": \"\",\n" +
                "\t\"site\": \"\",\n" +
                "\t\"wb\": \"\",\n" +
                "\t\"qq\": \"\",\n" +
                "\t\"isJob\": 0,\n" +
                "\t\"disable\": false,\n" +
                "\t\"lockAvatar\": false,\n" +
                "\t\"limitEdit\": false,\n" +
                "\t\"messaged\": false,\n" +
                "\t\"nameCapPy\": \"C\",\n" +
                "\t\"nameAllPy\": \"CESHI1\",\n" +
                "\t\"nameFirstPy\": \"CS1\",\n" +
                "\t\"checkFlag\": 0,\n" +
                "\t\"orgId\": \"5a6055ae741aac3cf435f8b6\",\n" +
                "\t\"sendSmsCount\": 0,\n" +
                "\t\"cardId\": \"5b8e5bcb670e4832b40e6152\",\n" +
                "\t\"cardStatus\": 0,\n" +
                "\t\"cardUserId\": \"5aac6b569a37c32c8c210277\",\n" +
                "\t\"notBindwxQrPath\": \"http://7xrx9e.com1.z0.glb.clouddn.com/wxAppCardQr/appid/wxbe89fa9715c0be2f/batch/2018_08_03_19_37_01/5b643e5db6751e4854740a9d/e324936d97e347d7b46bfcaa5e77d48f.jpg\",\n" +
                "\t\"canBindWxQrPath\": \"http://7xrx9e.com1.z0.glb.clouddn.com/wxAppCardQr/appid/wxbe89fa9715c0be2f/batch/2018_08_03_19_37_00/5b643e5cb6751e4854740a9c/ae654753645f440baa150aa2739b6ca9.jpg\",\n" +
                "\t\"sendFlag\": false,\n" +
                "\t\"showState\": 0,\n" +
                "\t\"nameAllPyTNine\": \"23744\"\n" +
                "}]";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("members", requestBody);
        String sign = getSign(JSON.parseObject(jsonObject.toJSONString(), Map.class));
        jsonObject.put("sign", sign);
        Request request = new Request.Builder()
                .url("http://192.168.1.25:8181/bizcardBackend2/open/company/member/importMember")
                .post(RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString()))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        System.out.println(JSON.toJSONString(execute));
    }

    public static final String encode3(String s, String encode) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] strTemp = s.getBytes(encode);
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var11) {
            return null;
        }
    }

    public String getSign(final Map<String, Object> params) {
        String sign = null;
        TreeMap<String, Object> linkParams = new TreeMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            linkParams.put(entry.getKey(), entry.getValue());
        }
        String line = "";
        for (Map.Entry<String, Object> entry : linkParams.entrySet()) {
            line += entry.getKey() + "=" + entry.getValue() + "&";
        }
        if (line.length() > 0) {
            line = StringUtils.substring(line, 0, StringUtils.lastIndexOf(line, "&"));
        }
        line += "dp1UeL8JOIkdyc4lCNJ3C4hlkN";
        line += getSignDate();
        sign = encode3(line, "UTF-8").toUpperCase().substring(8, 24);
        return sign;
    }

    public String getSignDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    @Test
    public void testInt(){
        int i = 10;
        System.out.println( i << 1);
        System.out.println(i);
        System.out.println(new Date().getTime());
    }

}
