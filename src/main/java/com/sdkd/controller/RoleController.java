package com.sdkd.controller;

import com.sdkd.pojo.RBACAccess;
import com.sdkd.pojo.RBACRole;
import com.sdkd.pojo.RBACRoleAccess;
import com.sdkd.service.AccessService;
import com.sdkd.service.RoleService;
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
 * @date 18:19 2017/12/7.
 */

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccessService accessService;

    @RequestMapping("selectAllRoles")
    public ModelAndView selectAllRoles(HttpServletRequest request, HttpSession session) {
        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        //System.out.println(currentPage);
        ModelAndView modelAndView =new ModelAndView();
        List<RBACRole> allRoles = roleService.getAllRoles();
        request.setAttribute("allRoles", allRoles);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("role");
        return modelAndView;
    }

    @RequestMapping("addRole")
    public ModelAndView addRole(HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String rolename = request.getParameter("rolename").trim();
       /* if(userId == null || userId.length() == 0 || userId == ""||username == null || username.length() == 0 || username == ""||password == null || password.length() == 0 || password ==""){
            return selectClassAtAddUser(request, session);
        }*/

        RBACRole role1=roleService.selectRoleByRoleName(rolename);
        if(null != role1 ||rolename.equals("")){
            modelAndView.setViewName("role-add");
            return modelAndView;
        }
        else{
            RBACRole role = new RBACRole();
            role.setRoleName(rolename);
            int i = roleService.addRole(role);
            return selectAllRoles(request, session);
        }
    }

    @RequestMapping("editRoleAccess")
    public ModelAndView editRoleAccess(HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String roleId = request.getParameter("roleId").trim();
        Integer rId =Integer.valueOf(roleId);
        List<RBACRoleAccess> selectedRoleAccess = roleService.selectRoleAccessByRoleId(rId);
        request.setAttribute("selectedRoleAccess",selectedRoleAccess);
        return selectAllAccessesInRole(request, session);
    }

    @RequestMapping("selectAllAccessesInRole")
    public ModelAndView selectAllAccessesInRole(HttpServletRequest request, HttpSession session) {
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
        modelAndView.setViewName("edit-role-access");
        return modelAndView;
    }
}
