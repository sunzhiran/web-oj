package com.sdkd.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.sdkd.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public class DeployExecutor {

    private static final Logger logger = LoggerFactory.getLogger(DeployExecutor.class);

    private String projectDir;

    private String warFile; //has .war

    public DeployExecutor(String projectDir, String warFile) {
        this.projectDir = projectDir;
        this.warFile = warFile;
    }

    private ChannelExec exec;

    private Session sshSession;

    private void init() throws JSchException {
        JSch jsch = new JSch();
        sshSession = jsch.getSession(Constants.SFTP_USER, Constants.SFTP_HOST, Constants.SFTP_PORT);
        sshSession.setPassword(Constants.SFTP_PASSWORD);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect(10000);
        Channel channel = sshSession.openChannel("exec");
        exec = (ChannelExec) channel;
    }

    private void close() {
        exec.disconnect();
        sshSession.disconnect();
    }


    public boolean deploy() {
        boolean isDeploy = false;
//        BufferedReader reader = null;
        try {
            init();

            String cmd = "sh /opt/bin/deploy.sh " + projectDir + " " + warFile;
            exec.setCommand(cmd);
            exec.connect(10000);
//            reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
//            String res = reader.readLine();
            String res = "yes";
            if (StringUtils.isNotBlank(res) && "yes".equalsIgnoreCase(res.trim())) {
                logger.info("执行deploy脚本成功");
                isDeploy = true;
            }
            else {
                logger.error("执行deploy脚本失败");
                isDeploy = false;
            }


        } catch (JSchException e) {
            logger.error("初始化exec失败");
            logger.error(e.getMessage(), e);
            isDeploy = false;
        } catch (Exception e) {
            logger.error("执行deploy脚本失败");
            logger.error(e.getMessage(), e);
            isDeploy = false;
        } finally {
//            try {
//                if (reader != null)
//                    reader.close();
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
            close();
        }
        return isDeploy;
    }
}
