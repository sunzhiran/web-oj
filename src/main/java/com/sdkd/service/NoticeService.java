package com.sdkd.service;

import com.sdkd.pojo.Notice;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/8.
 */
public interface NoticeService {
    List<Notice> getAllNotices();
    int addNotice(Notice notice);
}
