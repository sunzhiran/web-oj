package com.sdkd.dao;

import com.sdkd.pojo.User;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/4/22.
 */
public interface UserDao {
    List<User> selectAllUser();
    User selectUserByUserId(String userId);
    User selectUserByUserName(String userName);
    List<User> selectUserByPartUserName(String userName);
    int insertUser(User user);
    int updateUserByUserId(User user);
    int updateUserSelf(User user);
    List<User>selectUserByClassId(String classId);
    List<User>selectNoCompletedUserByHomeworkId(Integer homeworkId);
}
