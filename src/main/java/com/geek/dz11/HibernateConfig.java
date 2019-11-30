package com.geek.dz11;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HibernateConfig {
    private static SessionFactory factory;

    private HibernateConfig() {
        HibernateConfig.factory = new Configuration()
                .configure("configs/postgre.cfg.xml")
                .buildSessionFactory();

        prepareData();
    }

    static public SessionFactory getFactory() {
        if (HibernateConfig.factory == null) {
            HibernateConfig config = new HibernateConfig();
        }
        return HibernateConfig.factory;
    }

    private void prepareData() {
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("./src/main/resources/prepare.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}