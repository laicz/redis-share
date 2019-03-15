package com.zhou.redisshare.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhoumb on 2019/3/8
 */
@Service
public class TransactionService {

    @Transactional
    public void save(String str) {
        System.out.println("执行save = " + str);
    }
}
