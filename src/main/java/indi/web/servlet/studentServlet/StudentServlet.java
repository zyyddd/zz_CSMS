package indi.web.servlet.studentServlet;

import indi.domain.User;
import indi.service.StudentService;
import indi.service.impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends BaseServlet {

    StudentService studentService = new StudentServiceImpl();
    //成绩查询模块
    protected void showScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        request.setAttribute("name",user.getName());
        String account = user.getAccount();
        List<Object> studentScoreList = studentService.scoreInquiry(account);
        request.setAttribute("studentScoreList",studentScoreList);
        request.getRequestDispatcher("/studentScoreList.jsp").forward(request,response);
    }



    //学生课程表查询模块
    protected void showCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String name = user.getName();
        request.setAttribute("name",name);
        String account = user.getAccount();
        //已选择课程名单
        List<Map<String, Object>> maps = studentService.showCourse(account);
        request.setAttribute("maps1",maps);
        //未选择课程名单
        List<Map<String, Object>> maps2 = studentService.enselectedCourse(account);
        request.setAttribute("maps2",maps2);
        request.getRequestDispatcher("/studentCourseList.jsp").forward(request,response);
    }




    //学生课程表退选模块
    protected void delCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");


        String account = request.getParameter("account");
        System.out.println(account);
        String couName = request.getParameter("couName");
        System.out.println(couName);
        studentService.delCourse(account,couName);

        response.sendRedirect(request.getContextPath() + "/StudentServlet?action=showCourse");
//        request.getRequestDispatcher("/StudentServlet?action=showCourse").forward(request,response);
    }

    //学生课程表选择模块
    protected void selectCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            String account = request.getParameter("account");
            String couName = request.getParameter("couName");
            String teaName = request.getParameter("teaName");
            studentService.selection(account,couName,teaName);
            response.sendRedirect(request.getContextPath() + "/StudentServlet?action=showCourse");

//        request.getRequestDispatcher("/StudentServlet?action=showCourse").forward(request,response);

    }


}
