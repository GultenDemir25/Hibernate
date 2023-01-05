package com.hb10.idgeneration;

import javax.persistence.*;

@Entity
public class Student10 {
    /*
    Oracle DB ile PostgreSQL ====>id generate ederken Sequence kullanir(KOntrolu developera birakir,Id üretilirken başlangıç değeri veya kaç tane id cachelenecek bu gibi bilgileri developer setliyebilir)
    MySql ve MicrosoftSql ====> id generate ederken identity kullanir(Control DBde kendi yapisina gore ID olusturur, en basitidir)

    GenerationType.AUTO====>>Hibernateg otomatik olarak stratejiyi belirler.
    GenerationType.TABLE =====>> En yavasi, o yuzden cok kullanilmiyor. Cunku id uretmek icin ekstra table olusturuyor
     */
    @GeneratedValue(generator ="sequence", strategy = GenerationType.AUTO) // Eyy Hibernate olusturacagim objelerin idsini sen olustur..!!
    @SequenceGenerator(name="sequence",//@generationValue nun generator parametresi ile ayni olmali
            sequenceName = "student_seq", //DB de olusaack sequance ismi
            initialValue = 1000, //idlerim 1000 ile baslasin
            allocationSize = 10)//catchleme mekanizmasinda 10 adet id hazirda beklesin...

    @Id
    private  int id;
    @Column(name="student_name", nullable = false)
    private String name;

    private int grade;

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student10{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
