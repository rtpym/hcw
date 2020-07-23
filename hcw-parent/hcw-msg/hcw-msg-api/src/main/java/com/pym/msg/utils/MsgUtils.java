package com.pym.msg.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 19:54
 */
@Slf4j
public class MsgUtils {
    public static String defaultHtmlTemplate = "";
    static {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:\\idea\\IdeaProjects\\hcw-parent\\hcw-msg\\hcw-msg-api\\src\\main\\resources\\defaultEmailTemplate.html");
            // inputStream = EmailContent.class.getResourceAsStream("defaultEmailTemplate.html");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            defaultHtmlTemplate = new String(bytes, "UTF-8");
        } catch (Exception e) {
            //读取默认文件模板失败
            defaultHtmlTemplate = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <h1>${title}</h1>\n" +
                    "    <p>${text}</p>\n" +
                    "</body>\n" +
                    "</html>";
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("解析出错",e);
                }
            }
        }
    }
    private MsgUtils() {}

    public static String randomUID() {
        return MsgConstants.MSG_MODEL_ID_TAG + UUID.randomUUID().toString();
    }
}
