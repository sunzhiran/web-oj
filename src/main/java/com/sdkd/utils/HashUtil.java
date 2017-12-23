package com.sdkd.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * Created by zhiran.sun on 2017/5/11.
 */
public class HashUtil {
    /**
     * 把字符串hash成数字
     * @param str
     * @return
     */
    public static long hashStrToLong(String str) {
        if (StringUtils.isEmpty(str))
            return 0;
        int len = str.length();
        long h = 0, g;
        int index = 0;
        while (index < len) {
            h = (h << 4) + str.charAt(index++);

            if ((g = (h & 0xF0000000)) == 1) {
                h = h ^ (g >> 24);
                h = h ^ g;
            }
        }
        return h;
    }


    /**
     * 对字符串进行md5加密
     * @param str
     * @return
     */
    public static String md5(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
