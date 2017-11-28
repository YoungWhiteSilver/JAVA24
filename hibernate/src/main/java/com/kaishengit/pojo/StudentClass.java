package com.kaishengit.pojo;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/28
 */
public class StudentClass {

    private Integer id;
    private String className;
    private Integer classHot;
    private Set<Student> studentSet;

    @Override
    public String toString() {
        return "StudentClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", classHot=" + classHot +
                ", studentSet=" + studentSet +
                '}';
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassHot() {
        return classHot;
    }

    public void setClassHot(Integer classHot) {
        this.classHot = classHot;
    }
}
