package com.himnabil.alphau.client.model;

import com.amiotisse.ubsunu.marks.model.UserType;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author himna
 * @since 4/23/2017.
 */
public class AuthRequest {

    private String appName ;
    private String userName ;
    private String password ;
    private UserType userType ;

    public AuthRequest(
            String appName,
            String userName,
            String password,
            UserType userType
    ){
        this.appName = appName;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    @JsonGetter("app_name")
    public String getAppName() {
        return appName;
    }
    @JsonSetter("app_name")
    public void setAppName(String appName) {
        this.appName = appName;
    }
    @JsonGetter("user_name")
    public String getUserName() {
        return userName;
    }
    @JsonSetter("user_name")
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonGetter("password")
    public String getPassword() {
        return password;
    }
    @JsonSetter("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "appName='" + appName + '\'' +
                ", userName='" + userName + '\'' +
                ", (hash)password='" + password.hashCode() + '\'' +
                ", userType=" + userType +
                '}';
    }
}
