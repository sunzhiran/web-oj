package com.sdkd.dao;

import com.sdkd.pojo.RBACUserRole;

import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 20:24 2017/12/7.
 */
public interface UserRoleDao {

    int insertUserRole(RBACUserRole rbacUserRole);
    List<RBACUserRole> selectUserRole(String userId);
}
