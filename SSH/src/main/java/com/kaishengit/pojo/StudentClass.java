package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/29
 */
@Entity
@Table(name = "t_class")
public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "class_hot")
    private Integer classHot;
    /*mappedBy为多的名字 cascade = CascadeType.REMOVE 及联删除 ：
    当该表删除了一条记录那么关联的表记录也会删除*/
    @OneToMany(mappedBy = "studentClass")
    private Set<Student> studentSet;

    public StudentClass() {
    }

    public StudentClass(String className, Integer classHot,
                        Set<Student> studentSet) {

        this.className = className;
        this.classHot = classHot;
        this.studentSet = studentSet;

    }

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
