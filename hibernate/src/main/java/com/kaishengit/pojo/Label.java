package com.kaishengit.pojo;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/28
 */
public class Label {

    private Integer id;
    private String labelName;
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
