package com.museum.controller;

//import com.museum.Listener.UserListener;
import com.museum.pojo.Admin;
import com.museum.service.ALogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 名称:LoginController
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-29 11:27
 */


@Controller
@RequestMapping("checkLogin")
public class LoginController extends HttpServlet {

    @Autowired(required = false)
    private ALogInService aLogInService;
    @RequestMapping("/tologin")
    public String tologin(){
        return "Login";
    }
    @RequestMapping("/doget")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        List<Admin> adminList=aLogInService.logInSearch(username,password);

            if (adminList.size() != 0) {
                HttpSession session = request.getSession();
                System.out.println(session.getAttribute("username"));
                session.setMaxInactiveInterval(60 * 60 * 24);
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/index");
                //return "index";
            } else {
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write("<h1>账号或密码错误<h1>");
                writer.write("<h3><a href='" + request.getContextPath() + "/tologin'>点击重新登录</a></h3>");
                //return "login";
            }
        /*finally {
            if(response!=null)
            {
                response.getWriter().flush();
                response.getWriter().close();
            }
        }*/
    }

   @RequestMapping("/dopost")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
       request.getSession().setAttribute("isLogged", true);
        //response.reset();
    }

}
