package com.himnabil.alphau.client.model.builder;

import com.amiotisse.ubsunu.marks.model.UserType;
import com.himnabil.alphau.client.model.AuthRequest;

public class AuthRequestBuilder {
    private String appName;
    private String userName;
    private String password;
    private UserType userType;

    public AuthRequestBuilder setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public AuthRequestBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public AuthRequestBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public AuthRequestBuilder setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public AuthRequest build() {
        return new AuthRequest(appName, userName, password, userType);
    }
}