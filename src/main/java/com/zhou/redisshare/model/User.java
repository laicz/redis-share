package com.zhou.redisshare.model;

import java.util.List;

/**
 * Created by zhoumb on 2019/4/9
 */
public class User {
    private String id;
    private String username;
    private String password;
    private UserExtension userExtension;
    private List<UserExtension> userExtensionList;
    private UserExtension[] userExtensions;
}
