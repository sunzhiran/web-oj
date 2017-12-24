package com.sdkd.service;

import com.sdkd.pojo.RBACRole;
import com.sdkd.pojo.RBACRoleAccess;
import com.sdkd.pojo.RBACUserRole;

import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 18:17 2017/12/7.
 */
public interface RoleService {

    List<RBACRole> getAllRoles();
    int addRole(RBACRole rbacRole);
    RBACRole selectRoleByRoleName(String roleName);
    int addRoleAccess(RBACRoleAccess rbacRoleAccess);
    List<RBACRoleAccess> selectRoleAccessByRoleId(Integer rId);
    List<RBACUserRole> getRBACUserRole(String userId);
}

