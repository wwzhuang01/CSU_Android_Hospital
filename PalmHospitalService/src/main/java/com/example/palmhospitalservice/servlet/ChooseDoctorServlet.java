package com.example.palmhospitalservice.servlet;

import com.example.palmhospitalservice.bean.Depart;
import com.example.palmhospitalservice.bean.Doctor;
import com.example.palmhospitalservice.dao.DepartDao;
import com.example.palmhospitalservice.dao.DoctorDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ChooseDoctorServlet", value = "/ChooseDoctorServlet")
public class ChooseDoctorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接受到查询科室的所有医生请求了");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int departid = Integer.parseInt(request.getParameter("departid"));
        DoctorDao DoctorDao = new DoctorDao();
        List<Doctor> doctors = DoctorDao.selectDoctorsByDepartid(departid);

        PrintWriter writer=response.getWriter();
        String result="";

        response.setStatus(HttpServletResponse.SC_OK);
        result= new Gson().toJson(doctors, new TypeToken<List<Doctor>>() {}.getType());        // 将user的数据变成json格式，传输给客户端
        writer.write(result);
        writer.flush();
        writer.close();

        System.out.println("查询某科室的所有医生：" + result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
