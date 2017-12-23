package com.sdkd.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/17.
 */
public class IOUtil {
    public static double readFileByLines(String fileName) {
        int fileNew = 0;
        int fileOld = 0;
        double result =0;
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            boolean isSim = false;
            String simFile ="";
            List<Integer> simNum = new ArrayList<Integer>();
            int simMaxNum =0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                // System.out.println("line " + line + ": " + tempString);
                if(tempString.equals("File |: new/old separator")){
                    fileNew = line-1;
                }
                else if(tempString.equals("")){
                    fileOld = line -fileNew -3;
                    isSim = true;
                    continue;
                }
                if(isSim){
                    String[] s = tempString.split(" ");
                    if(!simFile.equals(s[0])){
                        simFile = s[0];
                        simNum.add(simMaxNum);
                        simMaxNum =Integer.valueOf(s[3]);
                    }
                    else{
                        simMaxNum = simMaxNum>Integer.valueOf(s[3])?simMaxNum : Integer.valueOf(s[3]);
                    }
                }
                line++;
            }
            double sum =0;
            simNum.add(simMaxNum);
            System.out.println(fileNew);
            System.out.println(fileOld);
            for(int i=1;i<simNum.size();i++){
                sum+=simNum.get(i);
                System.out.println(i+" "+simNum.get(i));
            }

            if(fileNew != 0){
                result = sum/fileNew;
            }
            System.out.println(result);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return result;
    }


}
