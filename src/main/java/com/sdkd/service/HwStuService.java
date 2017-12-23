package com.sdkd.service;

import com.sdkd.pojo.HwStu;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public interface HwStuService {
    List<HwStu> selectHomeworkInfoByHomeworkId(Integer hwId);
    List<HwStu> selectHomeworkByUserId(String userId);
    HwStu selectHwStuById(Integer hwId);
    HwStu setHwStu(HwStu hwStu);
    void similarityJudge(HwStu hwStu);
}
