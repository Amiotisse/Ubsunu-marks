package com.himnabil.alphau.client.model;

import com.amiotisse.ubsunu.marks.model.UserType;

/**
 * @author himna
 * @since 4/23/2017.
 */
public class UserSubscriptionResponse {

    private String id;
    private String userName;
    private String appName ;
    private UserType userType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
