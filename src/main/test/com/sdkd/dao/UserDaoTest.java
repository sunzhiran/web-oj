package com.sdkd.dao;

import com.sdkd.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zhiran.sun on 2017/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {


    @Resource
    UserDao userDao;

    @Test
    public void testSelectUser() {
        User user = userDao.selectUserByUserId("2013010101");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUserId("2013010105");
        user.setUserName("丁一");
        user.setUserPassword("1234");
        user.setUserClass("");
        user.setUserType(1);
        user.setUserSex("男");
        int i = userDao.insertUser(user);
        System.out.println(i);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUserId("2013010105");
        user.setUserName("丁一");
        user.setUserPassword("1234");
        user.setUserClass("20130101");
        user.setUserType(1);
        user.setUserSex("男");
        int i = userDao.updateUserByUserId(user);
        System.out.println(i);
    }

    @Test
    public void testSim() throws IOException {
        Runtime.getRuntime().exec("sim_java.exe -peu -o ret.txt -S new/* \"|\" old/*");
    }

}