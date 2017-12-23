package com.sdkd.service.impl;

import com.sdkd.dao.UserDao;
import com.sdkd.pojo.StuClass;
import com.sdkd.pojo.User;
import com.sdkd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/4/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StuClassServiceImpl stuClassServiceImpl;

    public List<User> getAllUsers() {
        return userDao.selectAllUser();
    }

    public User getUserByUserId(String userId) {
        return userDao.selectUserByUserId(userId);
    }

    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    public int updateUserById(User user) {
        return userDao.updateUserByUserId(user);
    }

    public List<User> getUserByPartUserName(String userName) {
        return userDao.selectUserByPartUserName("%"+userName+"%");
    }
    public List<StuClass> selectAllClass() {
        return stuClassServiceImpl.selectAllClass();
    }

    public int updateUserSelf(User user) {
        return userDao.updateUserSelf(user);
    }

    public List<User> selectUserByClassId(String classId) {
        return userDao.selectUserByClassId(classId);
    }

    public List<User> selectNoCompletedUserByHomeworkId(Integer homeworkId) {
        return userDao.selectNoCompletedUserByHomeworkId(homeworkId);
    }
}
