package com.sdkd.dao;

import com.sdkd.pojo.Teacher;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/9.
 */
public interface TeacherDao {
    List<Teacher> selectAllTeachers();
    Teacher selectTeacherByUserId(String teacherId);
    int updateTeacherById(Teacher teacher);
    int insertTeacher(Teacher teacher);
    int updateTeacherByManager(Teacher teacher);
}
