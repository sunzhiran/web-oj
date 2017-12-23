package com.sdkd.controller;

import com.sdkd.pojo.StuClass;
import com.sdkd.pojo.Teacher;
import com.sdkd.pojo.User;
import com.sdkd.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/10.
 */

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping("showAllTeachers")
    public ModelAndView showAllTeachers(HttpServletRequest request, HttpSession session){
        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        ModelAndView modelAndView =new ModelAndView();
        List<Teacher> allTeachers = teacherService.selectAllTeachers();
        request.setAttribute("allTeachers",allTeachers);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("manager/teacher");
        return modelAndView;
    }


    @RequestMapping("addTeacher")
    public ModelAndView addTeacher(HttpServletRequest request, HttpSession session){
        String teacherId = request.getParameter("userId").trim();

        ModelAndView modelAndView = new ModelAndView();
        Teacher t = teacherService.selectTeacherByUserId(teacherId);
        if(null != t){
            modelAndView.setViewName("manager/teacher-add");
            return modelAndView;
        }
        else{
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            String userSex = request.getParameter("userSex").trim();

            Teacher teacher =new Teacher();
            teacher.setTeacherId(teacherId);
            teacher.setTeacherName(username);
            teacher.setTeacherPassword(password);
            teacher.setTeacherSex(userSex);
            int i = teacherService.insertTeacher(teacher);
            return showAllTeachers(request, session);
        }
    }

    @RequestMapping("editTeacher")
    public ModelAndView editTeacher(HttpServletRequest request, HttpSession session){
        String teacherId = request.getParameter("teacherId").trim();
        Teacher teacher = teacherService.selectTeacherByUserId(teacherId);
        ModelAndView modelAndView = new ModelAndView();
        request.setAttribute("teacher",teacher);
        modelAndView.setViewName("manager/teacher-edit");
        return modelAndView;
    }

    @RequestMapping("updateTeacher")
    public ModelAndView updateTeacher(HttpServletRequest request, HttpSession session){
        String userId = request.getParameter("userId").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String userSex = request.getParameter("userSex").trim();
        String userNote = request.getParameter("userNote").trim();

        Teacher teacher =new Teacher();
        teacher.setTeacherId(userId);
        teacher.setTeacherName(username);
        teacher.setTeacherPassword(password);
        teacher.setTeacherSex(userSex);
        teacher.setTeacherNote(userNote);

        int i = teacherService.updateTeacherByManager(teacher);
        return showAllTeachers(request, session);
    }

    @RequestMapping("setupTeacher")
    public ModelAndView setupUserInTeacher(HttpServletRequest request, HttpSession session){
        String userId = request.getParameter("userId").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String userSex = request.getParameter("userSex").trim();

        Teacher teacher = new Teacher();
        teacher.setTeacherId(userId);
        teacher.setTeacherName(username);
        teacher.setTeacherPassword(password);
        teacher.setTeacherSex(userSex);

        ModelAndView modelAndView = new ModelAndView();
        int i = teacherService.updateTeacherById(teacher);
        session.setAttribute("teacher", teacher);
        modelAndView.setViewName("teacher/setup");
        return modelAndView;
    }
}
