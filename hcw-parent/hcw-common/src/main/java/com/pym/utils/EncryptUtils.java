package com.pym.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * TODO
 *  加解密工具类
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 0:16
 */
@Slf4j
public class EncryptUtils {
    /* *
     * @Description
     * base64 不加盐加密
     * @Param [info]
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/15 0:29
     **/
    public static String base64Encode(String info) {
        return base64Encode(info, null);
    }
    /* *
     * @Description
     * base64 加盐加密
     * @Param [info, salt]
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/15 0:29
     **/
    public static String base64Encode(String info, String salt ) {
        if (ObjUtils.isEmpty(info)) {
            return null;
        }
        String sub = info;
        if (!ObjUtils.isEmpty(salt)) {
            sub += salt;
        }
        try {
            return new BASE64Encoder().encode(sub.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("加密失败：{}",e);
            return null;
        }
    }

    /* *
     * @Description
     * base64 解密
     * @Param [info, salt]
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/15 0:41
     **/
    public static String base64Decode(String info, String salt){
        if (ObjUtils.isEmpty(info)) {
            return null;
        }
        try {
            String res = new String(new BASE64Decoder().decodeBuffer(info),"UTF-8");
            if (!ObjUtils.isEmpty(salt)) {
                if (!res.endsWith(salt)) {
                   throw new RuntimeException("盐不正确");
                }
               res = res.substring(0,res.length()-salt.length());
            }
            return res;
        } catch (Exception e) {
            log.error("解密失败：{}",e);
            return null;
        }
    }
    /* *
     * @Description
     * 不加盐解密
     * @Param [info]
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/15 0:42
     **/
    public static String base64Decode(String info) {
        return base64Encode(info,null);
    }

}
