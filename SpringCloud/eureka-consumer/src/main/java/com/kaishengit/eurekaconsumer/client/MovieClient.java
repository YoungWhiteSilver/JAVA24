package com.kaishengit.eurekaconsumer.client;

import com.kaishengit.eurekaconsumer.pojo.Movie;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/27
 */
@FeignClient(name = "MOVIE-SERVICE-PROVIDER-1")
public interface MovieClient {

    @GetMapping("/movie/{id}")
    Movie getMovie(@PathVariable(name = "id") Integer id);
}
