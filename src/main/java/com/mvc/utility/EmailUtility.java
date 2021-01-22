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
        props.put("mail.smtp.host", "smtp.gmail.com");
        //props.put("mail.smtp.socketFactory.port", "587");
        //props.put("mail.smtp.socketFactory.class", socketFactoryClass);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        //Get Session

        Authenticator authenticator = new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        };

        Session session = Session.getInstance(props, authenticator);

        try
        {
            Message mail = new MimeMessage(session);
            mail.setFrom(new InternetAddress(senderEmail));
            mail.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail)
            );
            mail.setSubject(subject);
            mail.setText(message);
            mail.setSentDate(new Date());
            mail.setFrom(new InternetAddress(senderEmail, senderName));
            Transport.send(mail);
            System.out.println("Send Email Done!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static NguoidungEntity sendEmail(Class<NguoidungEntity> nguoidungEntityClass, HttpServletRequest request) {
        return null;
    }
}