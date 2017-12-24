package com.sdkd.service.impl;

import com.sdkd.dao.AccessDao;
import com.sdkd.dao.RoleAccessDao;
import com.sdkd.pojo.RBACAccess;
import com.sdkd.pojo.RBACRoleAccess;
import com.sdkd.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 21:19 2017/12/7.
 */

@Service
public class AccessServiceImpl implements AccessService{

    @Autowired
    private AccessDao accessDao;
    @Autowired
    private RoleAccessDao roleAccessDao;

    @Override
    public List<RBACAccess> getAllAccesses() {
        return accessDao.selectAllAccess();
    }

    @Override
    public int addAccess(RBACAccess rbacAccess) {
        return accessDao.insertAccess(rbacAccess);
    }

    @Override
    public RBACAccess selectAccessByAccessTitle(String accessTitle) {
        return accessDao.selectAccessByAccessTitle(accessTitle);
    }

    @Override
    public List<RBACRoleAccess> selectRoleAccessByRoleId(int roleId) {
        return roleAccessDao.selectRoleAccessByRoleId(roleId);
    }
}
