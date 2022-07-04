package indi.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if (uri.contains("/login.jsp") || uri.contains("/LoginServlet") || uri.contains("/css/")
                || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/CheckCodeServlet") || uri.contains("/error/")
                || uri.contains("/imgs/")) {
            // 用户想登录，应放行
            chain.doFilter(request, response);
        } else {
            Object user = req.getSession().getAttribute("user");
            if (user != null) {
                // 用户已经登录过
                chain.doFilter(request, response);
            } else {
                // 用户未登录
                req.setAttribute("login_msg", "您尚未登录，请登录");
                req.getRequestDispatcher("/login.jsp").forward(req, response);
            }
        }
    }
}
