package com.sdkd.service.impl;

import com.sdkd.enums.TomcatEnum;
import com.sdkd.service.DeployService;
import com.sdkd.sftp.AutoDeployTask;
import com.sdkd.sftp.DatabaseExecutor;
import com.sdkd.utils.Constants;
import com.sdkd.utils.HashUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
@Service
public class DeployServiceImpl implements DeployService {

    @Override
    public String autoDeploy(InputStream in, String token, String fileName) {

        //负载均衡
        long hash = HashUtil.hashStrToLong(token);
        int id = (int)(hash % Constants.TOMCAT_SIZE);
        TomcatEnum tomcatEnumFu = TomcatEnum.idOf(id);
//        ExecutorService pool = Executors.newFixedThreadPool(10);
        //默认tomcat0
        TomcatEnum tomcat = TomcatEnum.TOMCAT_0;
//        pool.submit(new AutoDeployTask(in, token, fileName, tomcat));
        (new AutoDeployTask(in, token, fileName, tomcat)).autoDeploy();
        String warName = fileName.substring(0, fileName.length()-4); //no .war
        //拼接访问路径
        String projectUrl = "http://" + Constants.SFTP_HOST + ":" + tomcat.getPort() + "/" + warName + "/";
        return projectUrl;
    }

    @Override
    public Boolean autoDeployDB(String dbName, String sqlFilePath) {

        if (StringUtils.isBlank(dbName))
            return false;
        //只取字母数字下划线
        StringBuilder nameBuilder = new StringBuilder();
        for (char ch : dbName.toCharArray()) {
            if (ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == '_') {
                nameBuilder.append(ch);
            }
        }
        dbName = nameBuilder.toString();

        (new DatabaseExecutor(dbName, sqlFilePath)).deployDB();
        return true;
    }


//    public static void main(String[] args) throws FileNotFoundException {
//
//        System.out.println(autoDeploy(new FileInputStream("C:\\Users\\dell\\Desktop\\war-test.war"), "5b956cef344a11e7a39974867a2f3745", "war-test.war"));
//    }
}
