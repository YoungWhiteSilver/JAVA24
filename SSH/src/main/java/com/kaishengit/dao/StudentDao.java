package com.kaishengit.dao;

import com.kaishengit.pojo.Student;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/29
 */
@Repository
public class StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Student> selectAllByPage(Integer p, Integer age, Integer className, String stuName) {

        Criteria criteria = getSession().createCriteria(Student.class);

        if(StringUtils.isNotEmpty(stuName)) {

            criteria.add(Restrictions.like("stuName", stuName, MatchMode.ANYWHERE));

        }

        if(age != null && age > 0) {

            criteria.add(Restrictions.eq("stuAge", age));

        }

        if(className != null && className > 0) {

            criteria.createAlias("studentClass", "stuClass");
            criteria.add(Restrictions.eq("stuClass.id", className));

        }

        criteria.setFirstResult(p);
        criteria.setMaxResults(10);

        return criteria.list();

    }

}
