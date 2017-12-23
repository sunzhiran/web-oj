package com.sdkd.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sdkd.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public class UploadExecutor {

    private static final Logger logger = LoggerFactory.getLogger(UploadExecutor.class);

    private InputStream src; //对接sftp上传到服务器

    private String dst;

    public UploadExecutor(InputStream src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    private ChannelSftp sftp;

    private Session sshSession;

    private void init() throws JSchException {
        JSch jsch = new JSch();
        sshSession = jsch.getSession(Constants.SFTP_USER, Constants.SFTP_HOST, Constants.SFTP_PORT);
        sshSession.setPassword(Constants.SFTP_PASSWORD);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect(10000);
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
    }

    private void close() {
        sftp.disconnect();
        sshSession.disconnect();
    }


    public boolean upload() {
        boolean isUpload = false;
        try {
            init();
            sftp.put(src, dst);
            logger.info("war包上传成功");
            isUpload = true;
        } catch (JSchException e) {
            logger.error("初始化sftp失败");
            logger.error(e.getMessage(), e);
            isUpload = false;
        } catch (SftpException e) {
            logger.error("上传war包失败");
            logger.error(e.getMessage(), e);
            isUpload = false;
        } finally {
            close();
        }
        return isUpload;
    }

}
