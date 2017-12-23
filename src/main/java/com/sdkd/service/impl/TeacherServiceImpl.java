package com.sdkd.service.impl;

import com.sdkd.dao.TeacherDao;
import com.sdkd.pojo.Teacher;
import com.sdkd.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/9.
 */

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;

    public Teacher selectTeacherByUserId(String teacherId) {
        return teacherDao.selectTeacherByUserId(teacherId);
    }

    public int updateTeacherById(Teacher teacher) {
        return teacherDao.updateTeacherById(teacher);
    }

    @Override
    public List<Teacher> selectAllTeachers() {
        return teacherDao.selectAllTeachers();
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        return teacherDao.insertTeacher(teacher);
    }

    @Override
    public int updateTeacherByManager(Teacher teacher) {
        return teacherDao.updateTeacherByManager(teacher);
    }
}
