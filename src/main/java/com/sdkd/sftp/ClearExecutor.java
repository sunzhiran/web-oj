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
public class ClearExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ClearExecutor.class);

    private String webapps;

    private String token;

    private String warName; //no .war

    public ClearExecutor(String webapps, String token, String warName) {
        this.webapps = webapps;
        this.token = token;
        this.warName = warName;
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


    public boolean clear() {
        boolean isClear = false;
        //BufferedReader reader = null;
        try {
            init();

            String cmd = "sh /opt/bin/clear.sh " + webapps + " " + token + " " + warName;
            exec.setCommand(cmd);
            exec.connect(10000);
            //reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            //String res = reader.readLine();
            String res = "yes";
            if (StringUtils.isNotBlank(res) && "yes".equalsIgnoreCase(res.trim())) {
                logger.info("执行clear脚本成功");
                isClear = true;
            }
            else {
                logger.error("执行clear脚本失败");
                isClear = false;
            }
        } catch (JSchException e) {
            logger.error("初始化exec失败");
            logger.error(e.getMessage(), e);
            isClear = false;
        } catch (Exception e) {
            logger.error("执行clear脚本失败");
            logger.error(e.getMessage(), e);
            isClear = false;
        } finally {
//            try {
//                if (reader != null)
//                    reader.close();
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
            close();
        }
        return isClear;
    }
}
