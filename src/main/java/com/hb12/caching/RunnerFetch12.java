package com.hb12.caching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch12 {
    public static void main(String[] args) {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student12.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //1..
//        System.out.println("Ilk get islemi 1 idli ogrenci icin:");
//        Student12 student1 =session.get(Student12.class,1L);

        //session.clear();

//        System.out.println("Ikinci get islemi 1 idli ogrenci icin:");
//        Student12 student2 = session.get(Student12.class, 1L);

        tx.commit();
        session.close();

        // first level cache mekanizmasinda alt satirdaki kodlar icin yeni bir query olusturulur...

        Session session2 = sf.openSession();
        Transaction tx2 = session2.beginTransaction();
        System.out.println("session close sonrasi get islemi 1idli ogrenci icin :");
        Student12 student3 = session2.get(Student12.class, 1L);


        tx2.commit();
        session2.close();
        sf.close();
    }


}
