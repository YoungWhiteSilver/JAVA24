package com.kaishengit.eurekaservice.controller;

import com.kaishengit.eurekaservice.pojo.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/27 */
@RestController
public class MovieController {

    @GetMapping("/movie/{id}")
    public Movie getMovie(@PathVariable(name = "id") Integer id) {

        System.out.println("<===========================================>" + id + "<===========================================>");
        return new Movie(10001, "哪吒传奇", "列夫托尔琪，赵");

    }

    @GetMapping("/movie/all")
    public List<Movie> getMovieAll() {

        System.out.println("<++++++++++++++++++++++++++list++++++++++++++++++++++++>");

        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(10002, "路与海", "共惊奇"));
        movieList.add(new Movie(10003, "天空之城", "璐黄"));

        return movieList;
    }
}
