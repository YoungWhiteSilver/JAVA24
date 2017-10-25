package com.kaishengit.entity;

import java.util.List;

/**
 *
 * @author silver
 * @date 2017/10/24
 */
public class Personnel {

    private int id;
    private String personnelName;
    private int personnelAge;
    private int personnelTeamId;
    private Team team;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public int getPersonnelAge() {
        return personnelAge;
    }

    public void setPersonnelAge(int personnelAge) {
        this.personnelAge = personnelAge;
    }

    public int getPersonnelTeamId() {
        return personnelTeamId;
    }

    public void setPersonnelTeamId(int personnelTeamId) {
        this.personnelTeamId = personnelTeamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", personnelName='" + personnelName + '\'' +
                ", personnelAge=" + personnelAge +
                ", personnelTeamId=" + personnelTeamId +
                ", team=" + team +
                '}';
    }

}

