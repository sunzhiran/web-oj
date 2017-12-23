package com.sdkd.service;

import com.sdkd.pojo.Manager;

/**
 * Created by zhiran.sun on 2017/5/10.
 */
public interface ManagerService {
    Manager selectManagerByUserId(String managerId);
    int updateManagerById(Manager manager);
}
