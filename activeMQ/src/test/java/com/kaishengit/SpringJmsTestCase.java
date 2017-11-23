package com.kaishengit;

import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.soap.Text;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringJmsTestCase {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void sendMessageToQueue() throws IOException {

        jmsTemplate.send("spring-queue",new MessageCreator() {
            @Override
            public Message createMessage( Session session) throws JMSException {

                TextMessage textMessage = session.createTextMessage("Hello,spring2");
                return textMessage;

            }

        });
        System.in.read();
    }

    @Test
    public void sendMessageToTopic() {
        ActiveMQTopic topic = new ActiveMQTopic("spring-topic");
        jmsTemplate.send(topic,new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("Hello,Spring-Topic");
                return textMessage;
            }
        });

    }


}
