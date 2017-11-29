package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/28
 *
 * 如果表名和类名一致，可以不写@Table注解
 * 同理，如果列名和表列名一样。可以不写
 *
 */
@Entity
@Table(name = "t_label")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "label_name")
    private String labelName;

    /*代表这个放弃维护关系*/
    @ManyToMany(mappedBy = "labelSet")
    private Set<Boy> boySet;

    public Label() {
    }

    public Label(String labelName) {
        this.labelName = labelName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Boy> getBoySet() {
        return boySet;
    }

    public void setBoySet(Set<Boy> boySet) {
        this.boySet = boySet;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
