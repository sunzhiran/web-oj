package com.sdkd.service;

import com.sdkd.pojo.Homework;
import com.sdkd.pojo.HwStu;
import com.sdkd.pojo.StuClass;
import com.sdkd.pojo.Teacher;
import com.sdkd.pojo.User;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/4/27.
 */
public interface HomeworkService {
    List<HwStu> selectHomeworkInfoByHomeworkId(Integer hwId);
    List<Homework> getAllHomework();
    List<Homework> selectHomeworkByPartTitle(String homeworkTitle);
    Homework addHomework(Homework homework);
    Homework selectHomeworkById(Integer homeworkId);
    int updateHomeworkById(Homework homework);
    int updateHomeworkAttachment(Homework homework);
    int deleteHomeworkById(Integer homeworkId);
    List<Homework> selectHomeworkByTeacherId(Teacher teacher);
    List<StuClass> selectAllClass();
    List<Homework> selectHomeworkByClassId(String classId);
    List<User> selectNoCompletedUserByHomeworkId(Integer homeworkId);
    List<HwStu> selectHomeworkByUserId(String userId);

}
