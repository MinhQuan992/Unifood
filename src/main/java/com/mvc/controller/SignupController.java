package com.mvc.controller;

import com.mvc.dao.UserDao;
import com.mvc.entities.NguoidungEntity;
import com.mvc.utility.EmailUtility;
import com.mvc.utility.HibernateUtility;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.*;

import javax.persistence.*;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "SignupController", urlPatterns = {"/signup"})
public class SignupController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String host;
    private String port;
    private String socketFactoryClass;
    private String auth;
    private String email;
    private String name;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        socketFactoryClass = context.getInitParameter("socketFactoryClass");
        auth = context.getInitParameter("auth");
        email = context.getInitParameter("email");
        name = context.getInitParameter("name");
        pass = context.getInitParameter("pass");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

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
        String userEmail = (request.getParameter("userEmail")).trim();

        boolean hasError = false;

        if (fullname.equals(""))
        {
            hasError = true;
            request.setAttribute("fullnameError","Bạn phải nhập họ và tên");
        }

        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = HibernateUtility.getSessionFactory().openSession(); //entityManager.unwrap(Session.class);

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
        Matcher emailMatcher = emailPattern.matcher(userEmail);
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
                    function.setString(2, userEmail);
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

        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{10,50}$");
        String randomPassword;
        Matcher passwordMatcher;
        do {
            randomPassword = RandomStringUtils.randomAlphanumeric(10);
            passwordMatcher = passwordPattern.matcher(randomPassword);
        } while (!passwordMatcher.matches());

        String url;
        boolean isManager;
        String prefix;
        String userType = (String) request.getSession().getAttribute("userType");
        if (userType.equals("Manager"))
        {
            isManager = true;
            prefix = "QL";
        }
        else
        {
            isManager = false;
            prefix = "KH";
        }
        if (hasError)
        {
            request.setAttribute("userFullname", fullname);
            request.setAttribute("userGender", gender);
            request.setAttribute("userBirthdate", birthdateInString);
            request.setAttribute("userAddress", address);
            request.setAttribute("userPhone", phone);
            request.setAttribute("userEmail", userEmail);

            request.setAttribute("signupSuccess",false);
            url = "/signup.jsp";
        }
        else
        {
            String userID = session.doReturningWork(connection -> {
                try (CallableStatement function = connection.prepareCall("{? = CALL func_TaoMa(?)}"))
                {
                    function.registerOutParameter(1, Types.VARCHAR);
                    function.setString(2, prefix);
                    function.execute();
                    return function.getString(1);
                }
            });

            UserDao userDao = new UserDao();
            NguoidungEntity user = new NguoidungEntity(userID, fullname, gender, birthdateInSqlDate, address, phone, userEmail, randomPassword);
            boolean canExecute = userDao.saveUser(user, isManager);
            String message = "";
            String subject;
            String content;

            if (canExecute)
            {
                if (!isManager)
                {
                    subject = "Tai khoan cua ban da duoc tao thanh cong";
                    content = "Xin chan thanh cam on ban da su dung dich vu cua UNIFOOD, day la thong tin dang nhap cua ban:";
                    content += "\nEmail: " + userEmail;
                    content += "\nMat khau: " + randomPassword;
                    content += "\nVi li do bao mat, ban phai doi mat khau ngay sau khi dang nhap.";
                    content += "\nXin kinh chuc ban that nhieu suc khoe va se dong hang lau dai cung UNIFOOD!";
                    content += "\nTran trong.";
                    content += "\nDoi ngu ho tro UNIFOOD";

                    try
                    {
                        EmailUtility.sendEmail(host, port, socketFactoryClass, auth, email, name, pass,
                                userEmail, subject, content);
                        message = "Tài khoản của bạn đã được tạo thành công, hãy kiểm tra email để lấy thông tin đăng nhập!";
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                        message = "Có lỗi xảy ra khi gửi thông tin đăng nhập đến email của bạn, mời bạn thử lại!";
                    }
                    request.setAttribute("message", message);
                    url = "/message.jsp";
                }
                else
                {
                    request.setAttribute("userFullname", null);
                    request.setAttribute("userGender", null);
                    request.setAttribute("userBirthdate", null);
                    request.setAttribute("userAddress", null);
                    request.setAttribute("userPhone", null);
                    request.setAttribute("userEmail", null);

                    subject = "Tai khoan quan ly cua ban da duoc tao thanh cong";
                    content = "Xin chao ban, ban vua duoc mot quan ly cua UNIFOOD tao tai khoan thanh cong, day la thong tin dang nhap cua ban:";
                    content += "\nEmail: " + userEmail;
                    content += "\nMat khau: " + randomPassword;
                    content += "\nVi li do bao mat, ban phai doi mat khau ngay sau khi dang nhap.";
                    content += "\nXin kinh chuc ban that nhieu suc khoe va se dong hang lau dai cung UNIFOOD!";
                    content += "\nTran trong.";
                    content += "\nDoi ngu ho tro UNIFOOD";

                    try
                    {
                        EmailUtility.sendEmail(host, port, socketFactoryClass, auth, email, name, pass,
                                userEmail, subject, content);
                        request.setAttribute("signupSuccess",true);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                        request.setAttribute("signupSuccess",false);
                    }
                    url = "/signup.jsp";
                }
            }
            else
            {
                request.setAttribute("userFullname", fullname);
                request.setAttribute("userGender", gender);
                request.setAttribute("userBirthdate", birthdateInString);
                request.setAttribute("userAddress", address);
                request.setAttribute("userPhone", phone);
                request.setAttribute("userEmail", userEmail);

                request.setAttribute("signupSuccess",false);
                url = "/signup.jsp";
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}