package com.sdkd.dao;

import com.sdkd.pojo.HwStu;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public interface HwStuDao {
    List<HwStu> selectHomeworkByHomeworkId(Integer hwId);
    List<HwStu> selectHomeworkByUserId(String userId);
    HwStu selectHwStuById(Integer hwId);
    Integer insertHwStu(HwStu hwStu);
    int  updateHwStuSim(HwStu hwStu);
}
