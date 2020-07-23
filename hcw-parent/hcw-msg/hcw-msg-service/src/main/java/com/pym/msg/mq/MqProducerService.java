package com.pym.msg.mq;

import com.pym.msg.model.MsgModel;
import com.pym.msg.service.MsgService;
import com.pym.msg.utils.MsgConstants;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * TODO
 *  mq生产者服务
 * @author zhangping
 * @version 1.0
 * @date 2020/7/17 12:15
 */
@Service
public class MqProducerService implements MsgService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    /* *
     * @Description
     * 将消息放入mq 只针对本系统中的消息模型
     * @Param [msgModel]
     * @return boolean
     * @Author zhangping
     * @Date 2020/7/17 12:29
     **/
    @Override
    public boolean sendMsg(MsgModel msgModel) {
        jmsMessagingTemplate.convertAndSend(MsgConstants.MSG_QUEUE_DESTINATION_NAME,msgModel);
        return true;
    }
    /* *
     * @Description
     * 发布本系统定义的消息topic
     * @Param [msgModel]
     * @return boolean
     * @Author zhangping
     * @Date 2020/7/17 15:15
     **/
    public boolean sendMsgTopic(MsgModel msgModel) {
        jmsMessagingTemplate.convertAndSend(new ActiveMQTopic(MsgConstants.MSG_TOPIC_DESTINATION_NAME));
        return true;
    }
    /* *
     * @Description
     * 将消息发送到mq指定queue
     * @Param [destinationName, msg]
     * @return boolean
     * @Author zhangping
     * @Date 2020/7/17 12:46
     **/
    public boolean sendQueue(String destinationName, Object msg) {
        jmsMessagingTemplate.convertAndSend(destinationName,msg);
        return true;
    }
    /* *
     * @Description
     * 发布topic
     * @Param [destination, msg]
     * @return boolean
     * @Author zhangping
     * @Date 2020/7/17 14:01
     **/
    public boolean sendTopic(String destinationName, Object msg) {
        Destination destination = new ActiveMQTopic(destinationName);
        jmsMessagingTemplate.convertAndSend(destination,msg);
        return true;
    }
}
