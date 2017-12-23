package com.sdkd.enums;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public enum SFTPStat {

    SUCCESS(1, "auto deploy success"),
    UPLOAD_FAILED(2, "upload file failed"),
    EXEC_CLEAR_FAILED(3, "clear the dir failed before upload"),
    EXEC_DEPLOY_FAILED(4, "deploy the project failed after upload");


    private int status;

    private String info;


    SFTPStat(int status, String info) {
        this.status = status;
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public static SFTPStat codeOf(int status) {
        for (SFTPStat stat : values()) {
            if (stat.getStatus() == status) {
                return stat;
            }
        }
        return null;
    }

}
