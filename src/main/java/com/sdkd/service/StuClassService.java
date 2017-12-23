package com.sdkd.service;

import com.sdkd.pojo.StuClass;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/10.
 */
public interface StuClassService {
    List<StuClass> selectAllClass();
    StuClass selectClassByClassId(String classId);
    int insertClass(StuClass stuClass);
}
