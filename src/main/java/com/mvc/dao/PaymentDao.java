package com.mvc.dao;

import com.mvc.entities.*;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public static List<MagiamgiaEntity> GetMaGiamGia()
    {
        List<MagiamgiaEntity> list = null;

        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("SELECT m FROM MagiamgiaEntity m", MagiamgiaEntity.class)
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

    public static MagiamgiaEntity ReceivedMaGiamGia(int tongGiaTri)
    {
        List<MagiamgiaEntity> list = GetMaGiamGia();
        if (list != null) {
            java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            list.sort(Comparator.comparing(MagiamgiaEntity::getGtghToiThieu).reversed());
            for (MagiamgiaEntity m: list) {
                if (m.getNgayBatDau().before(today) && m.getNgayKetThuc().after(today)
                        && tongGiaTri >= m.getGtghToiThieu()) {
                    return m;
                }
            }
        }
        return null;
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
}
