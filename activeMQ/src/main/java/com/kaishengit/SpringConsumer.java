package com.kaishengit;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/22
 */
@Component
public class SpringConsumer {

    @JmsListener(destination = "spring-queue")
    public void doSomething(String message) {

        System.out.println("=-=-=-=-=-" + message);

    }


}
