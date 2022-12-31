package com.example.palmhospitalservice.servlet;

import com.example.palmhospitalservice.bean.Doctor;
import com.example.palmhospitalservice.bean.Schedule;
import com.example.palmhospitalservice.dao.DoctorDao;
import com.example.palmhospitalservice.dao.ScheduleDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ChooseTimeServlet", value = "/ChooseTimeServlet")
public class ChooseTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接受到查询某医生的所有排班请求了");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int did = Integer.parseInt(request.getParameter("did"));
        ScheduleDao scheduleDao = new ScheduleDao();
        List<Schedule> schedules = scheduleDao.selectchedulesByDid(did);

        PrintWriter writer=response.getWriter();
        String result="";

        response.setStatus(HttpServletResponse.SC_OK);
        result= new Gson().toJson(schedules, new TypeToken<List<Schedule>>() {}.getType());        // 将user的数据变成json格式，传输给客户端
        writer.write(result);
        writer.flush();
        writer.close();

        System.out.println("查询某医生的所有排班：" + result);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
