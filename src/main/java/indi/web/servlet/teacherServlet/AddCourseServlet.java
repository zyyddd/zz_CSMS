package indi.web.servlet.teacherServlet;

import indi.domain.Course;
import indi.domain.User;
import indi.service.ManagerService;
import indi.service.TeacherService;
import indi.service.impl.ManagerServiceImpl;
import indi.service.impl.TeacherServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "AddCourseServlet", value = "/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Course course = new Course();
        try {
            BeanUtils.populate(course, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        TeacherService ts = new TeacherServiceImpl();
        ts.addCourse(course);

        response.sendRedirect(request.getContextPath() + "/FindCourseByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
