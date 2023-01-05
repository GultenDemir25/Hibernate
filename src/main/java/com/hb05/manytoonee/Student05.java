package com.hb05.manytoonee;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Student05 {

    @Id
    private int id;


    @Column(name="student_name" ,nullable = false)
    private String name;

    private int grade;
    private LocalDateTime createOn;  // Create edilme tarihi

    @ManyToOne
    @JoinColumn(name="university_id")
    private University university;



    @PrePersist // create tarihi  persist(kalici) edildigindeki zaman
    public void prePersist(){
        createOn=LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getCreateOn() {
        return createOn;
    }

//    public void setCreateOn(LocalDateTime createOn) {
//        this.createOn = createOn;
//    } --->creste edilme tarihi degistirilemesin diye yoruma aldik

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student05{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", createOn=" + createOn +
                ", university=" + university +
                '}';
    }
}
