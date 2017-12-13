package com.kaishengit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-dubbo.xml");
        classPathXmlApplicationContext.start();

        System.out.println("-------------------------run-------------------------------");
        System.in.read();

    }
}
