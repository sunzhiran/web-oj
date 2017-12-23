package com.sdkd.service.impl;

import com.sdkd.dao.NoticeDao;
import com.sdkd.pojo.Notice;
import com.sdkd.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/8.
 */
@Service
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeDao noticeDao;

    public List<Notice> getAllNotices() {
        return noticeDao.selectAllNotices();
    }

    public int addNotice(Notice notice) {
        return noticeDao.insertNotice(notice);
    }
}
