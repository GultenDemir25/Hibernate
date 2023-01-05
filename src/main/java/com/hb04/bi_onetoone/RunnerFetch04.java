package com.hb04.bi_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch04 {

    public static void main(String[] args) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student04.class).addAnnotatedClass(Diary04.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //Belli Idli studenti getirelim:
        Student04 student = session.get(Student04.class, 1001);

        //diary get edelim
//        Diary04 diary= session.get(Diary04.class,101);
//        System.out.println(student);
//        System.out.println("****************");
//        System.out.println(diary);
//
//        System.out.println("************");
//        System.out.println(diary.getStudent());// diaryden studenta ulasabiliyorum
//        System.out.println(student.getDiary());// studenttan diarye ulasabiliyorum


        //iki clasin kesisim kumesini getirelim (inner join)

        String hqlQuery = "SELECT s.name, d.name FROM Student04 s INNER JOIN FETCH Diary04 d on s.id=d.student";

        //UStteki HQL kodun SQL hali:select s.std_name,d.name from student04 s inner joim diary d on s.id=d


        List<Object[]> resultList = session.createQuery(hqlQuery).getResultList();
        for (Object[] object : resultList) {
            System.out.println(Arrays.toString(object));

        }

//           resultList.forEach(oa->{  // ======>>Lamda ile yazdik
//               System.out.println(Arrays.toString(oa));
//           });

        //  HQL LEFT JOIN**********

        //Kisaca butun ogrenciler ve gunlugu  olan ogrencileri istiyorum.


        String hqlQuery2 =
                "SELECT s.name,d.name FROM Student04 s LEFT JOIN FETCH Diary04 d on s.id=d.student";


//        List<Object[]> resultLIst2 = session.createQuery(hqlQuery2).getResultList();
//        resultLIst2.forEach(oa ->
//                System.out.println(Arrays.toString(oa)));


        //HQL RIGHT JOIN*********

        // butun gunlukler ve gunlugu olan ogrenciler

//        String hqlQuery3 =
//                "SELECT s.name,d.name FROM Student04 s RIGHT JOIN FETCH Diary04 d on s.id=d.student";
//
//        List<Object[]> resultLIst3 = session.createQuery(hqlQuery3).getResultList();
//        resultLIst3.forEach(oa ->
//                System.out.println(Arrays.toString(oa)));
//



        //!!! HQL FULL JOIN************

        // butun students ve diary bilgilerini getir

        String hqlQuery4 =
                "SELECT s.name, d.name FROM Student04 s FULL JOIN FETCH Diary04 d on s.id= d.student.id";
        List<Object[]> resultLIst4 = session.createQuery(hqlQuery4).getResultList();
       resultLIst4.forEach(oa ->
               System.out.println(Arrays.toString(oa)));



        tx.commit();
        session.close();
        sf.close();

    }
}
