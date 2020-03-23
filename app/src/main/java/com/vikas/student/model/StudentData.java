package com.vikas.student.model;

public class StudentData {
    String name;
    String rollno;
    String branch;
    String sem;

    public StudentData(String name, String rollno, String branch, String sem) {
        this.name = name;
        this.rollno = rollno;
        this.branch = branch;
        this.sem = sem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }
}
