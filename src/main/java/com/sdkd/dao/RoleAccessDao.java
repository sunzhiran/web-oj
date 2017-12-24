package com.sdkd.dao;

import com.sdkd.pojo.RBACRoleAccess;

import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 20:57 2017/12/21.
 */
public interface RoleAccessDao {
    int insertRoleAccess(RBACRoleAccess rbacRoleAccess);
    List<RBACRoleAccess> selectRoleAccessByRoleId(Integer rId);
}
