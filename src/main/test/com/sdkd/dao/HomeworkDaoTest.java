package com.sdkd.dao;

import com.sdkd.pojo.Homework;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by zhiran.sun on 2017/5/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class HomeworkDaoTest {
    @Resource
    HomeworkDao homeworkDao;

    @Test
    public void testHome() {
        homeworkDao.selectAllHomework();
    }

    @Test
    public void testHomework(){
        Homework homework = new Homework();
        homework.setHomeworkTitle("123");
        homework.setHomeworkWriter("qwe");
        Date date = new Date();
        homework.setHomeworkDeadline(date);
        homework.setHomeworkTime(date);

        Integer i=homeworkDao.insertHomework(homework);
        System.out.println(i);
        System.out.println(homework.getHomeworkId());

    }


}
