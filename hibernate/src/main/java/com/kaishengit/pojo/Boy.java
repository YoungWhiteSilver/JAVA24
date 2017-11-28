package com.kaishengit.pojo;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/28
 */
public class Boy {

    private Integer id;
    private String boyName;
    private Integer boyAge;
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
