package com.kaishengit;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/23
 */
@Component
public class TopicConsumer implements MessageListener{
    @Override
    public void onMessage(Message message) {

        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("=======>" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
