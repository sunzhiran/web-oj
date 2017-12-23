package com.sdkd.utils;

import java.io.File;

/**
 * Created by zhiran.sun on 2017/5/9.
 */
public class MyFile {
    public static void removedir(File file)
    {
        File[] files=file.listFiles();
        for(File f:files)
        {
            if(f.isDirectory())//递归调用
            {
                removedir(f);
            }
            else {
                f.delete();
            }
        }
        //一层目录下的内容都删除以后，删除掉这个文件夹
        file.delete();
    }
}
