package com.example.palmhospitalservice.servlet;

import com.example.palmhospitalservice.bean.User;
import com.example.palmhospitalservice.dao.UserDao;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 接收参数
        String uid = request.getParameter("uid");
        String upsw = request.getParameter("upsw");

        UserDao userDao = new UserDao();
        User user = userDao.login(uid,upsw);

        PrintWriter writer=response.getWriter();
        String result="";

        if(user == null){   // 登录失败
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else{       // 登录成功
            response.setStatus(HttpServletResponse.SC_OK);
            result= new Gson().toJson(user, User.class);        // 将user的数据变成json格式，传输给客户端
            writer.write(result);
            writer.flush();
            writer.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
