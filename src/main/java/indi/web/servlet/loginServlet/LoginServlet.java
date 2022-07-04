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

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        // 获取数据
        String verifycode = request.getParameter("verifycode");

        // 验证码校验
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");                // 防止验证码在返回页面中出现被缓存以致不更新的情况


        // 验证码验证应在后面完成阶段进行验证
        if (!checkcode_server.equalsIgnoreCase(verifycode)) {
            request.setAttribute("login_msg", "验证码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }


        // 封装USER对象
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 调用Service查询
        LoginService ls = new LoginServiceImpl();
        User loginUser = ls.login(user);

        // ===========================================================================
        // 这里可能存在扩展问题，也就是如果角色不断增加，每次都需要重构代码，影响性能
        // 解决方法：策略模式
        // ===========================================================================


        // 判断是否登录成功
        if (loginUser != null) {
            // 登录成功
            session.setAttribute("user", loginUser);
            String loginPath = ls.stragyMethodForRole(loginUser);
            if (loginPath != null) {
                response.sendRedirect(request.getContextPath() + loginPath);
            } else {
                request.setAttribute("login_msg", "数据库异常！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
//            if (loginUser.getRoleId() == 1) {
//                // 数据库管理员
//                response.sendRedirect(request.getContextPath() + "/managerIndex.jsp");
//            } else if (loginUser.getRoleId() == 2) {
//                // 教务处管理员
//                response.sendRedirect(request.getContextPath() + "/officerIndex.jsp");
//            } else if (loginUser.getRoleId() == 3) {
//                // 教师
//                response.sendRedirect(request.getContextPath() + "/teacherIndex.jsp");
//            } else if (loginUser.getRoleId() == 4) {
//                // 学生
//                response.sendRedirect(request.getContextPath() + "/studentIndex1.jsp");
//            } else {
//                request.setAttribute("login_msg", "数据库异常！");
//                request.getRequestDispatcher("/login.jsp").forward(request, response);
//            }

        } else {
            request.setAttribute("login_msg", "用户名或密码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
