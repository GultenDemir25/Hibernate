package com.hb01.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch01 {

    public static void main(String[] args) {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student01.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

     /*DBden bilgi almak icin 3 yol:
        1)get()
        2)SQL
        3)HQL

      */

       // 1. yol:get()*********
        Student01 student1 = session.get(Student01.class, 1001); //====>> get methodu id ile calisir
        Student01 student2 = session.get(Student01.class, 1002);
        Student01 student3 = session.get(Student01.class, 1003);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);

        //2. yol:sql**********
//        String sqlQuery="SELECT * FROM t_student01"; //===>> sorguda table ismi kullandik
//        List<Object[]> resultList = session.createSQLQuery(sqlQuery).getResultList(); //===>> Bana list donecek
//       for (Object[] object :resultList){
//           System.out.println(Arrays.toString(object));
//       }
//
        //3.yol:hql***********

        //Trick: HQL sorgusunda fromdan sonra sinif ismi kullanilmali
//        String hqlQuery = "FROM Student01";
//        List<Student01> resultList2 = session.createQuery(hqlQuery, Student01.class).getResultList();  //==> bana class gelecek
//        for (Student01 student01: resultList2) {
//            System.out.println(student01);
//        }


        //uniqueResult() with SQL*********************************
        //Ddonecek kaydin unique(tek bir tane) oldugundan eminsek uniqueResult() methodu kullanilabilir...

//        String sqlQuery2="SELECT * FROM t_student01 WHERE student_name='Tarık'";
//       Object[] uniqueResult1 = (Object[]) session.createSQLQuery(sqlQuery2).uniqueResult();
//        System.out.println(Arrays.toString(uniqueResult1));
//
//      // Yukaridaki kod calistirilinca 1 tane obje gelecek aslinda AMA  icinde sutunlar oldugu icin array turunde obje geldi
//        System.out.println(uniqueResult1[0] + " : " + uniqueResult1[1] +" : "+ uniqueResult1[2]);

        //unigueResult() with HQL *************************************
//        String hqlQuery2= "FROM Student01 WHERE name ='Tarık'";
//        Student01 uniqueResult2 = session.createQuery(hqlQuery2, Student01.class).uniqueResult();
//        System.out.println(uniqueResult2);

     //   yukarıdakı sorguyu HQL  Alıas kullanarak yapalım.

//        String hqlQuery3= "FROM Student01 std WHERE std.name= 'Miraç'";
//        Student01 unıqueResult3 =session.createQuery(hqlQuery3, Student01.class).uniqueResult();
//        System.out.println(unıqueResult3);

        //grade degeri 90 olan ogrenciyi getirelim
        String hqlQuery4="SELECT s.id,s.name FROM Student01 s WHERE s.grade=90"; //Student01 clasina s ismini verdik..
        List <Object[]> resultList4 = session.createQuery(hqlQuery4).getResultList();
       //createQuery methoduna tek parametre girdigimiz icin  Student01 clasi ile maplema islemi yapilmadi. bu yuzden object olarak aldik
        for (Object[] object : resultList4) {
            System.out.println(Arrays.toString(object));

        }

        tx.commit();
        session.close();
        sf.close();
    }
}
