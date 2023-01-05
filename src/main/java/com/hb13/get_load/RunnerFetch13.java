package com.hb13.get_load;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLOutput;

/*
   get() ---> gerçek nesneyi döndürür ,
   			  nesne yoksa null döner
   			  nesnenin olduğundan emin değilseniz get() kullanın
   			  dönen nesneyi hemen kullanacaksam get() kullanılmalı
   load() --> proxy nesne döndürür, gerçek nesnenin gölgesi ,
   			  nesne yoksa exception fırlatır
   			  dönen nesne üzerinde delete yapılacaksa kullanılabilir
 */
public class RunnerFetch13 {
    public static void main(String[] args) {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student13.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


//
//        Student13 student1 = session.get(Student13.class, 1L);
//        System.out.println("get methodunun bittigi satir");
//
//        System.out.println("Student ID :" + student1.getId());
//        System.out.println("Student get Name :" +student1.getName());


//        System.out.println("**************************");
//        //yukaridaki senaryonun aynisini load() methodu ile yapalim..
//        System.out.println("Load methodunun baslangic yeri :");
//        Student13 student2=session.load(Student13.class,2L);  // bu calistiginda proxy nesne olusur
//        System.out.println(" getName() cagrildi");
//        System.out.println("Student2nin ismi ;" +student2.getName());
//        System.out.println("getName() bitti");
//        System.out.println("Load methodunun bittigi satir :");
//
//        //DBde olmayan IDyi cagiralimm
//        System.out.println("get() methodu calismaya basladi");
//        Student13 student3=session.get(Student13.class,5L);
//        System.out.println("get() methodu bitti");
//        if(student3!=null){
//            System.out.println("Student ID :" +student3.getId());
//            System.out.println("Student name :" +student3.getName());
//        }
//        System.out.println("*************************");
//        System.out.println("load() methodu çalışmaya başladı :");
//        Student13 student4 = session.load(Student13.class,10L); //
//        System.out.println("load() metodu bitti");
//
//        if(student4 !=null ) { // true
//            System.out.println("Student ID : " + student4.getId()); // ObjectNotFoundException
//            System.out.println("Student Get Name : " + student4.getName());
//        }

        // !!! Peki load() Methodunu niye kullanayım ????

        Student13 student5 = session.get(Student13.class, 1L);
        session.delete(student5);
        // load methodu ile obje referansı alınır ve sonra delete çağrılır
        // get metodu çağrılmayarak DB ye hit( database e sorgu göndermek) engellenmiş olur

        Student13 student6 = session.load(Student13.class,1L);
        session.delete(student6);







        tx.commit();
        session.close();
        sf.close();
    }


}
