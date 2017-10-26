package com.kaishengit.test;

import com.kaishengit.entity.Team;
import com.kaishengit.mappers.TeamMapper;
import com.kaishengit.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by silver on 2017/10/26.
 */
public class TeamTestCase {

    private SqlSession sqlSession;
    private TeamMapper teamMapper;

    @Before
    public void init() {

        sqlSession = MyBatisUtil.getSqlSession();
        teamMapper = sqlSession.getMapper(TeamMapper.class);

    }

    @After
    public void close() {

        sqlSession.close();

    }

    @Test
    public void findAll() {

        List<Team> teamList = teamMapper.findAll();

        for(Team team: teamList) {

            System.out.println(team);

        }

    }

    @Test
    public void save() {

        Team team = new Team();
        team.setTeamName("GAM");
        teamMapper.save(team);

        sqlSession.commit();

        System.out.println(team.getId());

    }



















}
