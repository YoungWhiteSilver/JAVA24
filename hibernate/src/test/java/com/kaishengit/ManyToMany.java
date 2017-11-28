package com.kaishengit;

import com.kaishengit.pojo.Boy;
import com.kaishengit.pojo.HibernateUtil;
import com.kaishengit.pojo.Label;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/28
 */
public class ManyToMany {

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

        Boy boy = (Boy) session.get(Boy.class, 1);

        System.out.println(boy.getBoyName());
        Set<Label> labelSet = boy.getLabelSet();

        for(Label label: labelSet) {
            System.out.println(label.getLabelName());
        }

    }

    @Test
    public void findAll2() {

        Label label = (Label) session.get(Label.class, 3);

        System.out.println(label.getLabelName());

        Set<Boy> boySet = label.getBoySet();

        for(Boy boy: boySet) {

            System.out.println(boy.getBoyName());

        }

    }

    @Test
    public void save() {

        Boy boy = new Boy("迪达", 21);
        Boy boy1 = new Boy("田高", 23);

        Label label = new Label("高玩");
        Label label2 = new Label("大牛");

        Set<Label> labelSet = new HashSet<>();

        labelSet.add(label);
        labelSet.add(label2);

        boy.setLabelSet(labelSet);
        boy1.setLabelSet(labelSet);
        //最佳实践：让其中一端维护关系，先存不维护关系的对象，再存维护关系的对象
        session.save(label);
        session.save(label2);

        session.save(boy);
        session.save(boy1);


    }

}
