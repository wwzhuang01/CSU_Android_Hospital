package com.example.palmhospitalservice.servlet;

import com.example.palmhospitalservice.bean.Depart;
import com.example.palmhospitalservice.dao.DepartDao;
import com.example.palmhospitalservice.dao.OrderDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PayServlet", value = "/PayServlet")
public class PayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接受到支付请求了");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int uid = Integer.parseInt(request.getParameter("uid"));
        int sid = Integer.parseInt(request.getParameter("sid"));

        OrderDao orderDao = new OrderDao();
        int res = orderDao.addOrder(sid,uid);       // 将订单写入数据库

        PrintWriter writer=response.getWriter();
        String result="";
        System.out.println("servlet中：res="+  res);
        response.setStatus(HttpServletResponse.SC_OK);
//        result= new Gson().toJson(departs, new TypeToken<List<Depart>>() {}.getType());
//        writer.write(result);
//        writer.flush();
//        writer.close();

        System.out.println("订单预约结束" + result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
