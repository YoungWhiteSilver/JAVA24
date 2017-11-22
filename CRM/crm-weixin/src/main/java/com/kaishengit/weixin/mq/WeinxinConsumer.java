package com.kaishengit.weixin.mq;

import com.kaishengit.weixin.CrmWeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/22
 */
@Component
public class WeinxinConsumer {

    @Autowired
    private CrmWeixinUtil weixinUtil;

    @JmsListener(destination = "weixin-queue")
    public void sendMessageTouser(String message) {

        weixinUtil.sendTextMessage("WangMingXin", message);

    }


}
