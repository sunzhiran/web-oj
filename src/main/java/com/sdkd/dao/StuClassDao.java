package com.sdkd.dao;

import com.sdkd.pojo.StuClass;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/2.
 */
public interface StuClassDao {
    List<StuClass> selectAllClass() ;
    StuClass selectClassByUserId(String classId);
    int insertClass(StuClass stuClass);

}
