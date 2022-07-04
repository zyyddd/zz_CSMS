package indi.web.servlet.officerServlet;

import indi.service.OfficerService;
import indi.service.impl.OfficerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DelCourseSelectionResultServlet", value = "/DelCourseSelectionResultServlet")
public class DelCourseSelectionResultServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OfficerService os =  new OfficerServiceImpl();
        Integer stuId = Integer.parseInt(request.getParameter("stuId"));
        Integer couId = Integer.parseInt(request.getParameter("couId"));
        os.delSelectionResult(stuId,couId);
        response.sendRedirect(request.getContextPath() + "/FindCourseMessageByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
