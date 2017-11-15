package com.kaishengit.crm.service.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/15
 */
public class QuartzJob implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("<-====================================->");
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Integer accountId = dataMap.getInt("accountId");

        System.out.println("Quartz Running....." + accountId);

    }
}
