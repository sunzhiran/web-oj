package com.sdkd.controller;

import com.sdkd.pojo.Manager;
import com.sdkd.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhiran.sun on 2017/5/10.
 */
@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;


    @RequestMapping("setupManager")
    public ModelAndView setupManager(HttpServletRequest request, HttpSession session){
        String userId = request.getParameter("userId").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String userSex = request.getParameter("userSex").trim();

        Manager manager = new Manager();
        manager.setManagerId(userId);
        manager.setManagerName(username);
        manager.setManagerPassword(password);
        manager.setManagerSex(userSex);

        ModelAndView modelAndView = new ModelAndView();
        int i = managerService.updateManagerById(manager);
        session.setAttribute("manager", manager);
        modelAndView.setViewName("manager/setup");
        return modelAndView;
    }

}
