package com.pym.msg.ifs.impl;

import com.pym.base.ResultModel;
import com.pym.msg.ifs.MsgServiceInterface;
import com.pym.msg.model.Content;
import com.pym.msg.model.EmailContent;
import com.pym.msg.model.MsgModel;
import com.pym.msg.mq.MsgMqCustomer;
import com.pym.msg.mq.MqProducerService;
import com.pym.msg.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 21:43
 */
@RestController
public class MsgServiceController implements MsgServiceInterface {
    @Autowired
    private EmailService emailService;

    @Autowired
    private MqProducerService mqProducerService;
    @Override
    public ResultModel sendEmailSync(MsgModel<EmailContent> msgModel) {
        return emailService.sendMsg(msgModel)? ResultModel.ok():ResultModel.fail();
    }
    @Override
    public ResultModel sendEmailAsync(MsgModel<EmailContent> msgModel) {
        return mqProducerService.sendMsg(msgModel)? ResultModel.ok():ResultModel.fail();
    }

    @Override
    public ResultModel sendMsgQueue(MsgModel<? extends Content> msgModel) {

        return mqProducerService.sendMsg(msgModel)?ResultModel.ok() : ResultModel.fail();
    }

    @Override
    public ResultModel sendMsgTopic(MsgModel<? extends Content> msgModel) {
        return mqProducerService.sendMsgTopic(msgModel) ? ResultModel.ok() : ResultModel.fail();
    }

    @Override
    public ResultModel sendQueue(Map<String, Object> msg) {
        return mqProducerService.sendQueue(msg.get("destinationName").toString(),msg)? ResultModel.ok() : ResultModel.fail();
    }

    @Override
    public ResultModel sendTopic(Map<String, Object> msg) {
        return mqProducerService.sendTopic(msg.get("destinationName").toString(), msg) ? ResultModel.ok() : ResultModel.fail();
    }


}
