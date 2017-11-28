package com.kaishengit;

import com.kaishengit.pojo.HibernateUtil;
import com.kaishengit.pojo.Student;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/28
 */
public class CriteriaTestCase {

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

        Criteria criteria = session.createCriteria(Student.class);

        criteria.setFirstResult(0);
        criteria.setMaxResults(10);

        List<Student> studentList = criteria.list();

        showList(studentList);
    }

    @Test
    public void findById() {

        Criteria criteria = session.createCriteria(Student.class);

        criteria.add(Restrictions.eq("id", 4));

        Student student = (Student) criteria.uniqueResult();

        System.out.println(student);

    }

    @Test
    public void findLikeName() {

        Criteria criteria = session.createCriteria(Student.class);

        criteria.add(Restrictions.like("stuName", "jack", MatchMode.ANYWHERE));

        List<Student> studentList = criteria.list();

        showList(studentList);

    }

    @Test
    public void findLikeNameAndAge() {

        Criteria criteria = session.createCriteria(Student.class);

        criteria.add(Restrictions.like("stuName", "jack", MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("stuAge", 20));

        List<Student> studentList = criteria.list();

        showList(studentList);

    }

    @Test
    public void findLikeNameOrAge() {

        Criteria criteria = session.createCriteria(Student.class);

        Criterion stuNameCriterion = Restrictions.like("stuName", "ck", MatchMode.ANYWHERE);
        Criterion stuAgeCriterion = Restrictions.eq("stuAge", 30);

        //or 第一种
        criteria.add(Restrictions.or(stuAgeCriterion, stuNameCriterion));


        //or 第二种
        /*Disjunction disjunction = Restrictions.disjunction();

        disjunction.add(stuAgeCriterion);
        disjunction.add(stuNameCriterion);


        criteria.add(disjunction);*/

        //排序
        criteria.addOrder(Order.desc("id"));

        List<Student> studentList = criteria.list();

        showList(studentList);

    }


    @Test
    public void count() {

        Criteria criteria = session.createCriteria(Student.class);

        criteria.setProjection(Projections.rowCount());

        Long count = (Long) criteria.uniqueResult();

        System.out.println(count);


    }

    @Test
    public void countAndMax() {

        Criteria criteria = session.createCriteria(Student.class);

        ProjectionList projectionList = Projections.projectionList();

        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("stuAge"));
        projectionList.add(Projections.sum("stuAge"));
        criteria.setProjection(projectionList);

        Object[] data = (Object[]) criteria.uniqueResult();

        System.out.println("count：" + data[0] + " ————> " + data[1] + " ————> " + data[2]);



    }




    private void showList(List<Student> studentList) {

        for(Student student: studentList) {

            System.out.println(student);

        }

    }

}
