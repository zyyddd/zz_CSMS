package indi.web.servlet.officerServlet;

import indi.domain.Course;
import indi.domain.StudentScore;
import indi.service.OfficerService;
import indi.service.TeacherService;
import indi.service.impl.OfficerServiceImpl;
import indi.service.impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet(name = "AlterJudgeServlet", value = "/AlterJudgeServlet")
public class AlterJudgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//        // 获取request中所有参数
//        Map<String, String[]> maps = request.getParameterMap();
//        System.out.println("AlterJudgeServlet...");
//        for(Map.Entry<String,String[]> entry:maps.entrySet()){
//            System.out.println(entry.getKey()+":"+ Arrays.toString(entry.getValue()));
//        }

        Map<String, String[]> maps = request.getParameterMap();
        String[] couIds = maps.get("couId");
        String[] judges = maps.get("judge");
        String[] isOpens = maps.get("isopen");
        for (int i = 0; i < couIds.length; i += 1) {
            Course c = new Course(Integer.parseInt(couIds[i]), null, -1,
                    null, Integer.parseInt(judges[i]), Integer.parseInt(isOpens[i]), -1);
            // couHour limitNum 是无参考价值的 在mapper中是不会被使用的
            OfficerService os = new OfficerServiceImpl();
            boolean b = os.examineAndapprove(c);
//            System.out.println("课程信息："+c);
        }
        response.sendRedirect(request.getContextPath() + "/FindJudgeByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
