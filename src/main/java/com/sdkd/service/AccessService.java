package com.sdkd.service;

import com.sdkd.pojo.RBACAccess;
import com.sdkd.pojo.RBACRoleAccess;

import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 21:18 2017/12/7.
 */
public interface AccessService {
    List<RBACAccess> getAllAccesses();
    int addAccess(RBACAccess rbacAccess);
    RBACAccess selectAccessByAccessTitle(String accessTitle);
    List<RBACRoleAccess> selectRoleAccessByRoleId(int roleId);
}
