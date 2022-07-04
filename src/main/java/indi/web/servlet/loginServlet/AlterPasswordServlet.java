package indi.web.servlet.loginServlet;

import indi.domain.User;
import indi.service.LoginService;
import indi.service.impl.LoginServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "AlterPasswordServlet", value = "/AlterPasswordServlet")
public class AlterPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        String oldPassword = map.get("oldPassword")[0];
        LoginService ls = new LoginServiceImpl();
        boolean b = ls.alterPassword(user, oldPassword);
        if (b) {
            // 修改成功
            session.removeAttribute("user");            // 修改密码成功后应使用户重新登录
            request.setAttribute("alter_msg", "修改成功，正在回到登录界面");
            // 休眠1000毫秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            // 修改失败
            request.setAttribute("alter_msg", "旧密码错误！");
            request.getRequestDispatcher("/alterPwd.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
