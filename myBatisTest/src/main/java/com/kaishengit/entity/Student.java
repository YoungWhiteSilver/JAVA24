package com.kaishengit.entity;

/**
 *
 * @author silver
 * @date 2017/10/23
 */
public class Student {

    public Student() {}

    public Student(String stuName, int stuAge, String stuAddress) {
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.stuAddress = stuAddress;
    }

    private int id;
    private String stuName;
    private int stuAge;
    private String stuAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                ", stuAddress='" + stuAddress + '\'' +
                '}';
    }

}
