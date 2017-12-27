package com.kaishengit.eurekaconsumer.controller;

import com.kaishengit.eurekaconsumer.client.MovieClient;
import com.kaishengit.eurekaconsumer.pojo.Movie;
import com.kaishengit.eurekaconsumer.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/27
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate template;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private MovieClient movieClient;

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/{id:\\d+}")
    public Movie showMovie(@PathVariable Integer id) {

        //第一种方式 配合RestTemplate使用
        //String url = "http://127.0.0.1:8080/movie/" + id;
        //return template.getForObject(url, Movie.class);

        /*================================================分割线============================================*/
        //第二种 配和loadBalancerClient使用 并且在main里添加@EnableDiscoveryClient
        //ServiceInstance serviceInstance = loadBalancerClient.choose("MOVIE-SERVICE-PROVIDER-1");

        //？？？？
        //System.out.println(serviceInstance.getHost() + "=============1====================");

        //？？？？
        //System.out.println(serviceInstance.getUri() + "===============2==================");

        //服务名称
        //System.out.println(serviceInstance.getServiceId() + "===========3======================");

        //打印为端口号
        //System.out.println(serviceInstance.getPort() + "================4=================");

        //String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/movie/" + id;
        //return template.getForObject(url, Movie.class);

        /*================================================分割线============================================*/

        //第三种，使用Ribbon需要在RestTemplate的@Bean注解下加上@LoadBalanced，并且导入Ribbon的依赖。
        //String url = "http://MOVIE-SERVICE-PROVIDER-1/movie/" + id;
        //return template.getForObject(url, Movie.class);

        /*================================================分割线============================================*/
        //第四种：使用Feign，添加Feign依赖，在main里添加@EnableFeignClients注解，并创建访问接口movieClient。
        //return movieClient.getMovie(id);
        /*================================================分割线============================================*/
        return movieService.getMovie(id);
    }

    @GetMapping("/movie/all")
    public List<Movie> getAll() {
        return movieService.getMovieAll();
    }

}
