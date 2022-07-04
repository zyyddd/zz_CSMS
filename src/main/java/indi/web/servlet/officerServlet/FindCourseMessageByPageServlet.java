package indi.web.servlet.officerServlet;

import indi.domain.PageBean;
import indi.domain.StudentScore;
import indi.domain.StudentScoreAndName;
import indi.domain.User;
import indi.service.ManagerService;
import indi.service.OfficerService;
import indi.service.impl.ManagerServiceImpl;
import indi.service.impl.OfficerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FindCourseMessageByPageServlet", value = "/FindCourseMessageByPageServlet")
public class FindCourseMessageByPageServlet extends HttpServlet {
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
        PageBean<StudentScoreAndName> pb = os.findMessagerByPage(currentPage, rows, condition);

//        System.out.println("Enter....");
//        System.out.println(pb);
//        Set<String> strings = condition.keySet();
//        System.out.println(strings);
//        System.out.println("values:" + condition.values());

        request.setAttribute("pb", pb);
        request.setAttribute("condition", condition);

        request.getRequestDispatcher("/officerCourseSelectionResult.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
