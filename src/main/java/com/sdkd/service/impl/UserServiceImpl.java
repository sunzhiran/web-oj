package com.sdkd.service.impl;

import com.sdkd.dao.AccessDao;
import com.sdkd.dao.RBACUserDao;
import com.sdkd.dao.RoleAccessDao;
import com.sdkd.dao.RoleDao;
import com.sdkd.dao.UserDao;
import com.sdkd.dao.UserRoleDao;
import com.sdkd.pojo.RBACAccess;
import com.sdkd.pojo.RBACRole;
import com.sdkd.pojo.RBACRoleAccess;
import com.sdkd.pojo.RBACUser;
import com.sdkd.pojo.RBACUserRole;
import com.sdkd.pojo.StuClass;
import com.sdkd.pojo.User;
import com.sdkd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiran.sun on 2017/4/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RBACUserDao rbacUserDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleAccessDao roleAccessDao;

    @Autowired
    private AccessDao accessDao;

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

    public int addUser(RBACUser user) {
        return rbacUserDao.insertUser(user);
    }

    public int updateUserSelf(RBACUser user) {
        return rbacUserDao.updateUserSelf(user);
    }


    @Override
    public List<RBACRole> selectAllRole() {
        return roleDao.selectAllRole();
    }

    @Override
    public int addUserRole(RBACUserRole rbacUserRole) {
        return userRoleDao.insertUserRole(rbacUserRole);
    }

    @Override
    public RBACUser getRBACUser(String userId) {
        return rbacUserDao.selectRBACUserByUserId(userId);
    }

    @Override
    public List<RBACAccess> getAccessByUser(String userId) {
        List<RBACAccess> accesses = new ArrayList<>();
        List<RBACUserRole> rbacUserRoles = userRoleDao.selectUserRole(userId);
        if (rbacUserRoles == null || rbacUserRoles.size() <= 0)
            return null;
        for (RBACUserRole userRole : rbacUserRoles) {
            List<RBACRoleAccess> roleAccesses = roleAccessDao.selectRoleAccessByRoleId(userRole.getrId());
            if (roleAccesses == null || roleAccesses.size() <= 0)
                return null;
            for (RBACRoleAccess roleAccess : roleAccesses) {
                RBACAccess access = accessDao.selectByAccessId(roleAccess.getaId());
                accesses.add(access);
            }
        }
        if (accesses == null || accesses.size() <= 0)
            return null;
        else
            return accesses;
    }
}
