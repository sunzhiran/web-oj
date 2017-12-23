package com.sdkd.controller;

import com.sdkd.pojo.Notice;
import com.sdkd.pojo.Teacher;
import com.sdkd.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by zhiran.sun on 2017/5/8.
 */

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping("selectAllNotices")
    public ModelAndView selectAllNotices(HttpServletRequest request, HttpSession session){

        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        //System.out.println(currentPage);
        ModelAndView modelAndView =new ModelAndView();
        List<Notice> allNotices = noticeService.getAllNotices();
        request.setAttribute("allNotices", allNotices);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("notice");
        return modelAndView;
    }

    @RequestMapping("selectAllNoticesInTeacher")
    public ModelAndView selectAllNoticesInTeacher(HttpServletRequest request, HttpSession session){

        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        //System.out.println(currentPage);
        ModelAndView modelAndView =new ModelAndView();
        List<Notice> allNotices = noticeService.getAllNotices();
        request.setAttribute("allNotices", allNotices);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("teacher/notice");
        return modelAndView;
    }

    @RequestMapping("addNotice")
    public ModelAndView addNotice(HttpServletRequest request , HttpSession session){
        String content = request.getParameter("content");
        Date date = new Date();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String writer = teacher.getTeacherId();

        Notice notice = new Notice();
        notice.setNoticeContent(content);
        notice.setNoticeTime(date);
        notice.setNoticeWriter(writer);

        ModelAndView modelAndView = new ModelAndView();
        int i = noticeService.addNotice(notice);
        return selectAllNoticesInTeacher(request, session);
    }

}
