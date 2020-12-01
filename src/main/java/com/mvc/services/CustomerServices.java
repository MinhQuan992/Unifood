package com.mvc.services;

import com.mvc.bean.UserBean;
import com.mvc.dao.UserDao;
import org.apache.commons.lang3.RandomStringUtils;

public class CustomerServices {
    public String resetCustomerPassword(String email)
    {
        UserDao userDao = new UserDao();
        UserBean userBean = userDao.findUser(email);

        String randomPassword = RandomStringUtils.randomAlphanumeric(10);

        userBean.setPassword(randomPassword);
        userDao.updateUser(userBean);

        return randomPassword;
    }
}
