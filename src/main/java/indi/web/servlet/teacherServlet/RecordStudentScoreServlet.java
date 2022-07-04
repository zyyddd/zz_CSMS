package indi.web.servlet.teacherServlet;

import indi.domain.StudentScore;
import indi.service.TeacherService;
import indi.service.impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(name = "RecordStudentScoreServlet", value = "/RecordStudentScoreServlet")
public class RecordStudentScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//        // 获取request中所有参数
//        Map<String, String[]> maps = request.getParameterMap();
//        System.out.println("RecordStudentScoreServlet...");
//        for(Map.Entry<String,String[]> entry:maps.entrySet()){
//            System.out.println(entry.getKey()+":"+ Arrays.toString(entry.getValue()));
//        }

        Map<String, String[]> maps = request.getParameterMap();
        String[] ssIds = maps.get("ssId");
        String[] scores = maps.get("score");
        for (int i = 0; i < ssIds.length; i += 1) {
            StudentScore ss = new StudentScore(Integer.parseInt(ssIds[i]), null,
                    null, null, Integer.parseInt(scores[i]));
            TeacherService ts = new TeacherServiceImpl();
            boolean b = ts.recordScore(ss);
//            System.out.println("学生信息："+ss);
        }

        response.sendRedirect(request.getContextPath() + "/FindStudentByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
