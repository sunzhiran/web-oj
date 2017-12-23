package com.sdkd.pojo;

/**
 * Created by zhiran.sun on 2017/4/22.
 */
public class User {
    private String userId;//xuehao
    private String userName;
    private String userPassword;
    private String userClass;
    private Integer userType;
    private String userSex;
    private String userClassName;
    private String userNote;

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null: userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null?null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null: userPassword.trim();
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass == null ? null : userClass.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserClassName() {
        return userClassName;
    }

    public void setUserClassName(String userClassName) {
        this.userClassName = userClassName;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userClass='" + userClass + '\'' +
                ", userType=" + userType +
                ", userSex='" + userSex + '\'' +
                '}';
    }
}
