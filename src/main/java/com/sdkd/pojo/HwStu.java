package com.sdkd.pojo;

import java.util.Date;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public class HwStu {
    private Integer hwStuId;
    private String stuId;
    private Integer hwId;
    private String paperUrl;
    private String codeUrl;
    private String projectUrl;
    private Date uploadTime;
    private String stuName;
    private String hwTitle;
    private String hwStuUrl;
    private double hwStuSim;
    private String hwStuSimId;
    private String sqlUrl;

    public Integer getHwStuId() {
        return hwStuId;
    }

    public void setHwStuId(Integer hwStuId) {
        this.hwStuId = hwStuId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Integer getHwId() {
        return hwId;
    }

    public void setHwId(Integer hwId) {
        this.hwId = hwId;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getHwTitle() {
        return hwTitle;
    }

    public void setHwTitle(String hwTitle) {
        this.hwTitle = hwTitle;
    }

    public String getHwStuUrl() {
        return hwStuUrl;
    }

    public void setHwStuUrl(String hwStuUrl) {
        this.hwStuUrl = hwStuUrl;
    }

    public double getHwStuSim() {
        return hwStuSim;
    }

    public void setHwStuSim(double hwStuSim) {
        this.hwStuSim = hwStuSim;
    }

    public String getHwStuSimId() {
        return hwStuSimId;
    }

    public void setHwStuSimId(String hwStuSimId) {
        this.hwStuSimId = hwStuSimId;
    }

    public String getSqlUrl() {
        return sqlUrl;
    }

    public void setSqlUrl(String sqlUrl) {
        this.sqlUrl = sqlUrl;
    }
}
