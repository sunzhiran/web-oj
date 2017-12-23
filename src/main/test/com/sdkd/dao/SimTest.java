package com.sdkd.dao;

import java.io.IOException;

/**
 * Created by zhiran.sun on 2017/5/16.
 */
public class SimTest {

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("sim_java.exe -peu -o ret.txt -S new/* \"|\" old/*");
    }
}
