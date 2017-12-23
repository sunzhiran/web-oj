package com.sdkd.dao;

import com.sdkd.pojo.Manager;

import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/10.
 */
public interface ManagerDao {
    Manager selectManagerByUserId(String managerId);
    int updateManagerById(Manager manager);
}
