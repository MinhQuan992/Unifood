package com.mvc.dao;

import com.mvc.entities.NguoidungEntity;
import com.mvc.utility.HibernateUtility;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {
    public boolean saveUser(NguoidungEntity user)
    {
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("Save user successfully");
            return true;
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(NguoidungEntity user)
    {
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            System.out.println("Update user successfully");
            return true;
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(String userID)
    {
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            NguoidungEntity user = session.get(NguoidungEntity.class, userID);
            if (user != null)
            {
                session.delete(user);
                System.out.println("Delete user successfully");
            }
            transaction.commit();
            return true;
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public NguoidungEntity getUserByID(String userID)
    {
        Transaction transaction = null;
        NguoidungEntity user = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            user = session.get(NguoidungEntity.class, userID);
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public NguoidungEntity getUserByEmail(String email)
    {
        Transaction transaction = null;
        NguoidungEntity user = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("FROM NguoidungEntity WHERE email=:email");
            query.setParameter("email", email);
            user = (NguoidungEntity) query.uniqueResult();
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List< NguoidungEntity > getAllUser()
    {
        Transaction transaction = null;
        List < NguoidungEntity > listOfUser = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            listOfUser = session.createQuery("FROM NguoidungEntity").getResultList();
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }
}
