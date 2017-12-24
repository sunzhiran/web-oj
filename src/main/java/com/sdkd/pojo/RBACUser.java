package com.sdkd.pojo;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 21:14 2017/11/30.
 */
public class RBACUser {
    private String userId;
    private String userName;
    private String userPassword;
    private String userClass;
    private Integer userIsAdmin;
    private Integer userStatus;
    private String userClassName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public Integer getUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(Integer userIsAdmin) {
        this.userIsAdmin = userIsAdmin;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }


    public String getUserClassName() {
        return userClassName;
    }

    public void setUserClassName(String userClassName) {
        this.userClassName = userClassName;
    }
}
