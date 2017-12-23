package com.sdkd.pojo;

import java.util.Date;

/**
 * Created by zhiran.sun on 2017/5/8.
 */
public class Notice {
    private int noticeId;
    private String noticeContent;
    private String noticeWriter;
    private Date noticeTime;
    private String noticeWriterName;

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeWriter() {
        return noticeWriter;
    }

    public void setNoticeWriter(String noticeWriter) {
        this.noticeWriter = noticeWriter;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNoticeWriterName() {
        return noticeWriterName;
    }

    public void setNoticeWriterName(String noticeWriterName) {
        this.noticeWriterName = noticeWriterName;
    }
}
