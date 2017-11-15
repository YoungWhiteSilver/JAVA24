package com.kaishengit.crm.service.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    }
}
