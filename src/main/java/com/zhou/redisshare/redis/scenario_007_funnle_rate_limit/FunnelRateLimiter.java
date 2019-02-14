package com.zhou.redisshare.redis.scenario_007_funnle_rate_limit;

/**
 * 漏斗流量算法
 * Created by zhoumb on 2019/1/4
 */
public class FunnelRateLimiter {

    static class Funnel {
        int capacity;
        float leakingRate;
        int leftQuota;
        long leakingTs;

        public Funnel(int capacity, float leakingRate, int leftQuota, long leakingTs) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = leftQuota;
            this.leakingTs = leakingTs;
        }

        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;   //间隔时间
            int deltaQuota = (int) (deltaTs * leakingRate);

        }
    }

}
