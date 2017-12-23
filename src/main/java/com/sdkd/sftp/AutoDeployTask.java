package com.sdkd.sftp;

import com.sdkd.enums.TomcatEnum;
import com.sdkd.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public class AutoDeployTask {

        private static final Logger logger = LoggerFactory.getLogger(AutoDeployTask.class);

        private InputStream in; //in 上传文件的流

        private String token; //token 用户token

        private String fileName; //fileName war包的文件名 带.war

        private TomcatEnum tomcat;

        public AutoDeployTask(InputStream in, String token, String fileName, TomcatEnum tomcat) {
            this.in = in;
            this.token = token;
            this.fileName = fileName;
            this.tomcat = tomcat;
        }

        /**
         * 执行自动部署程序，返回访问连接。
         * @return
         */
        public String autoDeploy() {

            String webapps = tomcat.getWebapps();

            //执行clear脚本
            String warName = fileName.substring(0, fileName.length()-4); //no .war
            boolean isClear = (new ClearExecutor(webapps, token, warName)).clear();

            //上传war包
            String dst = webapps + "/" + warName + "/" +fileName;
            boolean isUpload = (new UploadExecutor(in, dst)).upload();

            //执行deploy脚本
            String projectDir = webapps + "/" + warName;
            boolean isDeploy = (new DeployExecutor(projectDir, fileName)).deploy();

            //拼接访问路径
            String projectUrl = "http://" + Constants.SFTP_HOST + ":" + tomcat.getPort() + "/" + warName + "/";
            return projectUrl;
        }

}
