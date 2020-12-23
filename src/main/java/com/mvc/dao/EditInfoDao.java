package com.mvc.dao;

import com.mvc.entities.NguoidungEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EditInfoDao {
    // String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";

    public static NguoidungEntity authorizeEditInfo(String userID)
    {
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NguoidungEntity user = session.get(NguoidungEntity.class, userID);
            transaction.commit();
            return user;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public static String updateUserInfo(NguoidungEntity user)
    {
        String status = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            status = e.getMessage();
        }
        return status;
    }
}
