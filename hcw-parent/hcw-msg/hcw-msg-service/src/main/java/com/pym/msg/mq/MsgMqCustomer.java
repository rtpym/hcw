package com.pym.msg.mq;

import com.pym.base.BaseException;
import com.pym.msg.model.EmailContent;
import com.pym.msg.model.MsgModel;
import com.pym.msg.service.EmailService;
import com.pym.msg.utils.MsgConstants;
import com.pym.utils.ObjUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.messaginghub.pooled.jms.JmsPoolConnection;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.messaginghub.pooled.jms.JmsPoolSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;


/**
 * TODO
 *  暂时只有处理msg相关的
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 21:12
 */
@Component
@Slf4j
public class MsgMqCustomer {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private EmailService emailService;

//    @JmsListener(destination = "", containerFactory = "jmsListenerContainerTopic")
//    public void receiveTopic(ObjectMessage message) {
//
//    }
    @JmsListener(destination = MsgConstants.MSG_QUEUE_DESTINATION_NAME, containerFactory = "jmsListenerContainerQueue")
    public void receiveQueueMsg(ObjectMessage message) {
        MsgModel msgModel = null;
        try {
            msgModel = (MsgModel)(message.getObject());
        } catch (JMSException e) {
            log.error("系统异常",e);
            throw new BaseException("系统异常");
        }
        if (!msgModel.isOk()) {
            throw new BaseException("关键信息错误或缺失");
        }
        switch (msgModel.getMsgType()){
            case MsgModel.MSG_TYPE_EMAIL:
                if (!emailService.sendMsg((MsgModel<EmailContent>) msgModel)){
                    throw new BaseException("发送失败");
                }
            case MsgModel.MSG_TYPE_NOTE:
                //do something
            case MsgModel.MSG_TYPE_WEI_CHAT:
                //do something
            default:
                //do something
        }
    }
    /* *
     * @Description
     * 处理死信队列  //暂时先放到redis hash
     * @Param [message]
     * @return void
     * @Author zhangping
     * @Date 2020/7/17 20:48
     **/
    @JmsListener(destination = "ActiveMQ.DLQ", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueueDLQMsg(ObjectMessage message){
        try {
            redisTemplate.opsForList().leftPush(MsgConstants.MSG_BAD_REDIS_LIST_KEY,ObjUtils.toJson(message.getObject()));
        } catch (JMSException e) {
            log.error("系统异常",e);
            throw new BaseException("系统异常");
        }
    }


}
