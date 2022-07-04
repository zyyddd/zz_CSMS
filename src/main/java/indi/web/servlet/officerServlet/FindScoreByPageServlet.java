package indi.web.servlet.officerServlet;

import indi.domain.PageBean;
import indi.domain.StudentScoreAndName;
import indi.service.OfficerService;
import indi.service.impl.OfficerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FindScoreByPageServlet", value = "/FindScoreByPageServlet")
public class FindScoreByPageServlet extends HttpServlet {
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

        request.setAttribute("pb", pb);
        request.setAttribute("condition", condition);

        request.getRequestDispatcher("/officerScoreSelectionResult.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
