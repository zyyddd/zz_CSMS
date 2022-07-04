package indi.web.servlet.teacherServlet;

import indi.service.ManagerService;
import indi.service.TeacherService;
import indi.service.impl.ManagerServiceImpl;
import indi.service.impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DelCourseSelectedServlet", value = "/DelCourseSelectedServlet")
public class DelCourseSelectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] ids = request.getParameterValues("couId");
        TeacherService ts = new TeacherServiceImpl();
        ts.delSelectedCourse(ids);

        response.sendRedirect(request.getContextPath() + "/FindCourseByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
