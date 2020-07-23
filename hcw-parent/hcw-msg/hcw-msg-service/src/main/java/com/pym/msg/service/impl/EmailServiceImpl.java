package com.pym.msg.service.impl;

import com.pym.base.BaseException;
import com.pym.msg.model.EmailContent;
import com.pym.msg.model.MsgModel;
import com.pym.msg.service.EmailService;
import com.pym.msg.utils.MsgConstants;
import com.pym.utils.ObjUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 21:13
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String SYS_EMAIL_ACCOUNT;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private StringRedisTemplate redisTemplate;
    /* *
     * @Description
     * 发送文本邮件
     * @Param [msgModel]
     * @return boolean
     * @Author zhangping
     * @Date 2020/7/15 21:40
     **/

    @Override
    public boolean sendMsg(MsgModel<EmailContent> msgModel) {
        if (ObjUtils.isEmpty(msgModel)
                || msgModel.getMsgType() != MsgModel.MSG_TYPE_EMAIL
                || !msgModel.isOk()) {
            throw new BaseException("关键信息有误或者缺失");
        }
        //处理重复发送
        if (redisTemplate.opsForSet().isMember(MsgConstants.MSG_ID_REDIS_SET_KEY,msgModel.getMsgId())) {
            return true;
        }
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            //设置发送人
            mimeMessageHelper.setFrom(SYS_EMAIL_ACCOUNT);
            //接收人
            mimeMessageHelper.setTo(msgModel.getTo());
            //主题
            mimeMessageHelper.setSubject(msgModel.getSubject());
            //内容
            mimeMessageHelper.setText(msgModel.getContent().getText(),msgModel.getContent().isHtml());
            InputStreamSource inputStreamSource = msgModel.getContent().getAttachmentStreamSource();
            if (inputStreamSource != null) {
                //附件
                mimeMessageHelper.addAttachment(msgModel.getContent().getAttachmentName(), inputStreamSource);
            }
            //调整逻辑顺序，插入redis放在发送之前：1.如果先发邮件再存redis，可能邮件发成功，存入redis出错，导致mq回滚，出现重复消费
            //2.先redis 再发送，可能redis存入成功邮件发送失败，出现异常捕捉后删除redis中的key
            redisTemplate.opsForSet().add(MsgConstants.MSG_ID_REDIS_SET_KEY,msgModel.getMsgId());
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            redisTemplate.opsForSet().remove(MsgConstants.MSG_ID_REDIS_SET_KEY,msgModel.getMsgId());
            log.error("发送失败",e);
           throw new BaseException("邮件发送失败");
        }

    }

}
