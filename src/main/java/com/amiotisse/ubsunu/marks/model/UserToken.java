package com.amiotisse.ubsunu.marks.model;

/**
 * @author himna
 * @since 4/16/2017.
 */
public class UserToken {

    private String id ;
    private String userName ;
    private UserType userType ;

    public UserToken(
            String id,
            String userName,
            UserType userType
    ) {
        this.id = id;
        this.userName = userName;
        this.userType = userType;
    }

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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                '}';
    }
}
