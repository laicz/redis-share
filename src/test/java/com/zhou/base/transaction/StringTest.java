package com.zhou.base.transaction;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by zhoumb on 2019/4/17
 */
public class StringTest {
    private String rel = "****";
    private String la = "***";

    @Test
    public void hideString() {
        String target = "13652405060";
        if (StringUtils.isNotBlank(target) && target.length() >= 11) {
            System.out.println(target.substring(0,target.length() - 8) + rel + target.substring(target.length() - 4));
        }
        target = "3561234";
        if (StringUtils.isNotBlank(target) && target.length() >= 7) {
            System.out.println(target.substring(0,target.length() - 5) + la + target.substring(target.length() - 2));
        }
    }

    @Test
    public void trim(){
        String str = " dhf falfjsdg  fajslf   ";
        System.out.println(str.length());
        System.out.println(str.trim().length());
    }
}
