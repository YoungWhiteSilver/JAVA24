package com.kaishengit;

import com.kaishengit.pojo.HibernateUtil;
import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.StudentClass;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/28
 */
public class OneToMany {

    private Session session;

    @Before
    public void before() {

        session = HibernateUtil.getSession();
        session.beginTransaction();

    }

    @After
    public void after() {

        session.getTransaction().commit();

    }

    @Test
    public void findAll() {

        StudentClass studentClass = (StudentClass) session.get(StudentClass.class, 2);
        System.out.println(studentClass.getClassName());

        Set<Student> students = studentClass.getStudentSet();

        for (Student student: students) {
            System.out.println(student);
        }

    }

    @Test
    public void findAll2() {

        Student student = (Student) session.get(Student.class, 3);
        System.out.println(student.getStuName());

        StudentClass studentClass = student.getStudentClass();
        System.out.println(studentClass.getClassName());
    }

    @Test
    public void save() {

        StudentClass studentClass = new StudentClass();

        studentClass.setClassName("皮皮班");
        studentClass.setClassHot(12321313);

        Student student1 = new Student("田安", 25 , "常熟");
        Student student2 = new Student("迪达", 27, "邯郸");


        Set<Student> studentSet = new HashSet<>();

        studentSet.add(student1);
        studentSet.add(student2);

        studentClass.setStudentSet(studentSet);

        /*一的已经放弃维护关系在xml中设置*/
        session.save(student1);
        session.save(student2);
        session.save(studentClass);


    }

    @Test
    public void save2() {

        StudentClass studentClass = new StudentClass();

        studentClass.setClassName("皮皮班");
        studentClass.setClassHot(12321313);

        Student student1 = new Student("田安", 25 , "常熟");
        Student student2 = new Student("迪达", 27, "邯郸");

        student1.setStudentClass(studentClass);
        student2.setStudentClass(studentClass);

        /*
        最佳实践 先保存一，再保存多
        */
        session.save(studentClass);
        session.save(student1);
        session.save(student2);


    }


    private void showList(List<Student> studentList) {

        for(Student student: studentList) {

            System.out.println(student);

        }

    }


}
