package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/28
 */
@Entity
@Table(name = "t_boy")
public class Boy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "boy_name")
    private String boyName;

    @Column(name = "boy_age")
    private Integer boyAge;

    @ManyToMany
    @JoinTable(name = "t_boy_label",
                joinColumns = {@JoinColumn(name = "boy_id")},
                inverseJoinColumns = {@JoinColumn(name = "label_id")})
    private Set<Label> labelSet;

    public Boy() {
    }

    public Boy(String boyName, Integer boyAge) {

        this.boyName = boyName;
        this.boyAge = boyAge;

    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBoyName() {
        return boyName;
    }

    public void setBoyName(String boyName) {
        this.boyName = boyName;
    }

    public Integer getBoyAge() {
        return boyAge;
    }

    public void setBoyAge(Integer boyAge) {
        this.boyAge = boyAge;
    }

    public Set<Label> getLabelSet() {
        return labelSet;
    }

    public void setLabelSet(Set<Label> labelSet) {
        this.labelSet = labelSet;
    }
}
