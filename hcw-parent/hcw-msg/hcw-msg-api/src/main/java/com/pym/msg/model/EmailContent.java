package com.pym.msg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pym.utils.ObjUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.net.URL;

/**
 * TODO
 * 邮件内容封装类
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/16 20:24
 */
@Slf4j
public class EmailContent implements Serializable, Content {
    @JsonIgnore
    public static final int TYPE_EMAIL_HTML = 1;  // html
    @JsonIgnore
    public static final int TYPE_EMAIL_TEXT = 2;// 文本



    private String text; //内容
    private Integer type = 2; //html 跟纯文本
    private String attachmentName; //附件名称
    private String attachmentUrl = ""; //附件路径
    @JsonIgnore
    private UrlResource attachmentStreamSource;//附件

    public EmailContent() {

    }
    public EmailContent(Integer type, String text) {
        this.type = type;
        this.text = text;
    }
    public EmailContent(Integer type, String text, String attachmentName, String attachmentUrl) {
        this.type = type;
        this.text = text;
        this.attachmentName = attachmentName;
        this.attachmentUrl = attachmentUrl;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    /* *
     * @Description
     * 根据附件url创建流
     * @Param []
     * @return java.io.InputStream
     * @Author zhangping
     * @Date 2020/7/17 0:08
     **/
    public UrlResource getAttachmentStreamSource() {
        if (ObjUtils.isEmpty(attachmentUrl)) {
            return null;
        }
        if (this.attachmentStreamSource != null) {
            return this.attachmentStreamSource;
        }
        try {
            return new UrlResource(new URL(attachmentUrl));
        } catch (Exception e) {
            log.error("获取附件失败", e);
            return null;
        }
    }

    public void setAttachmentStreamSource(UrlResource attachmentStreamSource) {
        this.attachmentStreamSource = attachmentStreamSource;
    }

    public String getAttachmentName() {
        if (attachmentName != null) {
            return attachmentName;
        }
        if (!ObjUtils.isEmpty(attachmentUrl)) {
            String[] strs = attachmentUrl.split("/");
            return !ObjUtils.isEmpty(strs) ? strs[strs.length - 1] : "";
        }
        return "";
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        if (type == null || type < 1 || type > 2) {
            return 2;
        }
        return type;
    }
    public boolean isHtml() {
        return this.getType() == TYPE_EMAIL_HTML;
    }
}
