package com.sdkd.service.impl;

import com.sdkd.dao.StuClassDao;
import com.sdkd.pojo.StuClass;
import com.sdkd.service.StuClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/10.
 */
@Service
public class StuClassServiceImpl implements StuClassService {
    @Autowired
    private StuClassDao stuClassDao;

    public List<StuClass> selectAllClass() {
        return stuClassDao.selectAllClass();
    }

    @Override
    public StuClass selectClassByClassId(String classId) {
        return stuClassDao.selectClassByUserId(classId);
    }

    @Override
    public int insertClass(StuClass stuClass) {
        return stuClassDao.insertClass(stuClass);
    }
}
