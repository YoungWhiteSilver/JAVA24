package com.kaishengit;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/22
 */
public class ActiverMQTestCase {

    @Test
    public void sendMessageTOQueue() throws JMSException {

        //1.创建ConnectionFactory
        String brokerUrl = "tcp://localhost:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

        //2.创建Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        /**
         *3.创建Session(第一个参数：是否启用事务; 第二个参数：指定签收消息的模式)
         *Session.CLIENT_ACKNOWLEDGE ：客户端签收
         *Session.AUTO_ACKNOWLEDGE : 自动签收
         */
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);

        //4.创建 Destination 目的地对象
        Destination destination = session.createQueue("test-message");

        //5.船舰消息的生产者
        MessageProducer messageProducer = session.createProducer(destination);
        /*
        * 设置持久化 根据业务需要设置 持久化后不会随着active的关闭而删除数据
        *DeliveryMode.PERSISTENT ：持久化
        * DeliveryMode.NON_PERSISTENT 不持久化
        * */
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //6.创建消息
        TextMessage textMessage = session.createTextMessage("1-MQ");

        //7.发送消息
        messageProducer.send(textMessage);

        /*
        * 由于设置了开启事务，所以需要手动提交
        * */
        session.commit();

        messageProducer.close();
        session.close();
        connection.close();

    }

    @Test
    public void consumerMessageFronQueue() throws JMSException, IOException {

        //1.创建ConnectionFactory
        String brokerUrl = "tcp://localhost:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

        //2.创建Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //3.创建Session
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        //4.创建目的地
        Destination destination = session.createQueue("test-message");

        //5.创建消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);

        //6.消费消息，监听队列中的消息，如果有新的消息，则执行onMessage方法
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {

                    System.out.println(">>>>>>>>>" + textMessage.getText());
                    //因为开启了客户端签收，所以需要手动签收
                    textMessage.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });

        System.in.read();
        //7.释放资源
        messageConsumer.close();
        session.close();
        connection.close();


    }




}
