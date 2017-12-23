package com.sdkd.service.impl;

import com.sdkd.dao.ManagerDao;
import com.sdkd.pojo.Manager;
import com.sdkd.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhiran.sun on 2017/5/10.
 */
@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private ManagerDao managerDao;

    public Manager selectManagerByUserId(String managerId) {
        return managerDao.selectManagerByUserId(managerId);
    }

    public int updateManagerById(Manager manager) {
        return managerDao.updateManagerById(manager);
    }
}
