package com.pym.utils;

import com.pym.constants.Constants;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 23:35
 */
public class TokenUtils {
    private TokenUtils(){

    }
    /* *
     * @Description
     * 产生唯一token
     * @Param []
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/14 23:45
     **/
    public static String make() {
        return Constants.TOKEN_TAG + "-" +UUID.randomUUID().toString();
    }
    /* *
     * @Description
     * 从cookie中取出token
     * @Param [req]
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/14 23:46
     **/
    public static String getFromCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (ObjUtils.isEmpty(cookies)) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(Constants.TOKEN_COOKIE_NAME)) {
                return cookie.getValue();
            }
        }
        return null;
    }
    /* *
     * @Description
     * 将token写入cookie
     * @Param [token, response]
     * @return void
     * @Author zhangping
     * @Date 2020/7/14 23:49
     **/
    public static void setToCooke(String token ,HttpServletResponse response) {
        Cookie cookie = new Cookie(Constants.TOKEN_COOKIE_NAME, token);
        response.addCookie(cookie);
    }

}
