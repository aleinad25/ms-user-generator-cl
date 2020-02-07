package com.globallogic.demo.app.msusergenerator.domain.entity;


public class UserDataResponse {
    private User user;
    private UserInfo userInfo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
