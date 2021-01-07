package com.mvc.controller;

import com.mvc.dao.UserDao;
import com.mvc.entities.ListItemEntity;
import com.mvc.entities.NguoidungEntity;
import com.mvc.utility.EmailUtility;
import sun.nio.ch.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "indexController", urlPatterns = {"/home","/signin"})
public class indexController extends HttpServlet {

    private NguoidungEntity nguoidungEntity;

    private static final long serialVersionUID = 2686801501274002166L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action != null && action.equals("signin")){
        NguoidungEntity nguoidungEntity1 = EmailUtility.sendEmail(NguoidungEntity.class, request);
    } else{
        response.sendRedirect(request.getContextPath()+"/signin");
    }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null && action.equals("signin")){
            RequestDispatcher rd=request.getRequestDispatcher("/signin.jsp");
            rd.forward(request,response);
        } else if (action!=null && action.equals("signout"))
        {

        } else
        {
            request.setAttribute("nguoidungEntity", nguoidungEntity.getMaNguoiDung());
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }
}
