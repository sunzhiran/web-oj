package com.sdkd.dao;

import com.sdkd.pojo.Homework;
import com.sdkd.pojo.Teacher;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/4/27.
 */
public interface HomeworkDao {
    List<Homework> selectAllHomework();
    List<Homework> selectHomeworkByClassId(String classId);
    List<Homework> selectHomeworkByPartTitle(String homeworkTitle);
    Integer insertHomework(Homework homework);
    Homework selectHomeworkById(Integer homeworkId);
    int updateHomeworkById(Homework homework);
    int updateHomeworkAttachment(Homework homework);
    int deleteHomeworkById(Integer homeworkId);
    List<Homework> selectHomeworkByTeacherId(Teacher teacher);
}
