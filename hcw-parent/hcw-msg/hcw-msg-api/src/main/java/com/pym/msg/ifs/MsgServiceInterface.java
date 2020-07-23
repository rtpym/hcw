package com.pym.msg.ifs;

import com.pym.base.ResultModel;
import com.pym.msg.model.Content;
import com.pym.msg.model.EmailContent;
import com.pym.msg.model.MsgModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 21:01
 */
@RequestMapping("/msg")
public interface MsgServiceInterface {
    /* *
     * @Description
     * 同步发送
     * @Param [msgModel]
     * @return com.pym.base.ResultModel<java.lang.String>
     * @Author zhangping
     * @Date 2020/7/15 21:10
     **/
    @PostMapping("/send/email/sync")
    ResultModel sendEmailSync(@RequestBody MsgModel<EmailContent> msgModel);
    /* *
     * @Description
     * 异步发送 直接放入mq
     * @Param [msgModel]
     * @return com.pym.base.ResultModel<java.lang.String>
     * @Author zhangping
     * @Date 2020/7/15 21:11
     **/
    @PostMapping("/send/email/async")
    ResultModel sendEmailAsync(@RequestBody MsgModel<EmailContent> msgModel);
    /* *
     * @Description
     * 发送消息到mq msg默认queue
     * @Param [msgModel]
     * @return com.pym.base.ResultModel<java.lang.String>
     * @Author zhangping
     * @Date 2020/7/17 23:41
     **/
    @PostMapping("send/msg/queue")
    ResultModel sendMsgQueue(@RequestBody MsgModel<? extends Content> msgModel);
    /* *
     * @Description
     * 发送消息到mq msg默认topic
     * @Param [msgModel]
     * @return com.pym.base.ResultModel<java.lang.String>
     * @Author zhangping
     * @Date 2020/7/17 23:43
     **/
    @PostMapping("/send/msg/topic")
    ResultModel sendMsgTopic(@RequestBody MsgModel<? extends Content> msgModel);

    /* *
     * @Description
     * 发送消息到队列 注意必须要在参数中指定destinationName
     * @Param [msg]
     * @return com.pym.base.ResultModel<java.lang.String>
     * @Author zhangping
     * @Date 2020/7/17 23:45
     **/
    @PostMapping("send/queue")
    ResultModel sendQueue(@RequestBody Map<String,Object> msg);
    /* *
     * @Description
     * 发布topic 注意必须要在参数中指定destinationName
     * @Param [msg]
     * @return com.pym.base.ResultModel<java.lang.String>
     * @Author zhangping
     * @Date 2020/7/17 23:47
     **/
    @PostMapping("send/topic")
    ResultModel sendTopic(@RequestBody Map<String,Object> msg);
}
