package com.sdkd.enums;

import com.sdkd.utils.Constants;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public enum TomcatEnum {

    TOMCAT_0(0, 8080, Constants.TOMCAT_0),
    TOMCAT_1(1, 8081, Constants.TOMCAT_1),
    TOMCAT_2(2, 8082, Constants.TOMCAT_2),
    TOMCAT_3(3, 8083, Constants.TOMCAT_3),
    TOMCAT_4(4, 8084, Constants.TOMCAT_4);

    private int id;

    private int port;

    private String webapps;

    TomcatEnum(int id, int port, String webapps) {
        this.id = id;
        this.port = port;
        this.webapps = webapps;
    }

    public int getId() {
        return id;
    }

    public int getPort() {
        return port;
    }

    public String getWebapps() {
        return webapps;
    }

    public static TomcatEnum idOf(int id) {
        for (TomcatEnum tomcatEnum : values()) {
            if (tomcatEnum.getId() == id) {
                return tomcatEnum;
            }
        }
        return null;
    }

}
