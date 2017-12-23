package com.sdkd.service.impl;

import com.sdkd.dao.HomeworkDao;
import com.sdkd.pojo.Homework;
import com.sdkd.pojo.HwStu;
import com.sdkd.pojo.StuClass;
import com.sdkd.pojo.Teacher;
import com.sdkd.pojo.User;
import com.sdkd.service.HomeworkService;
import com.sdkd.service.HwStuService;
import com.sdkd.service.StuClassService;
import com.sdkd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/4/27.
 */
@Service
public class HomeworkServiceImpl implements HomeworkService{
    @Autowired
    private HomeworkDao homeworkDao;

    @Autowired
    private StuClassService stuClassService;

    @Autowired
    private HwStuService hwStuService;

    @Autowired
    private UserService userService;

    public List<Homework> getAllHomework() {
        return homeworkDao.selectAllHomework();
    }

    public Homework addHomework(Homework homework){
        homeworkDao.insertHomework(homework);
        return homework;
    }

    public Homework selectHomeworkById(Integer homeworkId) {
        return homeworkDao.selectHomeworkById(homeworkId);
    }

    public int updateHomeworkById(Homework homework) {
        return homeworkDao.updateHomeworkById(homework);
    }

    public int deleteHomeworkById(Integer homeworkId) {
        return homeworkDao.deleteHomeworkById(homeworkId);
    }

    public List<Homework> selectHomeworkByPartTitle(String homeworkTitle) {
        return homeworkDao.selectHomeworkByPartTitle("%"+homeworkTitle+"%");
    }

    public int updateHomeworkAttachment(Homework homework) {
        return homeworkDao.updateHomeworkAttachment(homework);
    }

    public List<Homework> selectHomeworkByTeacherId(Teacher teacher) {
        return homeworkDao.selectHomeworkByTeacherId(teacher);
    }

    public List<StuClass> selectAllClass() {
        return stuClassService.selectAllClass();
    }

    public List<Homework> selectHomeworkByClassId(String classId) {
        return homeworkDao.selectHomeworkByClassId(classId);
    }

    public List<User> selectNoCompletedUserByHomeworkId(Integer homeworkId) {
        return userService.selectNoCompletedUserByHomeworkId(homeworkId);
    }

    public List<HwStu> selectHomeworkInfoByHomeworkId(Integer hwId) {
        return hwStuService.selectHomeworkInfoByHomeworkId(hwId);
    }

    public List<HwStu> selectHomeworkByUserId(String userId) {
        return hwStuService.selectHomeworkByUserId(userId);
    }


}
