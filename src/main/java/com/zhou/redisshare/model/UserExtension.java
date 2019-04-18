package com.zhou.redisshare.model;

/**
 * Created by zhoumb on 2019/4/10
 */
public class UserExtension {
    private String userId;
    private String cardId;
    private String companyId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
