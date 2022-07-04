package indi.web.servlet.teacherServlet;

import indi.domain.Course;
import indi.domain.User;
import indi.service.ManagerService;
import indi.service.TeacherService;
import indi.service.impl.ManagerServiceImpl;
import indi.service.impl.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FindCourseServlet", value = "/FindCourseServlet")
public class FindCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String couId = request.getParameter("couId");
        TeacherService ts = new TeacherServiceImpl();
        Course course = ts.findCourseById(Integer.parseInt(couId));

        request.setAttribute("course", course);
        request.getRequestDispatcher("/updateCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
