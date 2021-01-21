package com.mvc.dao;

import com.mvc.entities.*;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class PaymentDao {

    public static List<DathangEntity> GetDatHang(Integer maGio)
    {
        List<DathangEntity> list = null;

        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("SELECT d FROM DathangEntity d WHERE d.maGio=:maGio", DathangEntity.class)
                    .setParameter("maGio", maGio)
                    .getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<DonvigiaohangEntity> GetDVGiaoHang()
    {
        List<DonvigiaohangEntity> list = null;

        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("SELECT d FROM DonvigiaohangEntity d", DonvigiaohangEntity.class)
                    .getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<DonhangEntity> GetDonHang()
    {
        List<DonhangEntity> list = null;

        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("SELECT d FROM DonhangEntity d", DonhangEntity.class)
                    .getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static String AddDonHang(DonhangEntity don)
    {
        String status = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(don);
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

    public static List<DonhangEntity> GetAllDonHang() {
        List<DonhangEntity> list = null;

        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("SELECT d FROM DonhangEntity d", DonhangEntity.class)
                    .getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static DonhangEntity GetDonHang(int maDon) {
        DonhangEntity don = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            don = session.get(DonhangEntity.class, maDon);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return don;
    }


    public ViewAllOrderEntity getPaymentData(int cartCode)
    {
        ViewAllOrderEntity payment = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query<ViewAllOrderEntity> query = session.createQuery("SELECT DH FROM ViewAllOrderEntity DH WHERE DH.maDon=:CartCode");
            query.setParameter("CartCode",cartCode);
            payment = query.uniqueResult();
            transaction.commit();
        } catch (Exception var9) {
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }
        return payment;
    }

    public boolean UpdateOrderData(DonhangEntity payment)
    {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(payment);
            transaction.commit();
        } catch (Exception var8) {
            if (transaction != null) {
                transaction.rollback();
            }
            var8.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    public DonhangEntity getPaymentDataRaw(int cartCode) {
        DonhangEntity payment = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query<DonhangEntity> query = session.createQuery("SELECT DH FROM DonhangEntity DH WHERE DH.maDon=:CartCode");
            query.setParameter("CartCode",cartCode);
            payment = query.uniqueResult();
            transaction.commit();
        } catch (Exception var9) {
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }
        return payment;
    }

    public List<ViewAllOrderEntity> getAllPaymentData() {
        List<ViewAllOrderEntity> payment = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query<ViewAllOrderEntity> query = session.createQuery("FROM ViewAllOrderEntity ");
            payment = query.getResultList();
            transaction.commit();
        } catch (Exception var9) {
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }
        return payment;
    }

    public static String EditDonHang(DonhangEntity don)
    {
        String status = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(don);
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
