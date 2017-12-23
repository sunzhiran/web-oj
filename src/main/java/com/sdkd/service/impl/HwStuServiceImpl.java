package com.sdkd.service.impl;

import com.sdkd.dao.HwStuDao;
import com.sdkd.pojo.HwStu;
import com.sdkd.service.HwStuService;
import com.sdkd.utils.IOUtil;
import com.sdkd.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/11.
 */

@Service
public class HwStuServiceImpl implements HwStuService {

    @Autowired
    private HwStuDao hwStuDao;

    public List<HwStu> selectHomeworkInfoByHomeworkId(Integer hwId) {
        return hwStuDao.selectHomeworkByHomeworkId(hwId);
    }

    public List<HwStu> selectHomeworkByUserId(String userId) {
        return hwStuDao.selectHomeworkByUserId(userId);
    }

    @Override
    public HwStu selectHwStuById(Integer hwId) {
        return hwStuDao.selectHwStuById(hwId);
    }

    @Override
    public HwStu setHwStu(HwStu hwStu) {
        hwStuDao.insertHwStu(hwStu);
        return hwStu;
    }

    @Override
    public void similarityJudge(HwStu hwStu) {
        Integer homeworkId = hwStu.getHwId();
        List<HwStu> hwStus = hwStuDao.selectHomeworkByHomeworkId(homeworkId);
        String pathExe = "H:\\bishe\\web-oj\\sim_java.exe";
        String filePath = hwStu.getHwStuUrl();//根目录
        String pathRe = filePath+"/re";
        String fileZip = hwStu.getCodeUrl();
        String fileUnZip = filePath+"/unZip";
        ZipUtil.unZiFiles(fileZip, fileUnZip);
        double maxSim =0.0;
        String simStuId = null;
        boolean isChange = false;
        for(int i=0;i<hwStus.size();i++){
            if(!hwStus.get(i).getStuId().equals(hwStu.getStuId()) ){
                String file2 = hwStus.get(i).getHwStuUrl()+"/unZip";
                String stuId = hwStus.get(i).getStuId();
                try {
                    System.out.println(fileUnZip);
                    Process exec = Runtime.getRuntime().exec(pathExe + " -peu -R -o " + pathRe + stuId + ".txt -S " + fileUnZip + "/* \"|\" " + file2 + "/*");
                    exec.waitFor();//等待执行结果
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                File file = new File(pathRe+stuId+".txt");
                if(file.exists()){
                    double sim = IOUtil.readFileByLines(pathRe+stuId+".txt");
                    if(sim>maxSim){
                        isChange =true;
                        maxSim = sim;
                        simStuId = stuId;
                    }
                }

            }
        }
        if(isChange){
            hwStu.setHwStuSim(maxSim);
            hwStu.setHwStuSimId(simStuId);
            hwStuDao.updateHwStuSim(hwStu);
        }


        /*File file = new File(pathRe);
        if (file.exists()) {
            File[] fileList = file.listFiles();
            for(int i = 0 ;i<fileList.length;i++){
                double sim = IOUtil.readFileByLines(fileList[i].getAbsolutePath());

            }
        }*/
    }
}
