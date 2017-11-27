package com.kaishengit;

import com.kaishengit.pojo.HibernateUtil;
import com.kaishengit.pojo.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/27
 */
public class StudentHQLTestCase {

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

        String hql = "from Student";
        Query query = session.createQuery(hql);

        // 分页
        query.setFirstResult(0);
        query.setMaxResults(5);

        List<Student> studentList = query.list();

        showList(studentList);

    }

    @Test
    public void count() {

        String hql = "select count(*), MAX(stuAge) from Student";
        Query query = session.createQuery(hql);

        Object[] obj = (Object[]) query.uniqueResult();

        System.out.println(obj[0] + "--->" + obj[1]);

    }


    private void showList(List<Student> studentList) {

        for(Student student: studentList) {

            System.out.println(student);

        }

    }


}
