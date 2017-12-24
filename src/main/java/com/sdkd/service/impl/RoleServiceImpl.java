package com.sdkd.service.impl;

import com.sdkd.dao.RoleAccessDao;
import com.sdkd.dao.RoleDao;
import com.sdkd.dao.UserRoleDao;
import com.sdkd.pojo.RBACRole;
import com.sdkd.pojo.RBACRoleAccess;
import com.sdkd.pojo.RBACUserRole;
import com.sdkd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 18:18 2017/12/7.
 */

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleAccessDao roleAccessDao;

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public List<RBACRole> getAllRoles() {
        return roleDao.selectAllRole();
    }

    @Override
    public int addRole(RBACRole rbacRole) {
        return roleDao.insertRole(rbacRole);
    }

    @Override
    public RBACRole selectRoleByRoleName(String roleName) {
        return roleDao.selectRoleByRoleName(roleName);
    }

    @Override
    public int addRoleAccess(RBACRoleAccess rbacRoleAccess) {
        return roleAccessDao.insertRoleAccess(rbacRoleAccess);
    }

    @Override
    public List<RBACRoleAccess> selectRoleAccessByRoleId(Integer rId) {
        return roleAccessDao.selectRoleAccessByRoleId(rId);
    }

    @Override
    public List<RBACUserRole> getRBACUserRole(String userId) {
        return userRoleDao.selectUserRole(userId);
    }
}
