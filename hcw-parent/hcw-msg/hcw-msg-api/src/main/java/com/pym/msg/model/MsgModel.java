package com.pym.msg.model;

import com.pym.constants.Constants;
import com.pym.msg.utils.MsgUtils;
import com.pym.utils.ObjUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * TODO
 * 消息报文 内容暂时只支持字符串
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 19:44
 */
@Getter
@Setter
@Accessors(chain = true)
public class MsgModel<T extends Content> implements Serializable {
    public static final int MSG_TYPE_EMAIL = 1;
    public static final int MSG_TYPE_NOTE = 2;
    public static final int MSG_TYPE_WEI_CHAT = 3;
    //------------ header -------------------
    //Id 唯一标识，防止重复消费
    private String msgId; //创建时自动生成
    private Integer msgType;//创建时必须指定
    private String from; //发送者
    private String to; // 目标
    private String subject; //主题
    //--------------内容------------------------
    private T content; //内容

    public MsgModel() {
        this.msgId = MsgUtils.randomUID();
    }

    public String toJson() {
        return ObjUtils.toJson(this);
    }

    /* *
     * @Description
     * 创建文本形式的email模型 不带附件
     * @Param [to, subject, text]
     * @return com.pym.msg.model.MsgModel<com.pym.msg.model.EmailContent>
     * @Author zhangping
     * @Date 2020/7/16 22:26
     **/
    public static MsgModel<EmailContent> email(String to, String subject, String text) {
        return email(to, subject, text, null,null);
    }

    /* *
     * @Description
     * 创建文本形式的email模型 带附件
     * @Param [to, subject, text, attachmentUrl]
     * @return com.pym.msg.model.MsgModel<com.pym.msg.model.EmailContent>
     * @Author zhangping
     * @Date 2020/7/16 22:27
     **/
    public static MsgModel<EmailContent> email(String to, String subject, String text, String attachmentName, String attachmentUrl) {
        EmailContent emailContent = new EmailContent(2,text, attachmentName,attachmentUrl);
        return new MsgModel<EmailContent>().setMsgType(MSG_TYPE_EMAIL).setTo(to).setSubject(subject).setContent(emailContent);
    }

    /* *
     * @Description
     * 使用默认的html模板 不带附件
     * @Param [to, subject, infos]
     * @return com.pym.msg.model.MsgModel<com.pym.msg.model.EmailContent>
     * @Author zhangping
     * @Date 2020/7/17 0:05
     **/
    public static MsgModel<EmailContent> emailHtml(String to, String subject, Map<String, String> infos) {
        return emailHtml(to, subject, infos, null,null);
    }

    /* *
     * @Description
     * 使用默认的html模板 带附件
     * <!DOCTYPE html>
     *   <html lang="en">
     *      <head>
     *           <meta charset="UTF-8">
     *       </head>
     *       <body>
     *            <h1>${title}<h1>
     *           <p>${text}</p>
     *      </body>
     *   </html>
     * @Param [to, subject, infos, attachmentUrl]
     * @return com.pym.msg.model.MsgModel<com.pym.msg.model.EmailContent>
     * @Author zhangping
     * @Date 2020/7/16 23:56
     **/
    public static MsgModel<EmailContent> emailHtml(String to, String subject, Map<String, String> infos, String attachmentName,String attachmentUrl) {
        return emailHtml(to, subject, MsgUtils.defaultHtmlTemplate, infos, attachmentName ,attachmentUrl);
    }

    /* *
     * @Description
     * html形式的模型 会将infos中的数据对应填充到模板上生成text 填充形式${xxx}
     * @Param [to, subject, htmlTemplate, infos, attachmentUrl]
     * @return com.pym.msg.model.MsgModel<com.pym.msg.model.EmailContent>
     * @Author zhangping
     * @Date 2020/7/16 22:25
     **/
    public static MsgModel<EmailContent> emailHtml(String to, String subject, String htmlTemplate, Map<String, String> infos, String attachmentName,String attachmentUrl) {
        final StringBuilder text = new StringBuilder(htmlTemplate);
        if (ObjUtils.isAllNotEmpty(text, infos)) {
            infos.forEach((key, value) -> {
                String str = text.toString();
                text.delete(0, text.length()).append(str.replaceAll("\\$\\s*\\{\\s*" + key + "\\s*\\}", value));
            });
        }
        EmailContent emailContent = new EmailContent(1,text.toString(), attachmentName,attachmentUrl);
        return new MsgModel<EmailContent>().setMsgType(MSG_TYPE_EMAIL).setTo(to).setSubject(subject).setContent(emailContent);
    }
    /* *
     * @Description
     * 模型参数校验以以及部分参数补缺
     * @Param []
     * @return boolean
     * @Author zhangping
     * @Date 2020/7/17 21:19
     **/
    public boolean isOk() {
        if (!ObjUtils.isAllNotEmpty(this.msgId,this.msgType,this.to,this.subject,this.content)) {
            return false;
        }
        //内容校验
        if (ObjUtils.isEmpty(content.getText())) {
            return false;
        }
        if (this.msgType<1 || this.msgType>3) {
            return false;
        }
        //邮件检验
        if (this.msgType == MSG_TYPE_EMAIL) {
            //校验邮箱格式
            if (!this.to.matches(Constants.REG_EMAIL)) {
                return false;
            }
            if (!(this.content instanceof EmailContent)) {
                return false;
            }

        }
        //短信检验
        if (this.msgType == MSG_TYPE_NOTE) {
            if (!this.to.matches(Constants.REG_PHONE)) {
                return false;
            }
            //其他检验
        }
        //其他暂时不支持
        return true;
    }

}
