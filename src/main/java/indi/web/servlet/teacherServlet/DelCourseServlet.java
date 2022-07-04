package indi.web.servlet.teacherServlet;

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

@WebServlet(name = "DelCourseServlet", value = "/DelCourseServlet")
public class DelCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String couId = request.getParameter("couId");
        TeacherService ts = new TeacherServiceImpl();
        ts.deleteCourse(Integer.parseInt(couId));
        response.sendRedirect(request.getContextPath() + "/FindCourseByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
