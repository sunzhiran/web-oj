package com.sdkd.service;

import com.sdkd.pojo.RBACAccess;
import com.sdkd.pojo.RBACRole;
import com.sdkd.pojo.RBACUser;
import com.sdkd.pojo.RBACUserRole;
import com.sdkd.pojo.StuClass;
import com.sdkd.pojo.User;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/4/22.
 */
public interface UserService {
    List<User> getAllUsers();
    User getUserByUserId(String userId);
    int addUser(User user);
    int updateUserById(User user);
    List<User> getUserByPartUserName(String userName);
    List<StuClass> selectAllClass();
    int updateUserSelf(User user);
    List<User>selectUserByClassId(String classId);
    List<User>selectNoCompletedUserByHomeworkId(Integer homeworkId);

    //User getUserByUserId(String userId);
    RBACUser getRBACUser(String userId);
    int addUser(RBACUser user);
    int updateUserSelf(RBACUser user);
    List<RBACRole> selectAllRole();
    int addUserRole(RBACUserRole rbacUserRole);

    List<RBACAccess> getAccessByUser(String userId);
}
