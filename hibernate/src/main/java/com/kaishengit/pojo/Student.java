package com.kaishengit.pojo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/27
 */
public class Student {

    public Student() {
    }

    private Integer id;
    private String stuName;
    private Integer stuAge;
    private String stuAddress;
    private StudentClass studentClass;


    public Student(String stuName, Integer stuAge, String stuAddress) {
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.stuAddress = stuAddress;
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

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
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

