package com.kaishengit.eurekaconsumer.service;

import com.kaishengit.eurekaconsumer.pojo.Movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/27
 */
@Service
public class MovieService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getMovieFallback")
    public Movie getMovie(Integer id) {

        String url = "http://MOVIE-SERVICE-PROVIDER-1/movie/" + id;
        return restTemplate.getForObject(url, Movie.class);
    }

    @HystrixCommand(fallbackMethod = "getMovieAllFallback")
    public List<Movie> getMovieAll() {

        String url = "http://MOVIE-SERVICE-PROVIDER-1/movie/all";
        return restTemplate.getForObject(url, List.class);

    }


    public Movie getMovieFallback(Integer id) {

        return new Movie(id, "大话西游", "路易斯赵。威廉琪");

    }

    public List<Movie> getMovieAllFallback() {

        return null;

    }

}
