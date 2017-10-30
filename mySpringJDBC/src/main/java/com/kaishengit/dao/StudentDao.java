package com.kaishengit.dao;

import com.kaishengit.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author silver
 * @date 2017/10/30
 */
@Repository
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * BeanPropertyRowMapper 可以接受一个或多个 取决于方法为query（一个或多个）还是iqueryForObject(单个)
     *
     * @param id
     * @return
     */
    public Student findById(int id) {

        String sql = "select * from t_student where id = ?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);

    }

    public List<Student> findAll() {

        String sql = "select * from t_student";
        return jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

                Student student = new Student();

                student.setId(rs.getInt(1));
                student.setStuName(rs.getString(2));
                student.setStuAge(rs.getInt("stu_age"));
                student.setStuAddress(rs.getString(4));

                return student;
            }
        });
    }

    public void insert(Student student) {

        String sql = "insert into t_student (stu_name, stu_age, stu_address) values (?, ?, ?)";
        jdbcTemplate.update(sql, student.getStuName(), student.getStuAge(), student.getStuAddress());

    }

    public Long count() {

        String sql = "select count(*) from t_student";
        return jdbcTemplate.queryForObject(sql, Long.class);

    }

}
