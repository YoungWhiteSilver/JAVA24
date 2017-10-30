package com.kaishengit;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 *
 * @author silver
 * @date 2017/10/30
 */
@Configuration
@ComponentScan
@PropertySource(value = "classpath:config.properties")
public class Spring {

    /*
    //第一种方法
    @Value("${jdbc.driver}")
    private String driverClass;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.userName}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource basicDataSource() {

        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(driverClass);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(basicDataSource());

        return jdbcTemplate;

    }


    */

    //第二种

    @Autowired
    private Environment environment;

    @Bean
    public DataSource basicDataSource() {

        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        basicDataSource.setUrl(environment.getProperty("jdbc.url"));
        basicDataSource.setUsername(environment.getProperty("jdbc.userName"));
        basicDataSource.setPassword(environment.getProperty("jdbc.password"));

        return basicDataSource;

    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(basicDataSource());

        return jdbcTemplate;

    }


















}
