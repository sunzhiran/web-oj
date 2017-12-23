package com.sdkd.service;

import java.io.InputStream;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public interface DeployService {
    String autoDeploy(InputStream in, String token, String fileName);
    Boolean autoDeployDB(String dbName, String sqlFilePath);
}
