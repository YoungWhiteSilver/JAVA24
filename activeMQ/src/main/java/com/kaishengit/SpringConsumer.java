package com.kaishengit;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.xml.soap.Text;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/22
 */
@Component
public class SpringConsumer {

    @JmsListener(destination = "spring-queue")
    public void doSomething(Message message, Session session) throws JMSException {

        TextMessage textMessage = (TextMessage) message;
        try{
            System.out.println("=-=-=-=-=-" + textMessage.getText());
            if(1 == 1) {
                throw new JMSException("============1213");
            }
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
            session.recover();

        }

    }


}
