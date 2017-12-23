package com.sdkd.controller;

import com.sdkd.pojo.Manager;
import com.sdkd.pojo.StuClass;
import com.sdkd.pojo.Teacher;
import com.sdkd.pojo.User;
import com.sdkd.service.ManagerService;
import com.sdkd.service.TeacherService;
import com.sdkd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhiran.sun on 2017/4/22.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping("selectAllUsers")
    public ModelAndView selectAllUsers(HttpServletRequest request, HttpSession session) {
        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        //System.out.println(currentPage);
        ModelAndView modelAndView =new ModelAndView();
        List<User> allUsers = userService.getAllUsers();
        request.setAttribute("allUsers", allUsers);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("manager/user");
        return modelAndView;
    }

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, HttpSession session) {

        String userId = request.getParameter("userId").trim();
        String password = request.getParameter("password").trim();
        String userType = request.getParameter("usertype").trim();

        ModelAndView modelAndView = new ModelAndView();
        if(userType.equals("1")){
            User user=userService.getUserByUserId(userId);
            if (user != null && password.equals(user.getUserPassword())) {
                session.setAttribute("user", user);
                modelAndView.setViewName("index");
            }
            else {
                modelAndView.setViewName("login");
            }
        }
        else if(userType.equals("2")){
            Teacher teacher = teacherService.selectTeacherByUserId(userId);
            if (teacher != null && password.equals(teacher.getTeacherPassword())) {
                session.setAttribute("teacher", teacher);
                modelAndView.setViewName("teacher/index");
            } else {
                modelAndView.setViewName("login");
            }
        }
        else{
            Manager manager = managerService.selectManagerByUserId(userId);
            if(manager != null && password.equals(manager.getManagerPassword())){
                session.setAttribute("manager",manager);
                modelAndView.setViewName("manager/index");
            }
            else{
                modelAndView.setViewName("login");
            }
        }
        return modelAndView;
    }

    @RequestMapping("addUser")
    public ModelAndView addUser(HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        String userId = request.getParameter("userId").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
       /* if(userId == null || userId.length() == 0 || userId == ""||username == null || username.length() == 0 || username == ""||password == null || password.length() == 0 || password ==""){
            return selectClassAtAddUser(request, session);
        }*/

        User user1=userService.getUserByUserId(userId);
        if(null != user1 ||userId .equals("")){
            return selectClassAtAddUser(request, session);
        }
        else{
            String userSex = request.getParameter("userSex").trim();
            Integer userType = 1;
            String chooseClass = request.getParameter("chooseClass").trim();

            User user = new User();
            user.setUserId(userId);
            user.setUserName(username);
            user.setUserPassword(password);
            user.setUserSex(userSex);
            user.setUserType(userType);
            user.setUserClass(chooseClass);

            int i = userService.addUser(user);
            return selectAllUsers(request, session);
        }
    }

    @RequestMapping("selectClassAtAddUser")
    public ModelAndView selectClassAtAddUser(HttpServletRequest request , HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        List<StuClass> allClasses = userService.selectAllClass();
        request.setAttribute("allClasses",allClasses);
        modelAndView.setViewName("manager/user-add");
        return modelAndView;
    }

    @RequestMapping("editUser")
    public ModelAndView editUser(HttpServletRequest request, HttpSession session){
        String userId = request.getParameter("userId");
        User user = userService.getUserByUserId(userId);
        ModelAndView modelAndView = new ModelAndView();
        List<StuClass> allClasses = userService.selectAllClass();
        request.setAttribute("allClasses",allClasses);
        request.setAttribute("user",user);
        modelAndView.setViewName("manager/user-edit");
        return modelAndView;
    }

    @RequestMapping("updateUser")
    public ModelAndView updateUser(HttpServletRequest request, HttpSession session){
        String userId = request.getParameter("userId").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String userSex = request.getParameter("userSex").trim();
        String userNote = request.getParameter("userNote").trim();
       /* if(username == null || username.length() == 0 || username == ""||password == null || password.length() == 0 || password ==""){

        }*/
        //String type = request.getParameter("userType").trim();
        //Integer userType = Integer.valueOf(type);
        String userClass = request.getParameter("chooseClass").trim();

        User user = new User();
        user.setUserId(userId);
        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserSex(userSex);
        //user.setUserType(userType);
        user.setUserClass(userClass);
        user.setUserNote(userNote);

        int i = userService.updateUserById(user);
        return selectAllUsers(request, session);
    }

    @RequestMapping("setupUser")
    public ModelAndView setupUser(HttpServletRequest request, HttpSession session){
        String userId = request.getParameter("userId").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String userSex = request.getParameter("userSex").trim();

        User user = new User();
        user.setUserId(userId);
        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserSex(userSex);

        ModelAndView modelAndView = new ModelAndView();
        int i = userService.updateUserSelf(user);
        session.setAttribute("user", user);
        modelAndView.setViewName("setup");
        return modelAndView;
    }

    @RequestMapping("selectUserByPartUsername")
    public ModelAndView selectUserByPartUsername(HttpServletRequest request, HttpSession session) {
        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        //System.out.println(currentPage);
        String username = request.getParameter("username").trim();
        ModelAndView modelAndView =new ModelAndView();
        List<User> allUsers = userService.getUserByPartUserName(username);
        request.setAttribute("allUsers", allUsers);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("manager/user");
        return modelAndView;
    }
}
