package com.mvc.utility;

import com.mvc.entities.NguoidungEntity;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class EmailUtility {
    public static NguoidungEntity sendEmail(String host, String port, String socketFactoryClass, String auth,
                                            final String senderEmail, String senderName, final String password,
                                            String recipientEmail, String subject, String message) throws MessagingException, UnsupportedEncodingException {
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", socketFactoryClass);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.port", port);
        //Get Session
        Authenticator authenticator = new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        };
        Session session = Session.getDefaultInstance(props, authenticator);
        //Create a new e-mail message
        Message msg = new MimeMessage(session);
        msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
        msg.setFrom(new InternetAddress(senderEmail, senderName));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);
        //Send the e-mail
        Transport.send(msg);
        return null;
    }

    public static NguoidungEntity sendEmail(Class<NguoidungEntity> nguoidungEntityClass, HttpServletRequest request) {
        return null;
    }
}
