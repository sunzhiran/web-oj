package com.sdkd.dao;

import com.sdkd.pojo.RBACRole;

import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 18:16 2017/12/7.
 */
public interface RoleDao {
    List<RBACRole> selectAllRole();

    int insertRole(RBACRole rbacRole);

    RBACRole selectRoleByRoleName(String roleName);

}
