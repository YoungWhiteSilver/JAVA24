package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.example.TaskExample;
import com.kaishengit.crm.mappers.TaskMapper;
import com.kaishengit.crm.service.TaskService;

import com.kaishengit.crm.service.exception.ServiceException;
import com.kaishengit.crm.service.jobs.SendMessage;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/14
 */
@Service
public class TaskServiceImpl implements TaskService{

    /**
     * 未完成
     */
    public static final Integer UNDONE = 0;

    /**
     * 完成
     */
    public static final Integer DONE = 1;

    private Logger logger = LoggerFactory.getLogger(SendMessage.class);

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 通过 saleId 查找
     *
     * @param id
     * @return
     */
    @Override
    public List<Task> findBySaleId(Integer id) {

        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andSaleIdEqualTo(id);

        return taskMapper.selectByExample(taskExample);

    }

    /**
     * 创建任务，并使用Quartz
     *
     * @param map
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTask(Map<String, Object> map) {

        Task task = new Task();

        task.setDone(UNDONE);
        task.setAccountId((Integer) map.get("accountId"));
        task.setTitle((String) map.get("title"));
        task.setCustId((Integer) map.get("custId"));
        task.setSaleId((Integer) map.get("saleId"));

        DateTimeFormatter finishTime = DateTimeFormat.forPattern("yyyy-MM-dd");
        Date dateFinishTime = (Date) finishTime.parseDateTime((String) map.get("finishTime")).toDate();

        task.setFinishTime(dateFinishTime);
        DateTime dateTimeR = null;
        if(StringUtils.isNotEmpty((String) map.get("remindTime"))) {

            DateTimeFormatter remindTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
            dateTimeR = remindTime.parseDateTime((String) map.get("remindTime"));
            Date dateRemindTime = (Date) dateTimeR.toDate();

            task.setRemindTime(dateRemindTime);
        }

        taskMapper.insertSelective(task);

        if(StringUtils.isNotEmpty((String) map.get("remindTime") ) && dateTimeR.isAfterNow() ) {

             /*
            添加新的调度任务
            */
            JobDataMap jobDataMap  = new JobDataMap();
            jobDataMap.put("accountId", (Integer) map.get("accountId"));
            jobDataMap.put("message", (String) map.get("title"));

            JobDetail jobDetail = JobBuilder
                    .newJob(SendMessage.class)
                    .withIdentity(new JobKey("taskID:" + task.getId(), "sendMessageGroup"))
                    .build();
            /*String -->  DateTime */
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
            DateTime dateTime = formatter.parseDateTime((String) map.get("remindTime"));

            StringBuilder cron = new StringBuilder("0")
                    .append(" ")
                    .append(dateTime.getMinuteOfHour())
                    .append(" ")
                    .append(dateTime.getHourOfDay())
                    .append(" ")
                    .append(dateTime.getDayOfMonth())
                    .append(" ")
                    .append(dateTime.getMonthOfYear())
                    .append(" ? ")
                    .append(dateTime.getYear());

            logger.info("CRON EX: {}" ,cron.toString());

            //使用Cron
            ScheduleBuilder scheduleBuilder =
                    CronScheduleBuilder.cronSchedule(cron.toString());
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(scheduleBuilder)
                    .build();

            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            try{
                scheduler.scheduleJob(jobDetail, trigger);
                scheduler.start();
            } catch (Exception e) {
                throw new ServiceException("添加定时任务异常", e);
            }

        }

    }

    /**
     * 当shouw = 0时 查找未完成的
     * 当show = null 时，查找所有
     *
     * @param show
     * @return
     */
    @Override
    public List<Task> findAll(Integer show, Account account) {

        TaskExample taskExample = new TaskExample();

        if(show != null && show == 0) {

            taskExample.createCriteria().andDoneEqualTo(show);
        }

        taskExample.createCriteria().andAccountIdEqualTo(account.getId());
        List<Task> taskList = taskMapper.selectByExampleWithBLOBs(taskExample);

        return taskList;

    }


}
