package com.sdkd.pojo;

import java.util.Date;

/**
 * Created by zhiran.sun on 2017/4/27.
 */
public class Homework {
    private Integer homeworkId;
    private String homeworkTitle;
    private String homeworkContent;
    private Date homeworkTime;
    private Date homeworkDeadline;
    private String homeworkWriter;
    private int homeworkIsDelete;
    private String homeworkAttachment;
    private String homeworkClass;
    private String homeworkClassName;
    private String homeworkWriterName;

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public Date getHomeworkTime() {
        return homeworkTime;
    }

    public void setHomeworkTime(Date homeworkTime) {
        this.homeworkTime = homeworkTime;
    }

    public String getHomeworkWriter() {
        return homeworkWriter;
    }

    public void setHomeworkWriter(String homeworkWriter) {
        this.homeworkWriter = homeworkWriter;
    }

    public int getHomeworkIsDelete() {
        return homeworkIsDelete;
    }

    public void setHomeworkIsDelete(int homeworkIsDelete) {
        this.homeworkIsDelete = homeworkIsDelete;
    }

    public Date getHomeworkDeadline() {
        return homeworkDeadline;
    }

    public void setHomeworkDeadline(Date homeworkDeadline) {
        this.homeworkDeadline = homeworkDeadline;
    }

    public String getHomeworkAttachment() {
        return homeworkAttachment;
    }

    public void setHomeworkAttachment(String homeworkAttachment) {
        this.homeworkAttachment = homeworkAttachment;
    }

    public String getHomeworkClass() {
        return homeworkClass;
    }

    public void setHomeworkClass(String homeworkClass) {
        this.homeworkClass = homeworkClass;
    }

    public String getHomeworkClassName() {
        return homeworkClassName;
    }

    public void setHomeworkClassName(String homeworkClassName) {
        this.homeworkClassName = homeworkClassName;
    }

    public String getHomeworkWriterName() {
        return homeworkWriterName;
    }

    public void setHomeworkWriterName(String homeworkWriterName) {
        this.homeworkWriterName = homeworkWriterName;
    }
}

