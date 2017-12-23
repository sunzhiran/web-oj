package com.sdkd.controller;

import com.sdkd.pojo.Homework;
import com.sdkd.pojo.HwStu;
import com.sdkd.pojo.StuClass;
import com.sdkd.pojo.Teacher;
import com.sdkd.pojo.User;
import com.sdkd.service.DeployService;
import com.sdkd.service.HomeworkService;
import com.sdkd.service.HwStuService;
import com.sdkd.utils.Constants;
import com.sdkd.utils.DateUtil;
import com.sdkd.utils.HashUtil;
import com.sdkd.utils.MyFile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

;

/**
 * Created by zhiran.sun on 2017/4/27.
 */

@Controller
public class HomeworkController {

    private static final Logger logger = LoggerFactory.getLogger(HomeworkController.class);

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private DeployService deployService;

    @Autowired
    private HwStuService hwStuService;

    @RequestMapping("showAllHomeworks")
    public ModelAndView showAllHomeworks(HttpServletRequest request, HttpSession session){
        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        //System.out.println(currentPage);
        ModelAndView modelAndView =new ModelAndView();
        User user = (User) session.getAttribute("user");
        String userClass = user.getUserClass();
        List<Homework> allHomeworks = homeworkService.selectHomeworkByClassId(userClass);
        request.setAttribute("allHomeworks", allHomeworks);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("homework");
        return modelAndView;
    }

    @RequestMapping("showAllHomeworksInTeacher")
    public ModelAndView showAllHomeworksInTeacher(HttpServletRequest request, HttpSession session){
        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        //System.out.println(currentPage);
        ModelAndView modelAndView =new ModelAndView();
        List<Homework> allHomeworks =homeworkService.selectHomeworkByTeacherId(teacher);
        request.setAttribute("allHomeworks", allHomeworks);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("teacher/homework");
        return modelAndView;
    }

    @RequestMapping("showHomeworkByPartTitleInteacher")
    public ModelAndView showHomeworkByPartTitleInteacher(HttpServletRequest request, HttpSession session){
        //no use
        String paramCurrentPage=request.getParameter("currentPage");
        int currentPage = 0;
        if (paramCurrentPage != null) {
            paramCurrentPage = paramCurrentPage.trim();
            currentPage = Integer.parseInt(paramCurrentPage);
        }
        //System.out.println(currentPage);
        ModelAndView modelAndView =new ModelAndView();
        List<Homework> allHomeworks = homeworkService.getAllHomework();
        request.setAttribute("allHomeworks", allHomeworks);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("teacher/homework");
        return modelAndView;
    }

    @RequestMapping("deleteHomework")
    public ModelAndView deleteHomework(HttpServletRequest request, HttpSession session){
        String paramHomeworkId  = request.getParameter("homeworkId");
        Integer homeworkId = Integer.parseInt(paramHomeworkId);
        int homework = homeworkService.deleteHomeworkById(homeworkId);
        return showAllHomeworksInTeacher(request, session);
    }

    @RequestMapping("editHomework")
    public ModelAndView editHomework(HttpServletRequest request ,HttpSession session){
        String paramHomeworkId = request.getParameter("homeworkId");
        Integer homeworkId = Integer.parseInt(paramHomeworkId);
        Homework homework = homeworkService.selectHomeworkById(homeworkId);
        ModelAndView modelAndView = new ModelAndView();
        request.setAttribute("homework",homework);
        modelAndView.setViewName("teacher/homework-edit");
        return modelAndView;
    }

