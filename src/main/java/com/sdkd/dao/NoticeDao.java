package com.sdkd.dao;

import com.sdkd.pojo.Notice;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/8.
 */
public interface NoticeDao {
    List<Notice> selectAllNotices();
    int insertNotice(Notice notice);
}
