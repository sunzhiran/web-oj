package com.sdkd.dao;

import com.sdkd.pojo.RBACUser;

import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 21:35 2017/12/6.
 */
public interface RBACUserDao {
    RBACUser selectRBACUserByUserId(String userId);
    List<RBACUser> selectAllUser();
    int insertUser(RBACUser user);
    int updateUserSelf(RBACUser user);
}
