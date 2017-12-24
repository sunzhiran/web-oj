package com.sdkd.controller;

import com.sdkd.pojo.RBACAccess;
import com.sdkd.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhiran.sun
 * @version 1.0
 * @date 20:50 2017/12/7.
 */

@Controller
public class AccessController {

    @Autowired
    private AccessService accessService;

    @RequestMapping("selectAllAccesses")
    public ModelAndView selectAllAccesses(HttpServletRequest request, HttpSession session) {
        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        //System.out.println(currentPage);
        ModelAndView modelAndView =new ModelAndView();
        List<RBACAccess> allAccesses = accessService.getAllAccesses();
        request.setAttribute("allAccesses", allAccesses);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("access");
        return modelAndView;
    }

    @RequestMapping("addAccess")
    public ModelAndView addAccess(HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String accessname = request.getParameter("accessname").trim();
        String accessURL = request.getParameter("accessurl").trim();
       /* if(userId == null || userId.length() == 0 || userId == ""||username == null || username.length() == 0 || username == ""||password == null || password.length() == 0 || password ==""){
            return selectClassAtAddUser(request, session);
        }*/

        RBACAccess access1=accessService.selectAccessByAccessTitle(accessname);
        if(null != access1 ||accessname.equals("")){
            modelAndView.setViewName("access-add");
            return modelAndView;
        }
        else{
            RBACAccess access = new RBACAccess();
            access.setAccessTitle(accessname);
            access.setAccessURL(accessURL);
            int i = accessService.addAccess(access);
            return selectAllAccesses(request, session);
        }
    }

}
