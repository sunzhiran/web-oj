package com.sdkd.pojo;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 21:26 2017/11/30.
 */
public class RBACAccess {
    private Integer accessId;
    private String accessTitle;
    private String accessURL;
    private Integer accessStatus;

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public String getAccessTitle() {
        return accessTitle;
    }

    public void setAccessTitle(String accessTitle) {
        this.accessTitle = accessTitle;
    }

    public String getAccessURL() {
        return accessURL;
    }

    public void setAccessURL(String accessURL) {
        this.accessURL = accessURL;
    }

    public Integer getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(Integer accessStatus) {
        this.accessStatus = accessStatus;
    }
}
