package indi.web.servlet.teacherServlet;

import indi.domain.Course;
import indi.domain.Teacher;
import indi.service.TeacherService;
import indi.service.impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SelectThisCourseServlet", value = "/SelectThisCourseServlet")
public class SelectThisCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String couId = request.getParameter("couId");
        String teaId = request.getParameter("teaId");
        TeacherService ts = new TeacherServiceImpl();
        boolean b = ts.selectCourse(new Course(Integer.parseInt(couId), null, 0, null, 0, 0, 0),
                new Teacher(0, null, null, 0, null, null, Integer.parseInt(teaId)));

        response.sendRedirect(request.getContextPath() + "/TeacherSelectServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
