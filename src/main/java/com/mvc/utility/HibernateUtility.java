package com.mvc.utility;

import com.mvc.entities.*;
import com.mvc.entities.NguoidungEntity;
import com.mvc.entities.ViewChiTietDonHangEntity;
import com.mvc.entities.ViewDonHangEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtility {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                /*settings.put(Environment.URL, "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD");*/
                settings.put(Environment.URL, "jdbc:sqlserver://unifoods.cdvtbrr1vblp.us-east-2.rds.amazonaws.com\\UNIFOODS:1433;database=UNIFOOD;user=admin;password=Ninh2000");
                settings.put(Environment.USER, "admin");
                settings.put(Environment.PASS, "Ninh2000");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "none");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(NguoidungEntity.class);
                configuration.addAnnotatedClass(SanphamEntity.class);
                configuration.addAnnotatedClass(NhomsanphamEntity.class);
                configuration.addAnnotatedClass(AnkemEntity.class);
                configuration.addAnnotatedClass(DathangEntity.class);
                configuration.addAnnotatedClass(GiohangEntity.class);
                configuration.addAnnotatedClass(DonhangEntity.class);
                configuration.addAnnotatedClass(SanphamEntity.class);
                configuration.addAnnotatedClass(ViewDonHangEntity.class);
                configuration.addAnnotatedClass(ViewChiTietDonHangEntity.class);
                configuration.addAnnotatedClass(KhohangEntity.class);
                configuration.addAnnotatedClass(DonvigiaohangEntity.class);
                configuration.addAnnotatedClass(ViewAllOrderEntity.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
