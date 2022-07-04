package indi.web.servlet.teacherServlet;

import indi.domain.Course;
import indi.domain.PageBean;
import indi.domain.User;
import indi.service.ManagerService;
import indi.service.TeacherService;
import indi.service.impl.ManagerServiceImpl;
import indi.service.impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FindCourseByPageServlet", value = "/FindCourseByPageServlet")
public class FindCourseByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }

        if (rows == null || "".equals(rows)) {
            rows = "5";
        }
//        System.out.println("FindCourseServlet's user: "+ request.getSession().getAttribute("user"));

        User user = (User) request.getSession().getAttribute("user");

//        // ===============================================================================
//        // 测试使用，后期得删！！！
//        if (user == null) {
//            user = new User(30002, "tea2", "123456", 3, "tea2", "男");
//        }
//
//        // ===============================================================================

        Map<String, String[]> condition = request.getParameterMap();
        TeacherService ts = new TeacherServiceImpl();
        // 通过账号查对应的教师id
        Integer teaId = ts.findByAccount(user.getAccount());
        PageBean<Course> pb = ts.findCourseByPage(currentPage, rows, condition, teaId);

        request.setAttribute("pb", pb);
        request.setAttribute("condition", condition);
        request.getRequestDispatcher("/teacherList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
