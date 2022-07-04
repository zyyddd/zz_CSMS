package indi.web.servlet.officerServlet;

import indi.domain.Course;
import indi.domain.PageBean;
import indi.domain.User;
import indi.service.OfficerService;
import indi.service.TeacherService;
import indi.service.impl.OfficerServiceImpl;
import indi.service.impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FindJudgeByPageServlet", value = "/FindJudgeByPageServlet")
public class FindJudgeByPageServlet extends HttpServlet {
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

        Map<String, String[]> condition = request.getParameterMap();
        OfficerService os = new OfficerServiceImpl();
        PageBean<Course> pb = os.findCourseJudgeByPage(currentPage, rows, condition);

        request.setAttribute("pb", pb);
        request.setAttribute("condition", condition);
        request.getRequestDispatcher("/officerFindCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