    @RequestMapping("updateHomework")
    public ModelAndView updateHomework(HttpServletRequest request, HttpSession session ,MultipartFile attachmentFile){
        String homeworkId = request.getParameter("homeworkId").trim();
        Integer id = Integer.valueOf(homeworkId);
        String title = request.getParameter("title").trim();
        String deadlineTime = request.getParameter("deadline").trim();
        Date deadline = DateUtil.parseDate(deadlineTime);

        Homework homework = new Homework();
        homework.setHomeworkId(id);
        homework.setHomeworkTitle(title);
        homework.setHomeworkDeadline(deadline);
        homeworkService.updateHomeworkById(homework);

        ModelAndView modelAndView = new ModelAndView();
        if(!attachmentFile.isEmpty()){
            try {
                String path1 = Constants.UPLOAD_FILE_ROOT + "homework/" + homework.getHomeworkId().toString();
                File dir = new File(path1);
                if (dir.exists())
                    MyFile.removedir(dir);
                dir.mkdirs();
                String path = path1+"/"  + attachmentFile.getOriginalFilename();

                attachmentFile.transferTo(new File(path));
                homework.setHomeworkAttachment(path);
                int i = homeworkService.updateHomeworkAttachment(homework);
            } catch (IllegalArgumentException ee) {
                ee.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return showAllHomeworksInTeacher(request, session);
    }

    @RequestMapping("addHomework")
    public ModelAndView addHomework(HttpServletRequest request , HttpSession session,MultipartFile attachmentFile) {
        ModelAndView modelAndView = new ModelAndView();
        String title = request.getParameter("title");
        String deadlineTime = request.getParameter("deadline").trim();
        String chooseClass = request.getParameter("chooseClass").trim();
        Date deadline = DateUtil.parseDate(deadlineTime);
        Date date = new Date();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String writer = teacher.getTeacherId();

        Homework homework = new Homework();
        homework.setHomeworkTitle(title);
        homework.setHomeworkTime(date);
        homework.setHomeworkDeadline(deadline);
        homework.setHomeworkWriter(writer);
        homework.setHomeworkClass(chooseClass);

        Homework hw =  homeworkService.addHomework(homework);
        if(null == hw){
            modelAndView.setViewName("teacher/homework-add");
            return modelAndView;
        }
        else if(!attachmentFile.isEmpty()){
            try {
                String path1 = Constants.UPLOAD_FILE_ROOT + "homework/" + hw.getHomeworkId().toString();
                String path = path1+"/"  + attachmentFile.getOriginalFilename();
                File dir = new File(path1);
                if ( ! dir.exists())
                    dir.mkdirs();
                attachmentFile.transferTo(new File(path));


                hw.setHomeworkAttachment(path);
                int i = homeworkService.updateHomeworkAttachment(hw);
            } catch (IllegalArgumentException ee) {
                ee.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return showAllHomeworksInTeacher(request, session);
    }

    @RequestMapping("selectClassAtAddHomework")
    public ModelAndView selectClassAtAddHomework(HttpServletRequest request , HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        List<StuClass> allClasses = homeworkService.selectAllClass();
        request.setAttribute("allClasses",allClasses);
        modelAndView.setViewName("teacher/homework-add");
        return modelAndView;
    }

    @RequestMapping("moreHomeworkInfo")
    public ModelAndView moreHomeworkInfo(HttpServletRequest request , HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String homeworkIdParam = request.getParameter("homeworkId").trim();
        Integer homeworkId = Integer.parseInt(homeworkIdParam);
        List<HwStu> hwStus = homeworkService.selectHomeworkInfoByHomeworkId(homeworkId);
        request.setAttribute("completedHomework",hwStus);
        List<User> noCompletedUser = homeworkService.selectNoCompletedUserByHomeworkId(homeworkId);
        request.setAttribute("noCompletedUser",noCompletedUser);
        modelAndView.setViewName("teacher/homework-list");
        return modelAndView;
    }

    @RequestMapping("myHomework")
    public ModelAndView myHomework(HttpServletRequest request , HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        List<HwStu> hwStus = homeworkService.selectHomeworkByUserId(userId);
        request.setAttribute("completedHomework",hwStus);
        modelAndView.setViewName("homework-complated");
        return modelAndView;
    }

    @RequestMapping("uploadHomework")
    public ModelAndView uploadHomework(HttpServletRequest request , HttpSession session, String homeworkId, MultipartFile paper, MultipartFile code, MultipartFile project,MultipartFile sql){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");

        if (StringUtils.isBlank(homeworkId) || paper.isEmpty() || code.isEmpty() || project.isEmpty()||sql.isEmpty()) {
            request.setAttribute("homeworkId", homeworkId);
            modelAndView.setViewName("homework-upload");
            return modelAndView;
        }

        //自动部署
        String fileName = project.getOriginalFilename();
        String token = HashUtil.md5(user.getUserId());
        String projectUrl = null;
        try {
            InputStream in = project.getInputStream(); //deploy线程负载关闭资源
            projectUrl = deployService.autoDeploy(in, token, fileName);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        //存paper，code，sql   dir: student/hwid/token/file
        String fileDirPath = Constants.UPLOAD_FILE_ROOT + "student/" + homeworkId + "/" + token;
        File fileDir = new File(fileDirPath);
        if ( ! fileDir.exists() || ! fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        String paperFilePath = fileDirPath + "/" + paper.getOriginalFilename();
        String codeFilePath = fileDirPath + "/" + code.getOriginalFilename();
        String sqlFilePath = fileDirPath +"/"+ sql.getOriginalFilename();
        try {
            paper.transferTo(new File(paperFilePath));
            code.transferTo(new File(codeFilePath));
            sql.transferTo(new File(sqlFilePath));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            logger.error("The paper,code and sql files upload failed");
        }

        //自动部署DB
        String dbName = fileName.substring(0, fileName.length()-4);
        deployService.autoDeployDB(dbName, sqlFilePath);

        //更新作业完成情况表记录
        HwStu hwStu = new HwStu();
        hwStu.setStuId(user.getUserId());
        hwStu.setHwId(Integer.parseInt(homeworkId));
        hwStu.setPaperUrl(paperFilePath);
        hwStu.setCodeUrl(codeFilePath);
        hwStu.setProjectUrl(projectUrl);
        hwStu.setUploadTime(new Date());
        hwStu.setHwStuUrl(fileDirPath);
        hwStu.setSqlUrl(sqlFilePath);

        hwStu =hwStuService.setHwStu(hwStu);
        hwStuService.similarityJudge(hwStu);
        return myHomework(request,session);
    }

    @RequestMapping("download")
    public void download(HttpServletResponse response, String fileName, String path) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(path) && StringUtils.isNotBlank(fileName)) {

            fileName = URLEncoder.encode(fileName, "UTF-8");

            File file = new File(path);
            if ( ! file.exists() || file.isDirectory()) {
                return;
            }
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream(response.getOutputStream());

                byte[] cache = new byte[2048];
                int len = 0;
                while ((len = in.read(cache, 0, cache.length)) != -1) {
                    out.write(cache, 0, len);
                }
                out.flush();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
}
