package com.sdkd.dao;

import com.sdkd.pojo.RBACAccess;

import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 21:21 2017/12/7.
 */
public interface AccessDao {

    List<RBACAccess> selectAllAccess();
    int insertAccess(RBACAccess rbacAccess);
    RBACAccess selectAccessByAccessTitle(String accessTitle);
    RBACAccess selectByAccessId(int accessId);
}
