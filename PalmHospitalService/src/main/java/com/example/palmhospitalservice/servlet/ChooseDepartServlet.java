package com.example.palmhospitalservice.servlet;

import com.example.palmhospitalservice.bean.Depart;
import com.example.palmhospitalservice.bean.User;
import com.example.palmhospitalservice.dao.DepartDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ChooseDepartServlet", value = "/ChooseDepartServlet")
public class ChooseDepartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        DepartDao departDao = new DepartDao();
        List<Depart> departs = departDao.selectAllDepart();

        PrintWriter writer=response.getWriter();
        String result="";

        response.setStatus(HttpServletResponse.SC_OK);
        result= new Gson().toJson(departs, new TypeToken<List<Depart>>() {}.getType());        // 将user的数据变成json格式，传输给客户端
        writer.write(result);
        writer.flush();
        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
