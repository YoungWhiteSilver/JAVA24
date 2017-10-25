package com.kaishengit.entity;

import java.util.List;

/**
 * Created by silver on 2017/10/25.
 */
public class Boy {

    private int id;
    private String boyName;
    private int boyAge;
    private List<Label> labelList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoyName() {
        return boyName;
    }

    public void setBoyName(String boyName) {
        this.boyName = boyName;
    }

    public int getBoyAge() {
        return boyAge;
    }

    public void setBoyAge(int boyAge) {
        this.boyAge = boyAge;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "id=" + id +
                ", boyName='" + boyName + '\'' +
                ", boyAge=" + boyAge +
                ", labelList=" + labelList +
                '}';
    }
}
