package com.museum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 名称:LoggedController
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-29 16:38
 */
@Controller
//@SessionAttributes("LoggedIn")
//@WebServlet("/mainPage")
@RequestMapping("/user")
public class LoggedController extends HttpServlet {

    //@GetMapping("/dashboard")
    @Autowired(required = false)
    private AdminController adminController;
    @RequestMapping("/adminindex")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        System.out.println("Logged页面："+session.getAttribute("username"));
        Object username=session.getAttribute("username");
        //PrintWriter writer=response.getWriter();

            if (username != null) {
                //writer.write("<h1>用户："+username+" 登录成功</h1>");
                response.sendRedirect(request.getContextPath() + "/museum/index");
            } else {
                response.sendRedirect(request.getContextPath() + "/checkLogin/tologin");
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
        //response.reset();
    }


}
