package com.mvc.controller;

import com.mvc.dao.UserDao;
import com.mvc.entities.NguoidungEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.*;
import org.hibernate.graph.RootGraph;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.stat.SessionStatistics;

import javax.persistence.*;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "SignupController", urlPatterns = {"/signup"})
public class SignupController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = (request.getParameter("userFullname")).trim();
        String gender = request.getParameter("userGender");
        String birthdateInString = request.getParameter("userBirthdate");
        if (birthdateInString.equals(""))
        {
            birthdateInString = "2000-01-01";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdateInJavaDate = null;
        try 
        {
            birthdateInJavaDate = simpleDateFormat.parse(birthdateInString);
        }
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        if (birthdateInJavaDate == null)
        {
            birthdateInJavaDate = new Date();
        }
        java.sql.Date birthdateInSqlDate = new java.sql.Date(birthdateInJavaDate.getTime());
        String address = (request.getParameter("userAddress")).trim();
        String phone = (request.getParameter("userPhone")).trim();
        String email = (request.getParameter("userEmail")).trim();
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");

        boolean hasError = false;

        if (fullname.equals(""))
        {
            hasError = true;
            request.setAttribute("fullnameError","Bạn phải nhập họ và tên");
        }

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);

        boolean rightBirthdate = session.doReturningWork(connection -> {
            try (CallableStatement function = connection.prepareCall("{? = CALL func_NgaySinhHopLe(?)}"))
            {
                function.registerOutParameter(1, Types.BIT);
                function.setDate(2, birthdateInSqlDate);
                function.execute();
                return function.getBoolean(1);
            }
        });
        if (!rightBirthdate)
        {
            hasError = true;
            request.setAttribute("birthdateError","Ngày sinh không hợp lệ");
        }
        else
        {
            request.setAttribute("birthdateError", null);
        }

        if (address.equals(""))
        {
            hasError = true;
            request.setAttribute("addressError","Bạn phải nhập địa chỉ");
        }

        Pattern phonePattern = Pattern.compile("\\d{10}");
        Matcher phoneMatcher = phonePattern.matcher(phone);
        if (!phoneMatcher.matches())
        {
            hasError = true;
            request.setAttribute("phoneError","Số điện thoại phải có 10 chữ số");
        }
        else
        {
            boolean rightPhone = session.doReturningWork(connection -> {
                try (CallableStatement function = connection.prepareCall("{? = CALL func_DienThoaiHopLe(?)}"))
                {
                    function.registerOutParameter(1, Types.BIT);
                    function.setString(2, phone);
                    function.execute();
                    return function.getBoolean(1);
                }
            });
            if (!rightPhone)
            {
                hasError = true;
                request.setAttribute("phoneError","Số điện thoại này đã được sử dụng");
            }
            else
            {
                request.setAttribute("phoneError", null);
            }
        }

        Pattern emailPattern = Pattern.compile("\\w+@\\w+(.\\w+)*");
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches())
        {
            hasError = true;
            request.setAttribute("emailError","Email không hợp lệ");
        }
        else
        {
            boolean rightEmail = session.doReturningWork(connection -> {
                try (CallableStatement function = connection.prepareCall("{? = CALL func_EmailHopLe(?)}"))
                {
                    function.registerOutParameter(1, Types.BIT);
                    function.setString(2, email);
                    function.execute();
                    return function.getBoolean(1);
                }
            });
            if (!rightEmail)
            {
                hasError = true;
                request.setAttribute("emailError","Email này đã được sử dụng");
            }
            else
            {
                request.setAttribute("emailError", null);
            }
        }

        boolean rightPassword = session.doReturningWork(connection -> {
            try (CallableStatement function = connection.prepareCall("{? = CALL func_MatKhauHopLe(?)}"))
            {
                function.registerOutParameter(1, Types.BIT);
                function.setString(2, password);
                function.execute();
                return function.getBoolean(1);
            }
        });
        if (!rightPassword)
        {
            hasError = true;
            request.setAttribute("passwordError","Mật khẩu phải có độ dài từ 8 đến 50 kí tự");
        }
        else
        {
            request.setAttribute("passwordError", null);
        }

        if (!retypePassword.equals(password))
        {
            hasError = true;
            request.setAttribute("retypePasswordError","Mật khẩu không trùng khớp");
        }
        else
        {
            request.setAttribute("retypePasswordError",null);
        }

        String url;
        if (hasError)
        {
            request.setAttribute("userFullname", fullname);
            request.setAttribute("userGender", gender);
            request.setAttribute("userBirthdate", birthdateInString);
            request.setAttribute("userAddress", address);
            request.setAttribute("userPhone", phone);
            request.setAttribute("userEmail", email);

            request.setAttribute("signupSuccess","false");
            url = "/signup.jsp";
        }
        else
        {
            String userID = session.doReturningWork(connection -> {
                try (CallableStatement function = connection.prepareCall("{? = CALL func_TaoMa(?)}"))
                {
                    function.registerOutParameter(1, Types.VARCHAR);
                    function.setString(2, "KH");
                    function.execute();
                    return function.getString(1);
                }
            });

            UserDao userDao = new UserDao();
            NguoidungEntity user = new NguoidungEntity(userID, fullname, gender, birthdateInSqlDate, address, phone, email, password);
            boolean canExecute = userDao.saveUser(user);

            if (canExecute)
            {
                request.setAttribute("signupSuccess","true");
                url = "/login.jsp";
            }
            else
            {
                request.setAttribute("userFullname", fullname);
                request.setAttribute("userGender", gender);
                request.setAttribute("userBirthdate", birthdateInString);
                request.setAttribute("userAddress", address);
                request.setAttribute("userPhone", phone);
                request.setAttribute("userEmail", email);

                request.setAttribute("signupSuccess","false");
                url = "/signup.jsp";
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
