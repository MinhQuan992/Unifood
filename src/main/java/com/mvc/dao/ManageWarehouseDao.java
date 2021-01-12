package com.mvc.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.mvc.entities.KhohangEntity;
import com.mvc.entities.NguoidungEntity;
import com.mvc.entities.SanphamEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.*;

public class ManageWarehouseDao {
    //private String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";
    //private PreparedStatement statement = null;
    //private Connection connection = null;

    public static KhohangEntity GetWarehouse(String maKho)
    {
        KhohangEntity kh = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            kh = session.get(KhohangEntity.class, maKho);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return kh;
    }

    public static List<KhohangEntity> GetWarehouses()
    {
        List<KhohangEntity> list = null;

        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("SELECT k FROM KhohangEntity k", KhohangEntity.class).getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }

    public static SanphamEntity GetItem(String maSanPham)
    {
        SanphamEntity sp = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            sp = session.get(SanphamEntity.class, maSanPham);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sp;
    }

    public static List<SanphamEntity> GetItemsInWarehouse(KhohangEntity kh)
    {
        List<SanphamEntity> list = null;

        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("SELECT sp FROM SanphamEntity sp WHERE sp.maKho=:maKho", SanphamEntity.class)
                    .setParameter("maKho", kh.getMaKho())
                    .getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }

    public static String AddWarehouse(KhohangEntity kh)
    {
        String status = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(kh);
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

    public static String EditWarehouse(KhohangEntity kh)
    {
        String status = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(kh);
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

    public static String DeleteWarehouse(String maKho)
    {
        String status = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            KhohangEntity kh = session.get(KhohangEntity.class, maKho);
            if (kh != null) {
                session.delete(kh);
            }
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

    public static String AddItemInWarehouse(SanphamEntity sp)
    {
        String status = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(sp);
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

    public static String EditItemInWarehouse(SanphamEntity sp)
    {
        String status = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(sp);
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

    public static String DeleteItemInWarehouse(String maSanPham)
    {
        String status = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            SanphamEntity sp = session.get(SanphamEntity.class, maSanPham);
            if (sp != null) {
                session.delete(sp);
            }
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
