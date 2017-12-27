package com.kaishengit.eurekaconsumer.pojo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/27
 */
public class Movie {

    private Integer id;
    private String name;
    private String autor;

    public Movie() {
    }

    public Movie(Integer id, String name, String autor) {
        this.id = id;
        this.name = name;
        this.autor = autor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
