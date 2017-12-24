package com.sdkd.pojo;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 21:20 2017/11/30.
 */
public class RBACRole {
    private Integer roleId;
    private String roleName;
    private Integer userStatus;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}
