package com.kaishengit.crm.service.jobs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/15
 */
public class SendMessage implements Job {

    private Logger logger = LoggerFactory.getLogger(SendMessage.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String message = (String) dataMap.get("message");
        Integer accountId = (Integer) dataMap.get("accountId");
        System.out.println("Hello =========================== "  + message);

        logger.info("TO:{} Message:{}", accountId, message);

        try {

            ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getScheduler()
                    .getContext().get("springApplicationContext");

            JmsTemplate jmsTemplate = (JmsTemplate) applicationContext.getBean("jmsTemplate");

            jmsTemplate.send("weixin-queue", new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage("这是任务调度发给微信的");
                    return textMessage;
                }
            });


        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
