package indi.web.servlet.managerServlet;

import indi.domain.PageBean;
import indi.domain.User;
import indi.service.ManagerService;
import indi.service.impl.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "FindUserByPageServlet", value = "/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
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
        ManagerService ms = new ManagerServiceImpl();
        PageBean<User> pb = ms.findUserByPage(currentPage, rows, condition);

//        System.out.println("Enter....");
//        System.out.println(pb);
//        Set<String> strings = condition.keySet();
//        System.out.println(strings);
//        System.out.println("values:" + condition.values());

        request.setAttribute("pb", pb);
        request.setAttribute("condition", condition);

        request.getRequestDispatcher("/managerList.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
