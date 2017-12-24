package com.sdkd.pojo;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 21:23 2017/11/30.
 */
public class RBACUserRole {
    private Integer urId;
    private String uId;
    private Integer rId;
    private Integer status;

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
