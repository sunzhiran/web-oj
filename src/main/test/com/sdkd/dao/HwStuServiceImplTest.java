package com.sdkd.dao;

import com.sdkd.pojo.HwStu;
import com.sdkd.service.HwStuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zhiran.sun on 2017/5/20.
 */
//wrong
@RunWith(SpringJUnit4ClassRunner.class)
public class HwStuServiceImplTest {

    @Resource
    HwStuService hwStuService;

    @Test
    public void testSimilarityJudge(){
        HwStu hwStu = hwStuService.selectHwStuById(41);
        hwStuService.similarityJudge(hwStu);


    }


}
