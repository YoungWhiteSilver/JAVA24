package com.kaishengit.entity;

public class Student {
    private Integer id;

    private String stuName;

    private Integer stuAge;

    private String stuAddress;

    private String stuClass;

    private StudentClass stuClassName;

    public StudentClass getStuClassName() {
        return stuClassName;
    }

    public void setStuClassName(StudentClass stuClassName) {
        this.stuClassName = stuClassName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }
}