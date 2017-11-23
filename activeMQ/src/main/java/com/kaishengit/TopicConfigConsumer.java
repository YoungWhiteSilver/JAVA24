package com.kaishengit;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/23
 */
@Component
public class TopicConfigConsumer {

    @JmsListener(destination = "spring-topic")
    public void doSomething(String message) {

        System.out.println("XXXXXXXX" + message);

    }

}
