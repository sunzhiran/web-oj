package com.sdkd.controller;

import com.sdkd.pojo.StuClass;
import com.sdkd.pojo.User;
import com.sdkd.service.StuClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/15.
 */
@Controller
public class StuClassController {

    @Autowired
    private StuClassService stuClassService;

    @RequestMapping("selectAllClasses")
    public ModelAndView selectAllClasses(HttpServletRequest request, HttpSession session) {
        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        //System.out.println(currentPage);
        ModelAndView modelAndView =new ModelAndView();
        List<StuClass> allClasses = stuClassService.selectAllClass();
        request.setAttribute("allClasses", allClasses);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("manager/class");
        return modelAndView;
    }

    @RequestMapping("addClass")
    public ModelAndView addClass(HttpServletRequest request, HttpSession session){
        String classId = request.getParameter("userId").trim();

        ModelAndView modelAndView = new ModelAndView();
        StuClass stuClass1 = stuClassService.selectClassByClassId(classId);
        if(null != stuClass1){
            modelAndView.setViewName("manager/class-add");
            return modelAndView;
        }
        else{
            String className = request.getParameter("username").trim();
            String grade = request.getParameter("grade").trim();

            StuClass stuClass = new StuClass();
            stuClass.setClassId(classId);
            stuClass.setClassName(className);
            stuClass.setClassGrade(grade);

            int i = stuClassService.insertClass(stuClass);
            return selectAllClasses(request, session);
        }
    }

}
